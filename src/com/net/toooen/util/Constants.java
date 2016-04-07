package com.net.toooen.util;


public class Constants {
	
    /**  
     * @Fields SERVER_PATH : 服务器路径(测试) 
     */ 
     //public static String SERVER_PATH = "http://www.blood-xinq.com/";
     
     /**  
      * @Fields SERVER_PATH : 服务器路径(正式) 
      */ 
      public static String SERVER_PATH = "http://123.56.198.183/";
     
     /**  
      * @Fields SERVER_PATH : 服务器路径(本地) 
      */ 
     // public static String SERVER_PATH = "http://localhost:8081/";
     
     /**
      * @Fields SELECT_NEWS : 查看新闻地址
      */
     public static String SELECT_NEWS = SERVER_PATH + "toooen_interface/news/select.do?id=";

     /**
      * @Fields DISEASE_BG : 查看疾病背景页面
      */
     public static String DISEASE_BG = SERVER_PATH + "toooen_interface/disease/selectBg.do?id=";
     
     /**
      * @Fields DISEASE_SD : 查看疾病诊疗规范页面
      */
     public static String DISEASE_SD = SERVER_PATH + "toooen_interface/disease/selectSd.do?id=";
	
    
    /**
     * @Fields GET_FILE_UPLOAD_PATH : 获取文件上传地址
     */
    public static String GET_FILE_UPLOAD_PATH = SERVER_PATH + "resources/";
    
    /**
     * @Fields FILE_UPLOAD_PATH : 文件上传地址(测试)
     */
    //public static String FILE_UPLOAD_PATH = "D:\\server\\nginx-1.7.9\\resources\\";
   
    /**
     * @Fields FILE_UPLOAD_PATH : 文件上传地址(正式)
     */
    public static String FILE_UPLOAD_PATH = "E:\\server\\nginx-1.7.9\\resources\\";
    
    
    /**
     * @Fields UPLOAD_TXPIC : 用户头像地址
     */
    public static String UPLOAD_TXPIC = "userTxPic/";
    
    /**
     * @Fields UPLOAD_CONSULTATION : 会诊资料地址
     */
    public static String UPLOAD_CONSULTATION = "consultation/";

    /**
     * @Fields CAPTCHA_TYPE_REGISTER : 注册验证码
     */
    public static final Integer CAPTCHA_TYPE_REGISTER = 1;

    /**
     * @Fields CAPTCHA_RETRIEVE_PASSWORD : 修改密码
     */
    public static final Integer CAPTCHA_RETRIEVE_PASSWORD = 2;
    
    /**
     * @Fields CAPTCHA_RESEND : 修改变更
     */
    public static final Integer CAPTCHA_RESEND = 3;
    
    /**  
    * @Fields PAGE_NUM : 分页返回数量 
    */ 
    public static final Integer PAGE_NUM = 20;

}
