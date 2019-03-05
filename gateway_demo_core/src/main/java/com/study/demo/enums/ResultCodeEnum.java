package com.study.demo.enums;

/**
 * 
* @Company 连连银通电子支付有限公司
*
* @Description:返回结果码枚举 
* @ClassName: ResultCodeEnum 
* @author zhufj
* @date 2018年11月8日 下午6:56:52 
*
 */
public enum ResultCodeEnum {

    // 请求处理成功
    SUCCESS("0000", "请求处理成功"),
    SYSTEM_ERROR("9999","系统请求错误"),
    PARAM_ILLEGAL("1001","请求参数错误"),
    SOCKET_TIMEOUT("1002","socket请求超时"),
    ;


    private final String code;

    private final String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ResultCodeEnum getResultCoreCode(String code) {
        for (ResultCodeEnum resultCode : values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return SYSTEM_ERROR;
    }

}
