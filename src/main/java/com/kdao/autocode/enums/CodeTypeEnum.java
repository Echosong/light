package com.kdao.autocode.enums;

/**
 * <p>Title: </p >
 * <p>Description: Controller 用于对生成代表时候是否要覆盖</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021-11-0411:55</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public enum CodeTypeEnum {
    //数据接口
    REPOSITORY,
    //controller操作
    CONTROLLER,
    //DTO详情
    DTO,
    //DTO列表
    LIST_DTO,
    //查询DTO
    QUERY_DTO,
    //视图添加
    ADD_VIEW,
    //视图列表
    LIST_VIEW,

    MAPPER,
    //不需要任何
    NULL

}
