package com.study.demo.bean.request;

import org.hibernate.validator.constraints.NotBlank;

import com.study.demo.bean.BaseBean;

public abstract class BaseRequestBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "version is blank")
	private String version;//请求版本

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	 public abstract String validateLogic();
}
