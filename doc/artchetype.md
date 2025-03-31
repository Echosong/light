# 脚手架设处理

## 制作 archetype

1. **在根目录的 `pom.xml` 文件中添加插件**

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

2. **生成 Archetype**

   执行以下脚本或在 IDE 中点击 `plugins` 下的相应选项：

    ```shell
    mvn archetype:create-from-project
    ```


3. **部署 Archetype**

   在 `target/generated-sources/archetype` 目录下执行以下命令：

    ```shell
    # 如果只有一个默认 settings.xml 文件，则无需指定
    mvn clean deploy -s "C:/java/settings_ali.xml"
   "C:\Users\L2111018\AppData\Local\Programs\IntelliJ IDEA Ultimate\plugins\maven\lib\maven3\bin\mvn" clean deploy -s "D:\项目\08_离散MES标准产品\settings_hyit.xml"
    ```

   > 发布成功

## 使用 archetype

执行以下命令来生成新项目：

```shell

mvn archetype:generate ^
-s "C:/java/settings_ali.xml" ^
-DarchetypeGroupId=cn.light ^
-DarchetypeArtifactId=light-archetype ^
-DarchetypeVersion=0.0.1-SNAPSHOT ^
-DgroupId=com.ai ^
-DartifactId=ai-demo ^
-DinteractiveMode=false

```
> 注意这里设置 settings 文件
