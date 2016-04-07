package com.net.toooen.jfinal;

import java.util.HashMap;

import com.alibaba.druid.filter.stat.StatFilter;
import com.huilet.util.FileUtil;
import com.huilet.util.xml.ReadXml;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 * 
 * @author yuanhuaihao company huilet 2013-3-12
 */
public class ToooenConfig extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        MyRoutesUtil.add(me);
    }

    /**
     * 配置插件
     */
    @SuppressWarnings("rawtypes")
    public void configPlugin(Plugins me) {
        // 配置C3p0数据库连接池插件
        ReadXml xml = new ReadXml(FileUtil.getRootPath() + "/WEB-INF/classes/db.xml");

        HashMap xmlMap = xml.getXmlData("database", "id");
        HashMap param = (HashMap) xmlMap.get("main");

        // 添加自动绑定model与表插件
        DruidPlugin druidPlugin = new DruidPlugin(param.get("url").toString(), param
                .get("username").toString(), param.get("password").toString());
        druidPlugin.addFilter(new StatFilter());
        me.add(druidPlugin);

        AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin,
                TableNameStyle.LOWER);
        autoTableBindPlugin.setShowSql(true);
        autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
        me.add(autoTableBindPlugin);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {

    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new JFinalMy());
    }

}
