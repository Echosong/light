package cn.light.common.exception;

/**
 * <p>Title: </p >
 * <p>Description: </p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002720:40</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
public interface BaseErrorCode {
    /**
     * 消息
     * @return 消息
     */
    String getMessage();

    /**
     * 编码
     * @return 编码
     */
    Integer getCode();
}
