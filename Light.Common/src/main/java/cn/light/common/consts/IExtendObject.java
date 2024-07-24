package cn.light.common.consts;

import java.util.Map;

public interface IExtendObject {
    Integer getId();

    Map<String, Object> getExtend();

    void setExtend(Map<String, Object> stringObjectHashMap);
}
