package com.net.toooen.api.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ResultResponse
 * @Description: 带实体内容的数据结构
 * @author zhujiang
 * @date 2015年3月23日 下午4:11:47
 * 
 */
public class ResultResponse extends Response {

    private static final long serialVersionUID = -6850286896389538501L;

    private Map<String, Object> resultMap = new HashMap<String, Object>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

}
