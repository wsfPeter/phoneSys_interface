package com.net.toooen.entity;

/**
 * @author wangShaoFeng
 * @Description: ajax返回数据
 * @date 2015年12月10日
 */
public class Data implements java.io.Serializable {
    private static final long serialVersionUID = 8917607102011609049L;

    /**
     * @Fields code : 状态码.
     */
    private int code;

    /**
     * @Fields msg : 消息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
