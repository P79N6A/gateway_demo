package com.study.demo.utils;

import java.util.HashMap;
import java.util.UUID;

/**
 * 
* @Company 连连银通电子支付有限公司
*
* @Description: 上下文操作对象
* @ClassName: ContextHolder 
* @author zhufj
* @date 2018年11月8日 下午5:48:26 
*
 */
public class ContextHolder {

    private static final ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<HashMap<String, Object>>() {
                                                                              @Override
                                                                              protected HashMap<String, Object> initialValue() {
                                                                                  return new HashMap<String, Object>();
                                                                              }
                                                                          };

    public static void setCorrelationID(String correlationID) {
        if (null == threadLocal.get()) {
            threadLocal.set(new HashMap<String, Object>());
        }
        threadLocal.get().put("correlationID", correlationID);
    }

    public static String getCorrelationID() {
        if (null == threadLocal.get()) {
            threadLocal.set(new HashMap<String, Object>());
        }
        Object object = threadLocal.get().get("correlationID");
        if (null == object) {
            object = UUID.randomUUID();
            threadLocal.get().put("correlationID", object.toString());
        }
        return object.toString();
    }

    public static void clear() {
        if (null != threadLocal.get()) {
            threadLocal.get().clear();
        }
    }

}
