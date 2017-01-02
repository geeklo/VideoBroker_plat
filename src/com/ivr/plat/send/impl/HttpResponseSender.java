package com.ivr.plat.send.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.company.util.HttpClientUtil;
import com.ivr.plat.send.Sender;

/**
 * HTTP发送者(返回信息)
 * 
 * @author liugeng
 * 
 */
@Service("httpResponseSender")
public class HttpResponseSender implements Sender<Object, HashMap<String, Object>> {

	private Logger logger = Logger.getLogger(getClass());

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
		String resp = "";
		String charset = (String) paramMap.get("charset");
		if (charset == null || "".equals(charset)) {
			charset = "GBK";
		}
		String url = (String) paramMap.get("url");
		long start = 0;
		long end = 0;
		try {
			start = System.currentTimeMillis();
			if (paramMap.get("method").equals("post")) {
				resp = HttpClientUtil.doPostResponse(url, (Map<String, String>) paramMap.get("param"));
			} else {
				resp = HttpClientUtil.doGetResponse(url, charset);
			}
			end = System.currentTimeMillis();
		} finally {
			logger.debug("HTTP Use【" + (end - start) + "】MS,Url【" + url + "】,resp【" + resp + "】");
		}
		return resp;
	}
}
