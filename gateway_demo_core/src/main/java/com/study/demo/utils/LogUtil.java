package com.study.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @Company 连连银通电子支付有限公司
*
* @Description: 日志工具类
* @ClassName: LogUtil 
* @author zhufj
* @date 2018年11月8日 下午5:48:54 
*
 */
public class LogUtil {

    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void debug(String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message);
        }
    }

    public static void debug(String message, String... strings) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message, strings);
        }
    }

    public static void debug(Logger logger, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message);
        }
    }

    public static void debug(Logger logger, String message, String... strings) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
        }
    }

    public static void info(String message) {
        logger.info("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void info(String message, String... strings) {
        logger.info("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void info(Logger logger, String message) {
        logger.info("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void info(Logger logger, String message, String... strings) {
        logger.info("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void warn(String message) {
        logger.warn("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void warn(String message, String... strings) {
        logger.warn("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void warn(Logger logger, String message) {
        logger.warn("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void warn(Logger logger, String message, String... strings) {
        logger.warn("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void error(String message) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void error(String message, Exception exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(String message, Throwable exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(String message, String... strings) {
        logger.error("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void error(Logger logger, String message) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void error(Logger logger, String message, Exception exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(Logger logger, String message, String... strings) {
        logger.error("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

}
