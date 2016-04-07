package com.net.toooen.service;

import com.net.toooen.api.request.testProject.ApplyTestProjectListRequest;
import com.net.toooen.api.request.testProject.ApplyTestProjectRequest;
import com.net.toooen.api.request.testProject.DelTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.JoinTestProjectFolderRequest;
import com.net.toooen.api.request.testProject.ReportSelectRequest;
import com.net.toooen.api.request.testProject.ReportUserListRequest;
import com.net.toooen.api.request.testProject.TestProjectDetailRequest;
import com.net.toooen.api.request.testProject.TestProjectFolderListRequest;
import com.net.toooen.api.request.testProject.TestProjectListRequest;
import com.net.toooen.api.response.Response;

public interface TestProjectService {

	/**
     * 检测项目列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP025","hospId":医院ID,"parentMenuId",父菜单ID,"keyword":"关键字(项目名称)搜索","pageIndex":当前页}
     * <p>
     * R:{"resultMap":{
            //有子菜单时,返回 
			"testProjectList":[ 
			{
			    "id":子菜单ID
				"childMenu":"子菜单名称",
				"testProjects":[
					{
					"id":id,
					"tpname":"检测项目名称",
					}
			     ]
			}...
		  ]
		  
		  //没有子菜单和搜索时,返回 
		  "testProjects":[
				{
				"id":id,
				"tpname":"检测项目名称",
				}
		     ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response testProjectList(TestProjectListRequest req);

	/**
     * 检测项目详情
     * 
     * <p>
     * DEMO: text={"msgId":"APP026","id":id}
     * <p>
     * R:{"resultMap":{
			"testProject":
			{
				"id":id,
				"tpname":"检测项目名称",
				"reportdate":"报告时间",
				"ciinicalsign":"临床意义",
				"price":"价格",
				"readtype":报告解读类型:1为图片,2为PDF,
				"reposrread":["XX","XX"]报告解读,
				"sampleto":"标本要求"
			}
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response testProjectDetail(TestProjectDetailRequest req);

	/**
     * 申请检测项目
     * 
     * <p>
     * DEMO: text={
      			   "msgId":"APP027",
                   "userId":用户Id,
                    "patientName":"患者名称"(必填)
			        "hospId":项目所在的医院ID,(必填)
			        "tpfIds":[1,2,3]申请夹,(必填)
			        "gsDoctor":"收件医生",(必填)
					"gsHospital":"收件医院",(必填)
					"gsTitle":"收件科室",(必填)
					"gsMobile":"收件电话",(必填)
			        "totalPrice":总价(必填)
             }
     * <p>
     * R:
        {"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response applyTestProject(ApplyTestProjectRequest req);

	/**
     * 我的检测列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP042","userId":用户ID,"keyword":"关键字(患者名称)搜索","pageIndex":当前页 }
     * <p>
     * R:{"resultMap":{
            "applyTestProjectList":[
                 {
                    "id":id,
                    "patientname":"患者名称",
                    "totalprice":总价,
                    "gshospital":"收件医院"
                    "gstitle":"收件科室",
                    "gamoblie":"联系电话",
                    "gsdoctor":"收件人名称",
                    "createdate":"创建时间",
                    "testProjects":[
                         {
                          "id":id,
                          "tpname":"检测项目名",
                          "price":"价格",
                          "sampleTo":"标本要求"
                        },...
                    ]
                      
                 },
                  
               ...
             ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response applyTestProjectList(ApplyTestProjectListRequest req);

	/**
     * 删除检测项目申请夹
     * 
     * <p>
     * DEMO: text={"msgId":"APP043","id":检测项目申请夹ID}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response delTpFolder(DelTestProjectFolderRequest req);

	/**
     * 检测项目申请夹列表
     * 
     * <p>
     * DEMO: text={"msgId":"APP044","userId":用户ID,"hospId":医院ID }
     * <p>
     * R:{"resultMap":{
            "tpFolderList":[
             {
	              "id":检测项目申请夹ID,
	              "tpid":检测项目ID,
	              "tpname":"检测项目名",
	              "price":"价格",
	              "sampleto":"标本要求"
             },
               ...
             ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response tpFolderlist(TestProjectFolderListRequest req);

	/**
     * 加入检测项目申请夹
     * 
     * <p>
     * DEMO: text={"msgId":"APP045","userId":用户ID,"tpIds":[1,2,3]检测项目ID;}
     * <p>
     * R:{"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response joinTpFolder(JoinTestProjectFolderRequest req);
	
	/**
     * 报告查询
     * 
     * <p>
     * DEMO: text={"msgId":"APP032","userId":用户ID,"patientName":"患者名称","pageIndex":当前页 }
     * <p>
     * R:{"resultMap":{
            "reportList":[
                 {
                    "id":id,
                    "reportresult":"报告结果",
                    "tpname":"检测项目名称",
                    "reportfile":"报告文件路径",
                    "createdate":"申请时间字符串"    
                 },
               ...
             ]
        },"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
    public Response reportSelect(ReportSelectRequest req);
    
    /**
     * 检测省份的医院和菜单
     * 
     * <p>
     * DEMO: text={"msgId":"APP041"}
     * <p>
     * R:{"resultMap":{
			"areaLists":[ 
			{
				"id":区域ID,
				"name":"名称",
				"fullname":"全名"
				"hospitallist":[
				   "id":医院ID,
				   "hospitalname":"医院名称",
				   "hospitallogo":"医院LOGO图片地址",
				   "menus":[
				      "id":菜单ID,
				      "menuname":菜单名称
				   ]
				]
			}...
		  ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response testProjectHspital();

	/**
     * 患者列表搜索(报告查询)
     * 
     * <p>
     * DEMO: text={"msgId":"APP047","userId":用户ID,"keyword":"关键字(患者名称)搜索"}
     * <p>
     * R:{"resultMap":{
            "patientList":[
                 {
                    "patientname":"患者名称"   
                 },
               ...
             ]
		},"code":"0","message":"成功。"}
     * @param request
     * @param Response
     */
	public Response reportUserLis(ReportUserListRequest req);

}
