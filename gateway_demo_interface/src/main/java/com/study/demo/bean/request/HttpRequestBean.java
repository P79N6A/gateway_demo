package com.study.demo.bean.request;

import org.hibernate.validator.constraints.NotBlank;

public class HttpRequestBean extends BaseRequestBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "req_data is blank")
	private String req_data;
	@NotBlank(message = "req_url is blank")
	private String req_url;
	@NotBlank(message = "charset is blank")
	private String charset;
	@NotBlank(message = "method is blank")
	private String method;
	@NotBlank(message = "content_type is blank")
	private String content_type;
	public String getReq_data() {
		return req_data;
	}
	public void setReq_data(String req_data) {
		this.req_data = req_data;
	}
	public String getReq_url() {
		return req_url;
	}
	public void setReq_url(String req_url) {
		this.req_url = req_url;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	@Override
	public String validateLogic() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
