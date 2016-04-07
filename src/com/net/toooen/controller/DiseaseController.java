package com.net.toooen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**     
* @author wangShaoFeng 
* @Description: 疾病控制器     
* @date 2016年3月22日   
*/ 
@Controller
@RequestMapping("/disease")
public class DiseaseController extends BaseController {

	/**  
	* 查看疾病背景页面
	* @param @throws Exception
	* @return String 
	*/
	@RequestMapping(value = "/selectBg.do")
    public String selectBg(Long id, ModelMap model) throws Exception {
        Record diseaseRecord = Db.findById("disease", "id", id);
        model.put("diseaseName", diseaseRecord.getStr("diseaseName"));
        model.put("background", diseaseRecord.getStr("background"));
        return "/disease/diseaseBg.jsp";
    }
	
	/**  
    * 查看疾病诊疗规范
    * @param @throws Exception
    * @return String 
    */
	@RequestMapping(value = "/selectSd.do")
	public String selectSd(Long id, ModelMap model) throws Exception {
	    Record diseaseRecord = Db.findById("disease", "id", id);
	    model.put("diseaseName", diseaseRecord.getStr("diseaseName"));
	    model.put("standard", diseaseRecord.getStr("standard"));
	    return "/disease/diseaseSd.jsp";
	}

}