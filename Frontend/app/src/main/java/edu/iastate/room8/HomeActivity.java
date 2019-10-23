package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.iastate.room8.utils.SessionManager;

public class HomeActivity extends AppCompatActivity {

    private Button tempButton;
    private Button tempButtonBulletin;
    private Button tempButtonSchedule;
    private Button btnLogout;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
//        sessionManager.checkRoom();

        tempButton = findViewById(R.id.tempButton);
        tempButtonBulletin = findViewById(R.id.tempButtonBulletin);
        tempButtonSchedule = findViewById(R.id.tempButtonSchedule);
        btnLogout = findViewById(R.id.btnLogout);

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
        tempButtonSchedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(HomeActivity.this, ScheduleActivity.class);
                startActivity(i);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sessionManager.logout();
            }
        });
    }

}
