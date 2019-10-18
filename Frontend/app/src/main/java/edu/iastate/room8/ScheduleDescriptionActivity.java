package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScheduleDescriptionActivity extends AppCompatActivity {

    private TextView eventName;
    private TextView person;
    private TextView description;
    private TextView startEnd;

    private String eventNameString;
    private String personString;
    private String descriptionString;
    private String startEndString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_description);
        eventName = findViewById(R.id.eventNameDescription);
        person = findViewById(R.id.personTextView);
        description = findViewById(R.id.descriptionTextView);
        startEnd = findViewById(R.id.startEndTextView);


    }
}
