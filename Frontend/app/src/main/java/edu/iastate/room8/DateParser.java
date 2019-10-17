package edu.iastate.room8;

public class DateParser {
    public String parseDate(int i, int i1, int i2){
        return (i1 + 1) + "/" + i2 + "/" + i;

    }

    public String parseDay(int i){
        return i + "";
    }

    public String parseMonth(int i){
        return (i + 1) + "";

    }

    public String parseYear(int i){
        return i + "";

    }
}
