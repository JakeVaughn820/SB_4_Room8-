package edu.iastate.room8.Schedule.ScheduleMVP;

public interface IScheduleActivity {

    void calenderChange(int i, int i1, int i2);

    void goToScheduleDayClicked();

    String callDateParser();
}
