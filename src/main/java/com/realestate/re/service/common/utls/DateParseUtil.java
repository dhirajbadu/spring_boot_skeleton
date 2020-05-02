package com.realestate.re.service.common.utls;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

public class DateParseUtil {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String currentDateValue = "01";

    private static final String[] monthValue = {"January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"};

    public static String getCurrentDateStr(){
        LocalDate localDate = new LocalDate();

        return localDate.toString(dateFormat , Locale.ENGLISH);
    }

    public static Date getCurrentDate(){
        LocalDate localDate = new LocalDate();

        return localDate.toDate();
    }

    public static String getCurrentDateTime(){

        return new DateTime().toString(dateTimeFormat);
    }

    public static Date addYears(String date , int years){

        try {

            DateTimeFormatter dtf = DateTimeFormat.forPattern(dateTimeFormat);

            DateTime dateTime = dtf.parseDateTime(date);

            dateTime = dateTime.plusYears(years);

            return dateTime.toDate();
        }catch (Exception ex){
            LoggerUtil.logException(DateParseUtil.class , ex);
            return getCurrentDate();
        }
    }

    public static Date addHours(String date , int houre){

        try {

            DateTimeFormatter dtf = DateTimeFormat.forPattern(dateTimeFormat);

            DateTime dateTime = dtf.parseDateTime(date);

            dateTime = dateTime.plusHours(houre);

            return dateTime.toDate();
        }catch (Exception ex){
            LoggerUtil.logException(DateParseUtil.class , ex);
            return getCurrentDate();
        }
    }

    public static Date parseDate(String date){

        DateTime resultDate = new DateTime(date);

        return resultDate.toDate();
    }

    public static String parseDate(Date date){

        DateTime resultDate = new DateTime(date);

        return resultDate.toString(dateFormat , Locale.ENGLISH);
    }

    public static String parseDateTime(Date date){

        DateTime resultDate = new DateTime(date);

        return resultDate.toString(dateTimeFormat);
    }

    public static boolean validateBeforeCurrentDate(Date date){

        boolean isBefore = new DateTime(date).isAfterNow();
        return isBefore;
    }

    public static String getCurrentYearDateStr(){
        String currentDate = getCurrentDateStr();
        return getYear(currentDate) + "-" + currentDateValue + "-" + currentDateValue;
    }

    public static Date getCurrentYearDate(){
        String currentDate = getCurrentDateStr();
        return parseDate(getYear(currentDate) + "-" + currentDateValue + "-" + currentDateValue);
    }

    public static Date getYearDate(Date date){
        String currentDate = parseDate(date);
        return parseDate(getYear(currentDate) + "-" + currentDateValue + "-" + currentDateValue);
    }

    public static String getCurrentMonthDateString(){
        String currentDate = getCurrentDateStr();
        return getYear(currentDate) + "-" + getMonth(currentDate) + "-" + currentDateValue;
    }

    public static String getMonthDateString(Date date){
        String dateStr = parseDate(date);
        return getYear(dateStr) + "-" + getMonth(dateStr) + "-" + currentDateValue;
    }

    public static Date getMonthDate(Date date){
        return parseDate(getMonthDateString(date));
    }

    public static int getMonth(String date){

        DateTime currentDate = new DateTime(date);

        return currentDate.getMonthOfYear();
    }

    public static int getMonth(Date date){

        DateTime currentDate = new DateTime(date);

        return currentDate.getMonthOfYear();
    }

    public static final Date addHours(Date date , int hours){
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusHours(hours);

        return dateTime.toDate();
    }

    public static int getYear(String date){

        DateTime currentDate = new DateTime(date);

        return currentDate.getYear();
    }

    public static String getMonthValue(String date){
        return monthValue[getMonth(date) - 1];
    }

    public static String getMonthValue(Date date){
        return monthValue[getMonth(parseDate(date)) - 1];
    }
}
