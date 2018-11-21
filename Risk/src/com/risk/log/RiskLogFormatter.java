package com.risk.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Custom Log formatter for Risk Logs
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class RiskLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return new Date(record.getMillis())+":" +record.getSourceClassName()+":"
                +record.getSourceMethodName()+":"
                +":"
                +record.getMessage()+"\n";
	}

}
