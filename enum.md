## 关于枚举改造

> 根据项目中方便处理规则，对于类似状态这种东西，以前都是存tinyint 类型数据库字段， 但是每次查询总要去翻找
> 具体tinyint 对应的意义，比较不方便。 针对一下情况枚举最有些改进

```java
@Getter
public enum ResultEnum {
    /**
     * 字段信息
     */
    SUCCESS(200, "成功"),
    ERROR(1, "失败");

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return this.code;
    }

    @JsonValue
    public String getMessage() {return  this.message;}
}

```
1. 枚举类实现`Serializable`接口 message 直接做完 前端接受的数据
2. message 同时做完 数据库存储字段
3. 至于为啥要错一个code 就是用来做 一些业务判断，比如我想拿状态大于 数据库状态大于某某的