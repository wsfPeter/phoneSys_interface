package com.net.toooen.entity;

public class InterfaceKey {

    /** based param */
    public static String text = "text";

    public static String code = "code";
    public static String message = "message";
    public static String result = "result";

    public static String uuid = "uuid";
    public static String ostype = "ostype";
    public static String msgid = "msgid";
    public static String msg = "msg";
    public static String signature = "signature";

    public static String mobile = "mobile";
    @Deprecated
    public static String msg_mobile = "msg[mobile]";
    @Deprecated
    public static String msg_password = "msg[password]";
    public static String password = "password";

    public static String fwlb = "fwlb";

    /** 是否是debug 模式 */
    public static final Boolean IS_DEBUG = false;

    /** 未知错误 default */
    public static final String RESULT_UNKNOW = "未知错误";
    /** 返回正确 */
    public static final int RESULT_OK = 1;
    /** 返回错误t */
    public static final int RESULT_ERROR = 0;
    /** 未知错误 default */
    /* public static final String RESULT_UNKNOW = "未知错误"; */

}
