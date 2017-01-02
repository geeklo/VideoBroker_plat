package com.ivr.plat.send.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.company.util.HttpClientUtil;
import com.ivr.plat.send.Sender;

/**
 * HTTP发送者(返回状态)
 * 
 * @author liugeng
 * 
 */
@Service("httpStatusSender")
public class HttpStatusSender implements Sender<Object, HashMap<String, Object>> {

	private static Logger logger = Logger.getLogger(HttpStatusSender.class);

	public Object getDestination() {
		return null;
	}

	public void setDestination(Object destination) {
	}

	public Object sendObject(Object destination, HashMap<String, Object> msg) throws Throwable {
		return null;
	}

	public Object sendText(String msg) throws Throwable {
		return null;
	}

	public Object sendText(Object destination, String msg) throws Throwable {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object sendObject(HashMap<String, Object> paramMap) throws Throwable {
		int status = 0;
		String url = (String) paramMap.get("url");
		long start = 0;
		long end = 0;
		try {
			start = System.currentTimeMillis();
			if (paramMap.get("method").equals("post")) {
				status = HttpClientUtil.doPostResponseOnlyStatus(url, (Map<String, String>) paramMap.get("param"));
			} else {
				status = HttpClientUtil.doGetResponseOnlyStatus(url);
			}
			end = System.currentTimeMillis();
			if (HttpStatus.SC_OK != status) {
				logger.error("HTTP【" + url + "】失败,状态码:" + status);
			}
		} finally {
			logger.debug("HTTP Use【" + (end - start) + "】MS,Url【" + url + "】,Status【" + status + "】");
		}
		return status;
	}
}
