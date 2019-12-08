package edu.iastate.room8.Schedule.ScheduleMVP;

public class SchedulePresenter {
    private static final SchedulePresenter ourInstance = new SchedulePresenter();

    public static SchedulePresenter getInstance() {
        return ourInstance;
    }

    private SchedulePresenter() {
    }
}
