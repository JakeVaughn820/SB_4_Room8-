package edu.iastate.room8;

public class DateParser {
    private int day;
    private int month;
    private int year;
    public DateParser(int i, int i1, int i2){
        day = i;
        month = i1;
        year = i2;
    }
    public void setDay(int i){
        day = i;
    }
    public void setMonth(int i){
        month = i;
    }
    public void setYear(int i){
        year = i;
    }
    public static String parseDate(int i, int i1, int i2){
        return (i1 + 1) + "/" + i + "/" + i2;

    }

    public String parseDay(int i){
        return day + "";
    }

    public String parseMonth(int i){
        return (month + 1) + "";

    }

    public String parseYear(int i){
        return year + "";

    }
}
