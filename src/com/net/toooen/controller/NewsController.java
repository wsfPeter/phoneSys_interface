package com.net.toooen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.toooen.util.Constants;

/**
 * @ClassName: NewsController
 * @Description: 新闻控制器
 * @author wangshaofeng
 * @date 2016年3月5日 下午4:29:21
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@RequestMapping(value = "/select.do")
    public String edit(Long id, ModelMap model) throws Exception {
        Record newsRecord = Db.findById("news", "id", id);
        model.put("title", newsRecord.getStr("title"));
        model.put("content", newsRecord.getStr("content"));
        model.put("digest", newsRecord.getStr("digest"));
        model.put("titleImg", Constants.GET_FILE_UPLOAD_PATH + newsRecord.getStr("titleImage"));
        return "/news/newsSelect.jsp";
    }

}