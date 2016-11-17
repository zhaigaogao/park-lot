package com.parksys.maintain.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	 public static boolean compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() >= dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	                return true;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	                return false;
	            } 
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return true;
	    }
}
