package com.net.toooen.api.request;

import java.io.Serializable;

/**
 * @ClassName: Request
 * @Description: 请求
 * @author zhujiang
 * @date 2015年3月18日 下午3:07:25
 * 
 */
public class Request implements Serializable {

	private static final long serialVersionUID = -2079985284262869882L;
	
	/**
	 * @Fields REGISTER : 注册
	 */
	public static final String REGISTER = "APP001";

	/**
	 * @Fields LOGIN : 登录
	 */
	public static final String LOGIN = "APP002";
	
	/**
     * @Fields SEND_SMS : 短信下发
     */
    public static final String SEND_SMS = "APP003";

    /**
     * @Fields CHECK_CAPTCHA : 验证码校验
     */
    public static final String CHECK_CAPTCHA = "APP004";
    
    /**
     * @Fields BACK_PASSWORD : 找回密码
     */
    public static final String BACK_PASSWORD = "APP005";
    
    /**
     * @Fields VERSION_UPGRADE : 版本升级
     */
    public static final String VERSION_UPGRADE = "APP006";
    
    /**
     * @Fields SEND_MESSAGE : 发送消息(患者和医生之间)
     */
    public static final String SEND_MESSAGE = "APP007";
    
    /**
     * @Fields UPDATE_MESSAGE : 更新消息
     */
    public static final String UPDATE_MESSAGE = "APP008";
    
    /**
     * @Fields MY_MESSAGE : 我的消息
     */
    public static final String MY_MESSAGE = "APP009";
    
    /**
     * @Fields DELETE_MESSAGE : 删除我的消息
     */
    public static final String DELETE_MESSAGE = "APP010";
    
    /**
     * @Fields REPLACE_MOBILE : 更换手机号
     */
    public static final String REPLACE_MOBILE = "APP011";
    
    /**
     * @Fields MODIFY_USER : 修改用户信息
     */
    public static final String MODIFY_USER = "APP012";
    
    /**
     * @Fields NEWS_LIST : 资讯列表
     */
    public static final String NEWS_LIST = "APP013";
    
    /**
     * @Fields DISEASE_LIST : 疾病列表
     */
    public static final String DISEASE_LIST = "APP014";
    
    /**
     * @Fields EXPERT_LIST : 专家列表
     */
    public static final String EXPERT_LIST = "APP015";
    
    /**
     * @Fields AREA_LIST : 区域列表
     */
    public static final String AREA_LIST = "APP016";
    
    /**
     * @Fields HOSPITAL_LIST : 医院列表
     */
    public static final String HOSPITAL_LIST = "APP017";
    
    /**
     * @Fields RELATION_USER_LIST : 用户关联列表
     */
    public static final String RELATION_USER_LIST = "APP018";
    
    /**
     * @Fields USER_DETAIL : 用户详情
     */
    public static final String USER_DETAIL = "APP019";
    
    /**
     * @Fields RELATION_EXISTS : 关联关系是否存在
     */
    public static final String RELATION_EXISTS = "APP020";
    
    /**
     * @Fields APPLY_CONSULTATION : 申请会诊服务
     */
    public static final String APPLY_CONSULTATION = "APP021";
    
    /**
     * @Fields CONSULTATION_RECORD : 会诊记录列表
     */
    public static final String CONSULTATION_RECORD = "APP022";
    
    /**
     * @Fields CONSULTATION_DETAIL : 会诊记录详情
     */
    public static final String CONSULTATION_DETAIL = "APP023";
    
    /**
     * @Fields CONSULTATION_UPDATE : 会诊记录修改
     */
    public static final String CONSULTATION_UPDATE = "APP024";
    
    /**
     * @Fields TEST_PROJECT_LIST : 检测项目列表
     */
    public static final String TEST_PROJECT_LIST = "APP025";
    
    /**
     * @Fields TEST_PROJECT_DETAIL : 检测项目详情
     */
    public static final String TEST_PROJECT_DETAIL = "APP026";
    
    /**
     * @Fields APPLY_TEST_PROJECT : 申请检测项目
     */
    public static final String APPLY_TEST_PROJECT = "APP027";
    
    /**
     * @Fields GOOD_ADDR_LIST : 我的收件列表
     */
    public static final String GOOD_ADDR_LIST = "APP028";
    
    /**
     * @Fields GOOD_ADDR_ADD : 添加我的收件地址
     */
    public static final String GOOD_ADDR_ADD = "APP029";
    
    /**
     * @Fields GOOD_ADDR_EDIT : 编辑我的收件地址
     */
    public static final String GOOD_ADDR_EDIT = "APP030";
    
    /**
     * @Fields GOOD_ADDR_DELETE : 删除我的收件地址
     */
    public static final String GOOD_ADDR_DELETE = "APP031";
    
    /**
     * @Fields REPORT_SELECT : 报名查询
     */
    public static final String REPORT_SELECT = "APP032";
    
    /**
     * @Fields DRIGS_SEEK_LIST : 药品求助列表
     */
    public static final String DRIGS_SEEK_LIST = "APP033";
    
    /**
     * @Fields DRIGS_LIST : 药品列表
     */
    public static final String DRIGS_LIST = "APP034";
    
    /**
     * @Fields DRIGS_DETAIL : 药品详情
     */
    public static final String DRIGS_DETAIL = "APP035";
    
    /**
     * @Fields DRIGS_SEEK_ADD : 药品求助添加
     */
    public static final String DRIGS_SEEK_ADD = "APP036";
    
    /**
     * @Fields DRIGS_SEEK_DETAIL : 药品求助详情
     */
    public static final String DRIGS_SEEK_DETAIL = "APP037";
    
    /**
     * @Fields MY_RELATION_NUM : 我的关联人数
     */
    public static final String MY_RELATION_NUM = "APP038";
    
    /**
     * @Fields NEWS_SELECTNUM : 资讯查看次数
     */
    public static final String NEWS_SELECTNUM = "APP039";
    
    /**
     * @Fields EXPERT_DETAIL : 专家详情
     */
    public static final String EXPERT_DETAIL = "APP040";
    
    /**
     * @Fields TP_HOSPITAL : 检测项目省份医院
     */
    public static final String TP_HOSPITAL = "APP041";
    
    /**
     * @Fields APPLY_TESTPROJECTLIST : 我的检测列表
     */
    public static final String APPLY_TESTPROJECTLIST = "APP042";
    
    /**
     * @Fields JOIN_TP_FOLDER : 加入检测项目申请夹
     */
    public static final String JOIN_TP_FOLDER = "APP043";
    
    /**
     * @Fields TP_FOLDERLIST : 检测项目申请夹列表
     */
    public static final String TP_FOLDERLIST = "APP044";
    
    /**
     * @Fields DEL_TP_FOLDER : 删除检测项目申请夹
     */
    public static final String DEL_TP_FOLDER = "APP045";
    
    /**
     * @Fields USER_LIST : 用户列表
     */
    public static final String USER_LIST = "APP046";
    
    /**
     * @Fields REPORT_USER_LIST : (患者列表)报告查询
     */
    public static final String REPORT_USER_LIST = "APP047";
    
    /**
     * @Fields HOSP_USER_LIST : 根据医院搜索医生
     */
    public static final String HOSP_USER_LIST = "APP048";
    
    /**
     * @Fields DISEASE_USER_LIST : 根据疾病搜索医生
     */
    public static final String DISEASE_USER_LIST = "APP049";

	/**
	 * @Fields msgId : 消息类型
	 */
	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
