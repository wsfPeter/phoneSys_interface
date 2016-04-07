package com.net.toooen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * @ClassName: ALiDaYuMessageUtil  
 * @Description: 阿里大鱼短信平台 
 * @author wangshaofeng
 * @date 2016年3月13日 上午11:32:56 
 */
public class ALiDaYuMessageUtil {

    private static final Logger Log = LoggerFactory.getLogger(ALiDaYuMessageUtil.class);

    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String appkey = "23325458";
    private static String appSecret = "3b315e3020e12131183e70b023102988";

    public static boolean sendVarifyMessage(Boolean isTest, String mobile, String code,
            int messageType) {

        if (isTest) {
            return true;
        }
        Log.info("ready to sendMsg to {} with verify code : {}", mobile, code);
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

            req.setSmsType("normal");
            req.setSmsFreeSignName("e血液病");
            req.setRecNum(mobile);

            String msg = "";
            if (messageType == 1) {  //注册
                msg = "{\"code\":\"" + code + "\",\"product\":\"e血液病\"}";
                req.setSmsParamString(msg);
                req.setSmsTemplateCode("SMS_5750010");
            } else if (messageType == 2) { //修改密码
            	msg = "{\"code\":\"" + code + "\",\"product\":\"e血液病\"}";
                req.setSmsParamString(msg);
                req.setSmsTemplateCode("SMS_5750008");
            } else if (messageType == 3) { //信息变更
            	msg = "{\"code\":\"" + code + "\",\"product\":\"e血液病\"}";
                req.setSmsParamString(msg);
                req.setSmsTemplateCode("SMS_5750007");
            }
            

            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            Log.info("send verify message , result is : {}", rsp.getBody());

            if (rsp.getResult().getSuccess()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Log.info("发送短信失败", e);
        }
        return false;
    }
    
}
