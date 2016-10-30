package com.zhibo.infra;

import org.apache.logging.log4j.LogManager;

/**
 * Created by jichao on 2016/10/30.
 */
public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void trace(final String msg) {
        logger.trace(msg);
    }


    public static void info(final String msg) {
        logger.info(msg);
    }

    public static void warn(final String msg) {
        logger.warn(msg);
    }


    public static void warn(final String msg, Throwable t) {
        logger.warn(msg, t);
    }

    public static void error(final String msg) {
        logger.error(msg);
    }

    public static void error(final String msg, Throwable t) {
        logger.error(msg, t);
    }

}
