package com.example.fourbitbinarytodecimalcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView total;
    EditText binaryInput;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total = (TextView) findViewById(R.id.textOutput);
        binaryInput = (EditText) findViewById(R.id.editText);

        calculate = (Button) findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }

}
