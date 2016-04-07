package com.net.toooen.api.response;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 243897000190441468L;
    /**
     * @Fields SUCCESS : 成功
     */
    public static final String CODE_SUCCESS = "0";

    /**
     * @Fields CODE_FAIL : 失败（正常业务处理）
     */
    public static final String CODE_FAIL = "1";

    /**
     * @Fields PARAM_ERROR : 参数错误
     */
    public static final String CODE_ERROR_PARAM = "2";

    /**
     * @Fields SERVIE_ERROR : 服务器异常
     */
    public static final String CODE_ERROR_SERVER = "3";

    /**
     * @Fields INTERFACE_NOT_FOUND : 接口不存在
     */
    public static final String CODE_ERROR_NOT_METHOD = "4";
    
    /**
     * 配置未找到
     */
    public static final String CONFIG_NOT_FOUNT = "1001";
    
    /**
     * 记录不存在
     */
    public static final String RECORD_NOT_EXIST = "1002";

    public static final Response ERROR_NOT_METHOD = new Response(Response.CODE_ERROR_NOT_METHOD,
            "接口不存在。");
    public static final Response ERROR_PARAM = new Response(Response.CODE_ERROR_PARAM, "参数错误。");
    public static final Response ERROR_SERVER = new Response(Response.CODE_ERROR_SERVER, "服务器异常。");
    public static final Response SUCCESS = new Response(Response.CODE_SUCCESS, "成功。");

    public static Response paramError(String message) {
        return new Response(CODE_ERROR_PARAM, message);
    }

    /**
     * @Fields code :状态码 默认为成功
     */
    private String code = CODE_SUCCESS;

    /**
     * @Fields message :消息内容
     */
    private String message = "成功。";

    public Response() {
        super();
    }

    public Response(String code) {
        this.code = code;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
