package com.risk.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.risk.view.StartWindow;

/**
 * Logger setup class for Risk log files
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class RiskLogger {
	
	@SuppressWarnings("javadoc")
	public static Logger logger = Logger.getLogger(StartWindow.class.getName());

	/**
	 * Constructor 
	 *
	 */
	public RiskLogger() {
	}
	
	/**
	 * Setup up logger
	 *
	 * @throws IOException
	 */
	public void setLogger() throws IOException {
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		File file = new File("./logs/" + dateFormat.format(date) + ".log") ;
		try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("RiskLogging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new RiskLogHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("./logs/" + file.getName());
            fileHandler.setFormatter(new RiskLogFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new RiskLogFilter());
            logger.addHandler(fileHandler);
            
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        
	}
	
	/**
	 * Getter for logger
	 * 
	 * @return Logger logger
	 */
	public Logger getLogger() {
		return logger;
	}

}