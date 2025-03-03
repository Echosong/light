package cn.light.common.enums;

public enum ExtendColumnTypeEnum {
    VARCHAR(10, "字符串"),
    INT(20, "整数"),
    FLOAT(30, "小数"),
    DECIMAL(40, "精确小数2位"),
    DATE(50, "日期"),
    DATETIME(60, "精确时间"),
    TEXT(70, "长文本");

    private int code;
    private String message;

    private ExtendColumnTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}