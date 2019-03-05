package com.study.demo.utils;

/**
 * 
 */

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 线程安全的HttpCLient,单例模式，支持http、https协议
 * 
 * @author linys
 */
public class CustomHttpClient{
    private static Logger log = LoggerFactory.getLogger(CustomHttpClient.class);
    private static HttpClient customHttpClient;
   //这定义了从ConnectionManager管理的连接池中取出连接的超时时间ms。
    //这定义了通过网络与服务器建立连接的超时时间。Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间ms
    //这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间ms
    private static final int MaxTotal = Integer.MAX_VALUE; // 连接池最大连接数
	private static final int MaxPerRoute = Integer.MAX_VALUE; // 每个路由默认最大连接数

    private CustomHttpClient()
    {
    }

    public static synchronized HttpClient GetHttpClient() {

        if (customHttpClient == null) {
            return httpClientInstance();
        }
        return customHttpClient;
    }

    private static HttpClient httpClientInstance() {

        KeyStore trustStore;
        SSLSocketFactory sf = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            log.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        } catch (UnrecoverableKeyException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (CertificateException e) {
            log.error(e.getMessage(), e);
        } catch (KeyStoreException e) {
            log.error(e.getMessage(), e);
        }
       ;
        int SOCKET_TIME_OUT =Integer.parseInt(SpringConfigUtil.getProperties("socket_time_out"));
        int CONN_TIME_OUT = Integer.parseInt(SpringConfigUtil.getProperties("conn_time_out"));
        int CONN_POOL_TIME_OUT = Integer.parseInt(SpringConfigUtil.getProperties("conn_pool_time_out"));
        log.info("初始化 连接的超时时间为:{}毫秒,连接超时为:{}毫秒,请求超时时间为:{}毫秒",CONN_POOL_TIME_OUT,CONN_TIME_OUT,SOCKET_TIME_OUT);
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        /* 从连接池中取连接的超时时间 */
        ConnManagerParams.setTimeout(params, CONN_POOL_TIME_OUT);
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(params, CONN_TIME_OUT);
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(params, SOCKET_TIME_OUT);
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", sf, 443));
       
        PoolingClientConnectionManager conMgr = new PoolingClientConnectionManager(schReg);
        conMgr.setMaxTotal(MaxTotal); // 连接池最大连接数
        conMgr.setDefaultMaxPerRoute(MaxPerRoute); // 每个路由默认最大连接数
        customHttpClient = new DefaultHttpClient(conMgr, params);
        return customHttpClient;
    }

    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

}
