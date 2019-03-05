package com.study.demo.service;

import java.net.SocketTimeoutException;

import com.study.demo.bean.request.HttpRequestBean;
import com.study.demo.bean.request.SocketRequestBean;
import com.study.demo.bean.response.BaseResponseBean;
import com.study.demo.common.annotation.Dubbo;
import com.study.demo.enums.InterfaceTypeEnum;
import com.study.demo.enums.ResultCodeEnum;
import com.study.demo.exceptions.BizException;
import com.study.demo.utils.HttpRequestSimple;
import com.study.demo.utils.ISOUtil;
import com.study.demo.utils.LogUtil;
import com.study.demo.utils.SocketClient;

public class GateWayService extends BaseService implements IGateWayService {

	@Dubbo(operCode = InterfaceTypeEnum.HTTP_REQUEST, clazz = BaseResponseBean.class)
	@Override
	public BaseResponseBean httpRequest(HttpRequestBean httpRequestBean) {
		String res_data;
		try {
			// 发送post报文
			if ("GET".equals(httpRequestBean.getMethod())) {
				res_data = HttpRequestSimple.getInstance().getSendHttp(
						httpRequestBean.getReq_url() + "?"
								+ httpRequestBean.getReq_data(),
						httpRequestBean.getContent_type(),
						httpRequestBean.getCharset(),
						httpRequestBean.getCorrelationID());
			} else {
				res_data = HttpRequestSimple.getInstance().postSendHttp(
						httpRequestBean.getReq_url(),
						httpRequestBean.getReq_data(),
						httpRequestBean.getContent_type(),
						httpRequestBean.getCharset(),
						httpRequestBean.getCorrelationID());
			}
			LogUtil.info("外部系统响应报文[" + res_data + "]");
		} catch (SocketTimeoutException e) {
			LogUtil.error("调用外部系统异常：" + e.getMessage(), e);
			throw new BizException(ResultCodeEnum.SOCKET_TIMEOUT,
					e.getMessage());
		} catch (Exception e) {
			throw new BizException(ResultCodeEnum.SYSTEM_ERROR, e.getMessage());
		}
		BaseResponseBean baseResponseBean = new BaseResponseBean();
		baseResponseBean.setRet_code(ResultCodeEnum.SUCCESS.getCode());
		baseResponseBean.setCorrelationID(httpRequestBean.getCorrelationID());
		baseResponseBean.setRet_msg(ResultCodeEnum.SUCCESS.getDesc());
		baseResponseBean.setRes_data(res_data);
		return baseResponseBean;
	}

	@Dubbo(operCode = InterfaceTypeEnum.SOCKET_REQUEST, clazz = BaseResponseBean.class)
	@Override
	public BaseResponseBean socketRequest(SocketRequestBean socketRequestBean) {

		byte[] req_byte = ISOUtil.hexStringToByte(socketRequestBean
				.getReq_data().toUpperCase());
		byte[] res_data;
		try {
			// 发送socket
			LogUtil.info("调用外部系统请求报文[" + socketRequestBean.getReq_data() + "]");
			res_data = SocketClient.sendMessage(req_byte,
					socketRequestBean.getReq_ip(),
					Integer.parseInt(socketRequestBean.getReq_port()),
					socketRequestBean.getCorrelationID());
			LogUtil.info("外部系统响应报文[" + ISOUtil.hexString(res_data) + "]");
		} catch (SocketTimeoutException e) {
			throw new BizException(ResultCodeEnum.SOCKET_TIMEOUT,
					e.getMessage());
		} catch (Exception e) {
			throw new BizException(ResultCodeEnum.SYSTEM_ERROR, e.getMessage());
		}
		BaseResponseBean res = new BaseResponseBean();
		res.setRet_code(ResultCodeEnum.SUCCESS.getCode());
		res.setRet_msg(ResultCodeEnum.SUCCESS.getDesc());
		res.setCorrelationID(socketRequestBean.getCorrelationID());
		res.setRes_data(ISOUtil.hexString(res_data));
		return res;
	}

}
