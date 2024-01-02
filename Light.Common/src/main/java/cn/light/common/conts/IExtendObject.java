package cn.light.common.conts;

import java.util.Map;

public interface IExtendObject {
    Integer getId();

    Map<String, Object> getExtend();

    void setExtend(Map<String, Object> stringObjectHashMap);
}
