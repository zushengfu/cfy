package com.javen.function;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateChange {

/**
* 日期转换成字符串
* @param date  需要转换的日期
* @return str 按照yyyy-mm-dd格式转换的日期字符串
*/
public  String DateToStr(Date date) {
   //HH:mm:ss
   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   String str = format.format(date);
   return str;
}

/**
* 字符串转换成日期
* @param str
* @return date
*/
public  Date StrToDate(String str) {
  
   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   Date date = null;
   try {
    date = format.parse(str);
   } catch (ParseException e) {
    e.printStackTrace();
   }
   return date;
}
/**
 * 日期转换成字符串
 * @param date 需要转换的日期
 * @return str
 */
public String dateToStrWithY(Date date){
	SimpleDateFormat format = new SimpleDateFormat("yyyy");
	   String str = format.format(date);
	   return str;
}

}