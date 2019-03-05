package com.study.demo.bean.request;

import org.hibernate.validator.constraints.NotBlank;

public class SocketRequestBean extends BaseRequestBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "req_data is blank")
	private String req_data;
	@NotBlank(message = "req_ip is blank")
	private String req_ip;
	@NotBlank(message = "req_port is blank")
	private String req_port;
	public String getReq_data() {
		return req_data;
	}
	public void setReq_data(String req_data) {
		this.req_data = req_data;
	}
	public String getReq_ip() {
		return req_ip;
	}
	public void setReq_ip(String req_ip) {
		this.req_ip = req_ip;
	}
	public String getReq_port() {
		return req_port;
	}
	public void setReq_port(String req_port) {
		this.req_port = req_port;
	}
	@Override
	public String validateLogic() {
		// TODO Auto-generated method stub
		return null;
	}
}
