package com.study.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

public class HttpRequestSimple {
	private static Logger log = Logger.getLogger(HttpRequestSimple.class);

	private static HttpRequestSimple instance;

	public static HttpRequestSimple getInstance() {
		if (instance == null) {
			instance = new HttpRequestSimple();
		}
		return instance;
	}

	public HttpRequestSimple() {

	}

	public String postSendHttp(String url, String body,String content_type, String charset,
			String correlationID) throws Exception {
		if (url == null || "".equals(url)) {
			log.error("correlationID[" + correlationID
					+ "]request url is empty.");
			return null;
		}
		long start = System.currentTimeMillis();
		HttpClient httpClient = CustomHttpClient.GetHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", content_type + ";charset=" + charset);
		HttpResponse resp = null;
		BufferedReader br = null;
		try {
			StringEntity stringEntity = new StringEntity(body, charset);
			stringEntity.setContentEncoding(new BasicHeader(
					HTTP.CONTENT_ENCODING, charset));
			// 设置请求主体
			post.setEntity(stringEntity);
			// 发起交易
			resp = httpClient.execute(post);
			int ret = resp.getStatusLine().getStatusCode();
			log.info("correlationID[" + correlationID + "][" + url + "]响应["
					+ ret + "]");
			if (ret == HttpStatus.SC_OK) {
				// 响应分析
				HttpEntity entity = resp.getEntity();

				br = new BufferedReader(new InputStreamReader(
						entity.getContent(), charset));
				StringBuffer responseString = new StringBuffer();
				String result = br.readLine();
				while (result != null) {
					responseString.append(result);
					result = br.readLine();
				}
				long end = System.currentTimeMillis();
				log.info("correlationID[" + correlationID + "]请求[" + url
						+ "]消耗时间 " + (end - start) + "毫秒");
				return responseString.toString();
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resp != null) {
					resp.getEntity().getContent().close();
				}
			} catch (IllegalStateException e) {
				log.error(
						"correlationID[" + correlationID + "]异常："
								+ e.getMessage(), e);
			} catch (IOException e) {
				log.error(
						"correlationID[" + correlationID + "]异常："
								+ e.getMessage(), e);
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public String getSendHttp(String url,String content_type, String charset,  String correlationID)
			throws Exception {
		if (url == null || "".equals(url)) {
			log.error("correlationID[" + correlationID
					+ "]request url is empty.");
			throw new Exception("request url is empty");
		}
		long start = System.currentTimeMillis();
		HttpClient httpClient = CustomHttpClient.GetHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", content_type + ";charset=" + charset);
		HttpResponse resp = null;
		BufferedReader br = null;
		try {
			// 发起交易
			resp = httpClient.execute(get);
			int ret = resp.getStatusLine().getStatusCode();
			log.info("correlationID[" + correlationID + "][" + url + "]响应["
					+ ret + "]");
			if (ret == HttpStatus.SC_OK) {
				// 响应分析
				HttpEntity entity = resp.getEntity();

				br = new BufferedReader(new InputStreamReader(
						entity.getContent()));
				StringBuffer responseString = new StringBuffer();
				String result = br.readLine();
				while (result != null) {
					responseString.append(result);
					result = br.readLine();
				}
				long end = System.currentTimeMillis();
				log.info("correlationID[" + correlationID + "]请求[" + url
						+ "]消耗时间 " + (end - start) + "毫秒");
				return responseString.toString();
			}else{
				throw new Exception("HttpStatus["+ret+"]");
			}
		} catch (ConnectTimeoutException cte) {
			throw cte;
		} catch (SocketTimeoutException cte) {
			throw cte;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resp != null) {
					resp.getEntity().getContent().close();
				}
			} catch (IllegalStateException e) {
				log.error(
						"correlationID[" + correlationID + "]异常："
								+ e.getMessage(), e);
			} catch (IOException e) {
				log.error(
						"correlationID[" + correlationID + "]异常："
								+ e.getMessage(), e);
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public String postPramaList(List<NameValuePair> params, String url) {
		HttpClient httpClient = CustomHttpClient.GetHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		BufferedReader br = null;
		try {
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(params,
					HTTP.UTF_8);
			// 设置请求参数
			post.setEntity(formEntiry);
			// 发起交易
			HttpResponse resp = httpClient.execute(post);
			int ret = resp.getStatusLine().getStatusCode();
			if (ret == HttpStatus.SC_OK) {
				// 响应分析
				HttpEntity entity = resp.getEntity();
				br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "UTF-8"));
				StringBuffer responseString = new StringBuffer();
				String result = br.readLine();
				while (result != null) {
					responseString.append(result);
					result = br.readLine();
				}
				return responseString.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public String postPramaList(List<NameValuePair> list, String url,
			String charset) {
		long start = System.currentTimeMillis();
		HttpClient httpClient = CustomHttpClient.GetHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=" + charset);
		BufferedReader br = null;
		HttpResponse resp = null;
		try {
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(list,
					charset);
			// 设置请求参数
			post.setEntity(formEntiry);
			// 发起交易
			resp = httpClient.execute(post);
			log.info("请求[" + url + "] " + resp.getStatusLine());
			int ret = resp.getStatusLine().getStatusCode();
			if (ret == HttpStatus.SC_OK) {
				// 响应分析
				HttpEntity entity = resp.getEntity();
				br = new BufferedReader(new InputStreamReader(
						entity.getContent(), charset));
				StringBuffer responseString = new StringBuffer();
				String result = br.readLine();
				while (result != null) {
					responseString.append(result);
					responseString.append("\n");
					result = br.readLine();
				}
				long end = System.currentTimeMillis();
				log.info("请求[" + url + "]消耗时间 " + (end - start) + "毫秒");
				return responseString.toString();
			} else {
				log.info("retcode:" + ret);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resp != null) {
					resp.getEntity().getContent().close();
				}
			} catch (IllegalStateException e) {
				log.error(e.getMessage(), e);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}
}
