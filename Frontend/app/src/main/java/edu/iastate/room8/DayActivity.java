package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {
    private TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        date = findViewById(R.id.date);
        date.setText(getIntent().getStringExtra("EXTRA_INFORMATION"));
    }
}
