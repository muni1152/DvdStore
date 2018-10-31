package com.ideas2it.dvdstore.logger;

import org.apache.log4j.Logger;

public class DvdLogger {

    public static final Logger dvdLogger = Logger.getLogger(DvdLogger.class.getName());

    public void error(String message, Throwable exception) {
        dvdLogger.error(message,exception);
    }
   
    public void error(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }

    public void info(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }

    public void warn(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }
   
    public void debug(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }

    public void trace(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }

    public void fatal(String message, Exception exception) {
        dvdLogger.error(message,exception);
    }

}

