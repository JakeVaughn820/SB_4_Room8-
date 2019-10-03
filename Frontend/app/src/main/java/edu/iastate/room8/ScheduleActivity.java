package edu.iastate.room8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class ScheduleActivity extends AppCompatActivity {

    private Button goToScheduleDay;
    private CalendarView calender;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        goToScheduleDay = findViewById(R.id.goToScheduleDay);
        calender = findViewById(R.id.calendar);
        goToScheduleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScheduleActivity.this, DayActivity.class);
                i.putExtra("EXTRA_INFORMATION", date);
                startActivity(i);
            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = (i1 + 1) + "/" + i2 + "/" + i;
            }
        });
    }
}
