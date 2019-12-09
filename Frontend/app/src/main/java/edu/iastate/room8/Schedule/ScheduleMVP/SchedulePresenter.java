package edu.iastate.room8.Schedule.ScheduleMVP;

/**
 * Class for the schedule presenter that implements the interface ISchedulePresenter
 */
public class SchedulePresenter implements ISchedulePresenter {
    /**
     * Date parser for the SchedulePresenter class
     */
    private IDateParserInversionPattern dateParser;

    /**
     * Constructor for schedule presenter
     *
     * @param dateParser The dateparser being used in ScheduleActivity
     */
    SchedulePresenter(IDateParserInversionPattern dateParser) {
        this.dateParser = dateParser;
    }

    /**
     * Calls the date parser to parse the date that is to be used for the schedule.
     *
     * @return returns the parsed date
     */
    @Override
    public String callDataParser() {
        return dateParser.parseDate();
    }
}
