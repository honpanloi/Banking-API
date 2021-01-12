package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.app.main.Main;

public class Tool {
	
	private static Logger log = Logger.getLogger(Main.class);
	
	public static String getCurrentDate() {
		String timeNow = null;
		Date currentDate = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd EEEE z Z HH:mm:ss");
		timeNow = format1.format(currentDate).toString();
		return timeNow;
	}
	
	public static void get2SecondProcessingTime() {
		
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
				log.info("Processing...");
				log.info("...");
				log.info("...");
				log.info("...");
			} catch (InterruptedException e) {
				log.info("Internal error occurred. Please exit the app.");
			} 
		}
	}
	

}
