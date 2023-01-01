package com.kdao.light.common.enums;

/**
 * <p>Title: light-controller</p >
 * <p>Description: StationEnum</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2022-12-14 21:47 </p >
 *
 * @author : 二胡子
 * @version :1.0.0
 */
 public  enum StationAlertTypeEnum  implements BaseEnum{
    /**
     * 报警
     */
    ON(1, "开启报警"),
    OFF(0, "关闭报警");

    private final Integer code;
    private final String name;

    StationAlertTypeEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }



    @Override
    public String getName() {
        return this.name;
    }
}
