package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewScheduleActivity extends AppCompatActivity {

    private TextView addNewEventTextView;

    private Button addNewEventButton;

    private EditText startTime;
    private EditText endTime;
    private EditText eventName;
    private EditText eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);
        addNewEventTextView = findViewById(R.id.addNewEventTextView);
        addNewEventButton = findViewById(R.id.addNewEventButton);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        eventName = findViewById(R.id.eventName);
        eventDescription = findViewById(R.id.eventDescription);


    }
}
