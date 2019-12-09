package edu.iastate.room8.Schedule.ScheduleMVP;

public interface IDateParserInversionPattern {
    void setDay(int i);
    void setMonth(int i);
    void setYear(int i);
    String parseDay();
    String parseMonth();
    String parseYear();
    String parseDate();
}
