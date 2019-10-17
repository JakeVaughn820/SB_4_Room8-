package edu.iastate.room8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;

public class ScheduleActivity extends AppCompatActivity {

    private Button goToScheduleDay;
    private CalendarView calender;
    private String date;
    private String day;
    private String month;
    private String year;
    private DateParser dateParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        goToScheduleDay = findViewById(R.id.goToScheduleDay);
        calender = findViewById(R.id.calendar);

        dateParser = new DateParser();

        goToScheduleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ScheduleActivity.this, DayActivity.class);
                i.putExtra("EXTRA_INFORMATION", date);
                i.putExtra("Day", day);
                i.putExtra("Month", month);
                i.putExtra("Year", year);
                startActivity(i);
            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = dateParser.parseDate(i, i1, i2);
                day = dateParser.parseDay(i2);
                month = dateParser.parseMonth(i1);
                year = dateParser.parseYear(i);
//                date = (i1 + 1) + "/" + i2 + "/" + i;
//                day = i2 + "";
//                month = (i1 + 1) + "";
//                year = i + "";
            }
        });
    }

}
