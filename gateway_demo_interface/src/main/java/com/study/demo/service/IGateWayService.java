package com.study.demo.service;

import com.study.demo.bean.request.HttpRequestBean;
import com.study.demo.bean.request.SocketRequestBean;
import com.study.demo.bean.response.BaseResponseBean;

/**
 * 
*
* @Description: XX服务
* @ClassName: IGateWayService 
* @author zhufj
* @date 2019年3月4日 下午2:00:27 
*
 */
public interface IGateWayService {

	/**
	 * 
	* @Title:
	* @Description: HTTP/HTTPS网关请求
	* @param httpRequestBean
	* @return
	 */
	public BaseResponseBean httpRequest(HttpRequestBean httpRequestBean);
	
	/**
	 * 
	* @Title:
	* @Description: SOCKET网关请求
	* @param socketRequestBean
	* @return
	 */
	public BaseResponseBean socketRequest(SocketRequestBean socketRequestBean);
}
