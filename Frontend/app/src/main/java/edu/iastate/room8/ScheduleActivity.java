package edu.iastate.room8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import edu.iastate.room8.utils.DateParser;
import edu.iastate.room8.utils.SessionManager;

public class ScheduleActivity extends AppCompatActivity {

    private Button goToScheduleDay;
    private CalendarView calender;
    private String date;
    private String day;
    private String month;
    private String year;
    private DateParser dateParser;
    boolean clicked;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        sessionManager = new SessionManager(this);

        goToScheduleDay = findViewById(R.id.goToScheduleDay);
        calender = findViewById(R.id.calendar);

        clicked = false;
        dateParser = new DateParser(21, 10, 2019);

        goToScheduleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clicked==false ){
                    Toast.makeText(ScheduleActivity.this, "Please select a date first", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(ScheduleActivity.this, DayActivity.class);
                    i.putExtra("EXTRA_INFORMATION", date);
                    i.putExtra("Day", day);
                    i.putExtra("Month", month);
                    i.putExtra("Year", year);
                    startActivity(i);
                }

            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateParser.setDay(i2);
                dateParser.setMonth(i1);
                dateParser.setYear(i);
                day = dateParser.parseDay();
                month = dateParser.parseMonth();
                year = dateParser.parseYear();
                date = callDateParser();
                clicked = true;
//                date = (i1 + 1) + "/" + i2 + "/" + i;
//                day = i2 + "";
//                month = (i1 + 1) + "";
//                year = i + "";
            }
        });
    }

    public String callDateParser(){
        return dateParser.parseDate();
    }

}
