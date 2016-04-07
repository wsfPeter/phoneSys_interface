package com.net.toooen.service;

import com.net.toooen.api.request.user.DiseaseUserListRequest;
import com.net.toooen.api.request.user.ExpertDetailRequest;
import com.net.toooen.api.request.user.ExpertListRequest;
import com.net.toooen.api.request.user.HospUserListRequest;
import com.net.toooen.api.request.user.ModifyUserInfoRequest;
import com.net.toooen.api.request.user.MyRelationNumRequest;
import com.net.toooen.api.request.user.RelationExistsRequest;
import com.net.toooen.api.request.user.UserDetailRequest;
import com.net.toooen.api.request.user.UserListRequest;
import com.net.toooen.api.request.user.UserRelationListRequest;
import com.net.toooen.api.response.Response;

public interface UserService {


    /**
     * 修改用户信息
     * 
     * <p>
     * DEMO: text={"msgId":"APP012","paramMap":{"更新字段":"更新字段的值","更新字段":"更新字段的值"},"userId":"用户ID"}
     * <p>
     * R:{"message":"成功。"}
     * 
     * @param request
     * @param Response
     */
	public Response modifyUserInfo(ModifyUserInfoRequest req);

	/**
     * 专家列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP015",
                   "expertBeilType":专家会诊类型;1病例专家,2病理专家,3病例和病理专家,4普通医生,
                   "keyword":"关键字(姓名搜索)",
                   "pageIndex":当前页
                }
     * <p>
     * R:{"resultMap":{
			"expertLists":[ 
			{
				"id":专家ID,
				"name":"姓名",
				"txpic":"头像",
				"workunit":"工作单位"
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response expertList(ExpertListRequest req);

	/**
     * 用户列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP046",
     			   "userId":用户id,
                   "userType":0(获取患者列表),1(获取医生列表),
                   "isRelation":是否关联,
                   "keyword":"关键字(用户姓名搜索)",
                   "pageIndex":当前页
               }
     * <p>
     * R:{"resultMap":{
			"userLists":[ 
			{
				"id":用户ID,
				"name":"姓名",
				"sex":性别0为男,1为女,
				"age":年龄,
				"mobile":"手机号",
				"txpic":"头像",
				"workunit":"工作单位",
				"disease":"患者所得疾病"
				
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response userList(UserListRequest req);

	/**
     * 用户详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP019","userType":用户类型;0为患者,1为医生,"userId":用户ID}
     * <p>
     * R:{"resultMap":{
			"userInfo": 
			{ 
				"id":"1", 
				"username":"用户名",
				"name":"真实姓名", 
		 		"nickname":"昵称",
	 			"idcard":"身份证号",
				"mobile":"手机号",
				"age":"年龄", 
				"txpic":"头像",
				"sex":"性别", 
				"email":"邮箱",
				"birth":"出生日期",
				"address":"住址",
				"hometown":"籍贯",
				"ispush":是否接受推送消息,
				"brief":"个人简介",
				"disease":"患者所得疾病,医生擅长疾病;"x病","s病"",
				"deptphone":"患者固定电话,医生为科室电话",
					 
				// 医生详情返回时,加入这些字段 
			    "title":"职称",
				"workunit":"工作单位",
				"curetime":"会诊时间",
				"diseasedesc":"擅长疾病描述",
				"expertbeiltype":"专家会诊类型;1病例专家,2病理专家,3病例和病理专家,4普通医生",
				"department":"科室",
				"doctorcode":"医师资格证编号",
				"works":"荣誉及著作"
		 
		       // 患者详情时,加入这些字段 
	 		   "medihistory":"病史 ",
	           "allergydrug":"过敏药物",
		       "firstcontact":"第一联系人",
	           "firstcontactmobile":"第一联系人电话" 
	       }
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response userDetail(UserDetailRequest req);

	 /**
     * 用户之间关联是否存在
     * 
     * <p>
     * DEMO: text={"msgId":"APP020","patientId":患者ID,"doctorId":医生ID}
     * <p>
     * R:{"resultMap":{
            "result":"true(已关联)|false(未关联)"
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response relationExists(RelationExistsRequest req);


	 /**
     * 我的关联人数
     * 
     * <p>
     * DEMO: text={"msgId":"APP038","userId":用户ID,"userType":0为患者 ,1为医生}
     * <p>
     * R:{"resultMap":{
            "num":关联人数
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response myRelationNum(MyRelationNumRequest req);

	

	/**
     * 专家详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP040","expertId":专家ID}
     * <p>
     * R:{"resultMap":{
			"expert": 
			{ 
				"name":"真实姓名", 
				"expertbeiltype":"专家会诊类型;1病例专家,2病理专家,3病例和病理专家,4普通医生",
				"txpic":"头像",
				"workunit":"工作单位",
				"department":"科室",
				"deptphone":"科室电话",
			    "title":"职称",
				"curetime":"会诊时间",
				"disease":"患者所得疾病,医生擅长疾病;"x病","s病"",
				"diseasedesc":"擅长疾病描述",
				"doctorcode":"医师资格证编号",
				"brief":"个人简介"
	       }
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response expertDetail(ExpertDetailRequest req);

	/**
     * 用户关联列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP018",
                   "userType":用户类型;如果为医生获取的则是患者列表,如果为患者获取的则是医生列表,
     			   "userId":用户ID,
                   "keyword":"关键字(用户姓名搜索)",
                   "pageIndex":当前页
               }
     * <p>
     * R:{"resultMap":{
			"userLists":[ 
			{
				"id":用户ID,
				"name":"姓名",
				"sex":性别0为男,1为女
				"mobile":"手机号",
				"txpic":"头像",
				"workunit":"工作单位",
				"disease":"患者所得疾病"
				
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response relationUserList(UserRelationListRequest req);

	/**
     * 根据医院获取用户列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP048",
                   "userType":用户类型;0为患者,1为医生,
                   "hospId":医院id,
                   "pageIndex":当前页
               }
     * <p>
     * R:{"resultMap":{
            "userLists":[ 
            {
                "id":用户ID,
                "name":"姓名",
                "sex":性别0为男,1为女,
                "age"：年龄,
                "mobile":"手机号",
                "txpic":"头像",
                "workunit":"工作单位",
                "disease":"患者所得疾病"
                
            }...
          ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response hospUserList(HospUserListRequest req);

    /**
     * 根据疾病获取用户列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP048",
                   "userType":用户类型;0为患者,1为医生,
                   "diseaseName":"疾病名称",
                   "pageIndex":当前页
               }
     * <p>
     * R:{"resultMap":{
            "userLists":[ 
            {
                "id":用户ID,
                "name":"姓名",
                "sex":性别0为男,1为女,
                "age"：年龄,
                "mobile":"手机号",
                "txpic":"头像",
                "workunit":"工作单位",
                "disease":"患者所得疾病"
                
            }...
          ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response diseaseUserList(DiseaseUserListRequest req);


}
