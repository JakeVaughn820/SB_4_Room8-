package edu.iastate.room8;
//TODO make a mydate class and try to follow along with tutorial except with this instead
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

    public String parseDate(){
        return (month + 1) + "/" + day + "/" + year;

    }

    public String parseDay(){
        return day + "";
    }

    public String parseMonth(){
        return (month + 1) + "";

    }

    public String parseYear(){
        return year + "";

    }
}
