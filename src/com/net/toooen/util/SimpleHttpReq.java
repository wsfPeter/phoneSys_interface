package com.net.toooen.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * HTTP请求实现
 */
public class SimpleHttpReq {

    public static final String APPLICATION_JSON = "application/json;utf-8";

    @SuppressWarnings("finally")
	public static String request(String url){
    	HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		HttpMethodParams params = null;
		StatusLine statusLine = null;
		String response = null;
		try {
			method.addRequestHeader("Content-Type", "text/html;charset=UTF-8");
			//method.addParameter(paramName, jsonParamValue);
			params = method.getParams();
			params.setContentCharset("utf-8");
			httpClient.executeMethod(method);
			
			statusLine = method.getStatusLine();
			if (statusLine != null) {
				if (statusLine.getStatusCode() == 200) {
					response = method.getResponseBodyAsString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
			return response;
		}
    }

}
