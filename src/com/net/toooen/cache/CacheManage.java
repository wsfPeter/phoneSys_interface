package com.net.toooen.cache;

import java.net.URL;
import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.net.toooen.util.SystemUtil;

/**
 * 可扩展的功能：当chche到内存溢出时必须清除掉最早期的一些缓存对象，这就要求对每个缓存对象保存创建时间
 * 
 * @author cozone 2014-12-11
 * @version 1.0
 */
public class CacheManage {

    static final Logger Log = LoggerFactory.getLogger(CacheManage.class);

    static URL url = CacheManage.class.getResource("/ehcachetoooen.xml");

    static CacheManager manager = CacheManager.create(url);

    // 验证码缓存 10分钟有效
    static Cache varifyCache = manager.getCache("varify");

    static Cache phonegapCache = manager.getCache("phonegap");
    static Cache phonedayCache = manager.getCache("phoneday");

    /** 获取getPhonegap中的code */
    public static boolean getPhonegap(String mobile) {
        if (phonegapCache != null) {
            phonegapCache.acquireReadLockOnKey(mobile);
            try {
                Element e = phonegapCache.get(mobile);
                if (e != null) {
                    Log.info("每条验证码发送间隔为60秒 {}", mobile);
                    return false;
                } else {
                    e = new Element(mobile, new Date());
                    phonegapCache.put(e);
                    return true;
                }
            } finally {
                phonegapCache.releaseReadLockOnKey(mobile);
            }
        }
        return false;
    }

    /** 获取getPhonegap中的code */
    public static boolean getPhonedayCache(String mobile) {
        if (phonedayCache != null) {
            phonedayCache.acquireReadLockOnKey(mobile);
            try {
                Element e = phonedayCache.get(mobile);
                if (e != null) {
                    Log.info("{} " + SystemUtil.getDateNow() + "已发送短信 {} 次", mobile,
                            e.getHitCount());
                    if (e.getHitCount() > 4) {
                        return false;
                    }
                    return true;
                } else {
                    e = new Element(mobile, 1);
                    Log.info("{} " + SystemUtil.getDateNow() + "已发送短信 0 次", mobile);
                    phonedayCache.put(e);
                    return true;
                }
            } finally {
                phonedayCache.releaseReadLockOnKey(mobile);
            }
        }
        return false;
    }

    /** 获取cache中的code */
    public static String getCacheVerifyCode(String mobile) {
        Element element = null;
        if (varifyCache != null) {
            // Read lock get data
            varifyCache.acquireReadLockOnKey(mobile);
            try {
                element = varifyCache.get("verifycode_" + mobile);
                if (element != null) {
                    String code = element.getObjectValue().toString();
                    Log.info("getCacheCode get the cache : {},{}", mobile, code);
                    return code;
                }
            } finally {
                varifyCache.releaseReadLockOnKey(mobile);
            }
        }
        return null;
    }

    /** removeVerifyCode */
    public static String removeVerifyCode(String mobile) {
        if (varifyCache != null) {
            // Read lock get data
            varifyCache.acquireWriteLockOnKey(mobile);
            try {
                varifyCache.remove("verifycode_" + mobile);
                Log.info("removeVerifyCode {}", mobile);
            } finally {
                varifyCache.releaseWriteLockOnKey(mobile);
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        saveOrUpdateVerifyCode("123", "1234");
        Log.info("" + getCacheVerifyCode("123")); // 1234
        Thread.sleep(1000 * 2);
        Log.info(getCacheVerifyCode("123")); // 1234
        Thread.sleep(1000 * 5);
        Log.info(getCacheVerifyCode("123")); // null
        Thread.sleep(1000 * 7);
        Log.info(getCacheVerifyCode("123")); // null
        manager.shutdown();
    }

    /** saveOrUpdateVerifyCode */
    public static String saveOrUpdateVerifyCode(String mobile, String code) {
        if (varifyCache != null) {
            varifyCache.acquireWriteLockOnKey(mobile);
            try {
                Element element = new Element("verifycode_" + mobile, code);
                varifyCache.put(element);
                Log.info("saveOrUpdateVerifyCode {},{} ", mobile, code);
            } finally {
                varifyCache.releaseWriteLockOnKey(mobile);
            }
        }
        return null;
    }

    /**
     * close the cache.
     */
    public static void closeCache() {
        manager.shutdown();
    }

    public static void clearCache() {
        manager.clearAll();
    }

}