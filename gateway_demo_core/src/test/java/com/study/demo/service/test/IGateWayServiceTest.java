package com.study.demo.service.test;

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.demo.bean.request.HttpRequestBean;
import com.study.demo.bean.request.SocketRequestBean;
import com.study.demo.bean.response.BaseResponseBean;
import com.study.demo.service.IGateWayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class IGateWayServiceTest {
	
	@Autowired    
	private IGateWayService gateWayService;
	private static final String REQ_URL = "req_url";
	private static final String REQ_DATA = "req_data";
	
	@Test 
	public void httpRequest(){  	 
		
		JSONObject reqJson = new JSONObject();      
		reqJson.put("correlationID", UUID.randomUUID().toString());    
		reqJson.put("version", "1.0");   
		reqJson.put("protocol", "HTTPS");
		reqJson.put("method", "POST");  
		reqJson.put("content_type","application/json; charset=utf-8");
		reqJson.put("charset","UTF-8");
		
		String url="https://test.lianlianpay-inc.com/merchant//query/dealFeeQuery2.htm";
		String body="{'id_name':'小朱','id_no':'330327199010216090'}";
		reqJson.put(REQ_URL,url);
		reqJson.put(REQ_DATA,body);
		HttpRequestBean httpRequestBean=JSONObject.parseObject(reqJson.toJSONString(), HttpRequestBean.class);
		System.out.println("请求报文："+JSON.toJSONString(httpRequestBean));
		BaseResponseBean baseResponseBean= gateWayService.httpRequest(httpRequestBean);
		System.out.println("返回报文："+JSON.toJSONString(baseResponseBean)); 
	}  
	@Test
	public void socketRequest(){
		String body="{'id_name':'小朱','id_no':'330327199010216090'}";
		SocketRequestBean socketRequestBean=new SocketRequestBean(); 
		socketRequestBean.setCorrelationID(UUID.randomUUID().toString());
		socketRequestBean.setVersion("1.0");
		socketRequestBean.setReq_data(body);
		socketRequestBean.setReq_ip("192.168.110.52"); 
		socketRequestBean.setReq_port("8080");
		gateWayService.socketRequest(socketRequestBean);
	}
}
