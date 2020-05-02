package com.realestate.re.service.common.utls;

import org.apache.log4j.Logger;

public class LoggerUtil {

	private static Logger LOGGER = Logger.getLogger(LoggerUtil.class);

	public static void logException(Class className, Exception exception) {
		LOGGER.error("Exception class " + className);
		LOGGER.error("Error class " + exception.getClass());
		LOGGER.error("Error cause " + exception.getCause());
		LOGGER.error("Error message " + exception.getMessage());
	}

	public static void logDebug(Class className, Exception exception) {
		LOGGER.debug("Exception class " + className);
		LOGGER.debug("Error class " + exception.getClass());
		LOGGER.debug("Error cause " + exception.getCause());
		LOGGER.debug("Error message " + exception.getMessage());

	}

	public static void logDebug(Class className, String info) {
		LOGGER.debug("Debug class " + className);
		LOGGER.debug("Debug  " + info);

	}

	public static void logWarn(Class className, Exception exception) {
		LOGGER.warn("Exception class " + className);
		LOGGER.warn("Error class " + exception.getClass());
		LOGGER.warn("Error cause " + exception.getCause());
		LOGGER.warn("Error message " + exception.getMessage());
	}

	public static void logWarn(Class className, String warning) {
		LOGGER.warn("Warning class " + className);
		LOGGER.warn("Waring " + warning);
	}

	public static void logInfo(Class className, String info) {
		LOGGER.info("Info class " + className);
		LOGGER.info("Info : " + info);
	}

	public static void interceptorLog(String status, String url, String ip, String username, String datetime) {
		if (status == null) {
			status = "";
		}

		if (url == null) {
			url = "";
		}

		if (ip == null) {
			ip = "";
		}

		if (username == null) {
			username = "";
		}
		LOGGER.debug("...............interceptor( " + status + " )...................");
		LOGGER.debug("url : " + url);
		LOGGER.debug("ip : " + ip);
		LOGGER.debug("username : " + username);
		LOGGER.debug("date : " + datetime);
		LOGGER.debug("...............................................");
	}

	public static void logInfo(String info) {
		LOGGER.info("Info : " + info);
	}

}
