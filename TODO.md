1. 导出时候 需要处理数据列

# 制作 archetype

1. 根目录 pom 添加插件

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-archetype-plugin</artifactId>
                <version>3.2.0</version>
            </plugin>
        </plugins>
    </build>
```

2、生成 archeType 执行脚本或者ide 点击 plugins 下的

```shell

mvn archetype:create-from-project

```

3、 目录下 target\generated-sources\archetype 执行

```shell
# 需要指定setting 如果只有一个默认setting 就无所谓
 mvn clean deploy  -s "C:/java/settings_ali.xml" 

```
> 发布成功

## 使用 archetype

```shell

mvn archetype:generate ^
-s "C:/java/settings_ali.xml" ^
-DarchetypeGroupId=cn.light ^
-DarchetypeArtifactId=light ^
-DarchetypeVersion=0.0.1-SNAPSHOT ^
-DgroupId=com.hn1024 ^
-DartifactId=framework-demo ^
-DinteractiveMode=false

```
