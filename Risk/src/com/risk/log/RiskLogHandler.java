package com.risk.log;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * Custom Handler for Risk Log functions
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class RiskLogHandler extends StreamHandler{
	
	@Override
    public void publish(LogRecord record) {
        //add own logic to publish
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }
}
