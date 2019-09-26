package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button tempButton;
    private Button tempButtonBulletin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tempButton = findViewById(R.id.tempButton);
        tempButtonBulletin = findViewById(R.id.tempButtonBulletin);


        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainListActivity.class);
                startActivity(i);
            }
        });
        tempButtonBulletin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(HomeActivity.this, BulletinActivity.class);
                startActivity(i);
            }
        });
    }

}
