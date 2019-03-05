package com.study.demo.enums;

/**
 * 
* @Company 连连银通电子支付有限公司
*
* @Description: 接口类型枚举
* @ClassName: InterfaceTypeEnum 
* @author zhufj
* @date 2018年11月8日 下午5:47:27 
*
 */
public enum InterfaceTypeEnum {
	/** http请求 */
	HTTP_REQUEST("HTTP_REQUEST", "http请求"),
	/** socket请求 */
	SOCKET_REQUEST("SOCKET_REQUEST", "socket请求"),
	/** 未知接口 */
	UNKNOW("UNKNOW", "未知接口");

	private final String code;

	private final String desc;

	InterfaceTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static InterfaceTypeEnum getInterfaceType(String code) {
		for (InterfaceTypeEnum interfaceType : values()) {
			if (interfaceType.getCode().equals(code)) {
				return interfaceType;
			}
		}
		return null;
	}
}
