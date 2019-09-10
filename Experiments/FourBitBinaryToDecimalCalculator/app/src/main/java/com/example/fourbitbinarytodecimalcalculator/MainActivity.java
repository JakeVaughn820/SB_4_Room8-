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
                int binaryTotal = 0;
                boolean invalidInput = false;
                int length = binaryInput.length();
                for(int i = 0; i<length; i++){ //loop will go through for every bit and multiply by necessary power of 2.
                    if(Character.getNumericValue(binaryInput.getText().toString().charAt(i))>=2){
                        invalidInput = true;
                    }
                    binaryTotal += Character.getNumericValue(binaryInput.getText().toString().charAt(i))*Math.pow(2, length-1-i);
                }
                if(invalidInput==false){ //if there was an invalid input just output invalid.
                    total.setText(Integer.toString(binaryTotal));
                }else{
                    total.setText("Invalid");
                }
                invalidInput = false;
            }
        });
    }

}
