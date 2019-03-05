package com.study.demo.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "correlationID is blank")
	private String correlationID;//请求序列
	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}
}
