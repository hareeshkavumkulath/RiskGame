package com.risk.log;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Log filter class for Risk Log files
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class RiskLogFilter implements Filter {
	
	@Override
	public boolean isLoggable(LogRecord log) {
		if(log.getLevel() == Level.CONFIG) return false;
		return true;
	}

}
