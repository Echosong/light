# Light框架 Docker镜像构建文件
# 使用多阶段构建优化镜像大小

# 阶段1：Maven构建
FROM maven:3.9.5-openjdk-21-slim AS builder

# 设置工作目录
WORKDIR /app

# 复制pom文件（利用Docker缓存层）
COPY pom.xml .
COPY Light.Admin/pom.xml Light.Admin/
COPY Light.Api/pom.xml Light.Api/
COPY Light.Common/pom.xml Light.Common/
COPY Light.Entity/pom.xml Light.Entity/
COPY Light.Generator/pom.xml Light.Generator/
COPY Light.Packet/pom.xml Light.Packet/
COPY Light.Server/pom.xml Light.Server/

# 下载依赖（利用缓存）
RUN mvn dependency:go-offline -B

# 复制源代码
COPY . .

# 构建应用
RUN mvn clean package -DskipTests -B

# 阶段2：运行时镜像
FROM openjdk:21-jre-slim

# 安装必要的工具和字体
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    curl \
    wget \
    vim \
    tzdata \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建应用用户（安全性考虑）
RUN groupadd -r light && useradd -r -g light light

# 设置工作目录
WORKDIR /app

# 从构建阶段复制jar文件
COPY --from=builder /app/Light.Admin/target/Light.Admin-*.jar app.jar

# 复制配置文件
COPY Light.Admin/src/main/resources/application.yml .
COPY Light.Admin/src/main/resources/application-pro.yml .

# 创建日志目录
RUN mkdir -p /app/logs && chown -R light:light /app

# 切换到应用用户
USER light

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:2034/actuator/health || exit 1

# JVM参数优化
ENV JAVA_OPTS="-server \
               -Xms512m \
               -Xmx1g \
               -XX:MetaspaceSize=256m \
               -XX:MaxMetaspaceSize=512m \
               -XX:+UseG1GC \
               -XX:G1HeapRegionSize=16m \
               -XX:+UseG1MixedGCCountTarget=8 \
               -XX:+DisableExplicitGC \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/app/logs/ \
               -XX:+PrintGCDetails \
               -XX:+PrintGCTimeStamps \
               -XX:+PrintGCDateStamps \
               -Xloggc:/app/logs/gc.log \
               -XX:+UseGCLogFileRotation \
               -XX:NumberOfGCLogFiles=5 \
               -XX:GCLogFileSize=10M"

# 应用参数
ENV APP_OPTS="--spring.profiles.active=pro \
              --server.port=2034 \
              --logging.config=classpath:logback-spring.xml"

# 暴露端口
EXPOSE 2034

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar $APP_OPTS"]