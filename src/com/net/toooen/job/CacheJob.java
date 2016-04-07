package com.net.toooen.job;

import org.springframework.stereotype.Component;

/**
 * 定时清除缓存
 * @author wangShaoFeng
 *
 */
@Component
public class CacheJob {
	//private static final Logger logger = LoggerFactory.getLogger(CacheJob.class);
	
    /*@Scheduled(cron = "0 0 0 * * ?")
    public void clearData() {
    	logger.info(SystemUtil.getDateTimeNow()+ " : " + "开始清除缓存数据。");
    	try {
		} catch (Exception e) {
			logger.error("清除缓存失败", e);
		}
    }*/

}