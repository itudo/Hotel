package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 处理日期的工具类<br/>
 * 1.按当前时间生成文件名
 * @author 林雪
 *
 */
public class DateUtils {
	
      private static DateFormat df=new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
      /**
       * 日期格式化工具<br/>
       * @param d:待格式化的日期
       * @param format：要求的格式模式，为null，则使用默认模式
       * @return
       */
      public static String formatDate(Date d,String format){
    	  if(format!=null&!"".equals(format)){
    	  df=new SimpleDateFormat(format);
    	  }
		return df.format(d); 
      }
      /**
       * 按当前时间生成文件名<br/>
       * 文件名格式：yyyyMMddHHmmss.后缀名<br/>
       * @param oldFileName 旧文件名
       * @return  yyyyMMddHHmmss.后缀名
       */
      public static String getNewFileName(String oldFileName){
    	  DateFormat dateFormat =new SimpleDateFormat("yyyyMMddmmss");
          String prefixName=dateFormat.format(new Date());
          int dotIndex=oldFileName.lastIndexOf(".");
          String suffixName=oldFileName.substring(dotIndex);
          String newFileName=prefixName+suffixName;
		  return newFileName;
    	  
      }
      /**  
       * 计算两个日期之间相差的天数  
       * @param smdate 较小的时间 
       * @param bdate  较大的时间 
       * @return 相差天数 
       * @throws ParseException  
       */    
      public static int hoursBetween(Date smdate,Date bdate) throws ParseException    
      {    
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
          smdate=sdf.parse(sdf.format(smdate));  
          bdate=sdf.parse(sdf.format(bdate));  
          Calendar cal = Calendar.getInstance();    
          cal.setTime(smdate);    
          long time1 = cal.getTimeInMillis();                 
          cal.setTime(bdate);    
          long time2 = cal.getTimeInMillis();         
          long between_hour=(time2-time1)/(1000*3600);  
              
         return Integer.parseInt(String.valueOf(between_hour))+1;           
      }    
      /** 
      *字符串的日期格式的计算 
      */  
          public static int hoursBetween(String smdate,String bdate) throws ParseException{  
              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
              Calendar cal = Calendar.getInstance();    
              cal.setTime(sdf.parse(smdate));    
              long time1 = cal.getTimeInMillis();                 
              cal.setTime(sdf.parse(bdate));    
              long time2 = cal.getTimeInMillis();         
              long between_hour=(time2-time1)/(1000*3600);  
                  
             return Integer.parseInt(String.valueOf(between_hour))+1;     
          }

}
