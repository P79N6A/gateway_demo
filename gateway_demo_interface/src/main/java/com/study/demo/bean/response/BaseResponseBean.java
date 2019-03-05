package com.study.demo.bean.response;

import com.study.demo.bean.BaseBean;

public class BaseResponseBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ret_code;
	private String ret_msg;
	private String res_data;

	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	

	public String getRes_data() {
		return res_data;
	}

	public void setRes_data(String res_data) {
		this.res_data = res_data;
	}
}
