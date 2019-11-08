package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity of home. Home has buttons to get to each feature or logout
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * Button used to go to lists
     */
    private Button tempButton;
    /**
     * Button used to go to bulletin
     */
    private Button tempButtonBulletin;
    /**
     * Button used to go to schedule
     */
    private Button tempButtonSchedule;
    /**
     * Button used to logout
     */
    private Button btnLogout;
    /**
     * Session manager
     */
    SessionManager sessionManager;
    /**
     * Text View with the name of the room
     */
    private TextView roomNameTextView;
    /**
     * Text View with the ID of the room
     */
    private TextView roomIdTextView;

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
        roomIdTextView = findViewById(R.id.RoomIdTextView);
        roomNameTextView = findViewById(R.id.RoomNameTextView);

        roomIdTextView.setText("Room ID: "+sessionManager.getRoomid());
        roomNameTextView.setText("Room Name: "+sessionManager.getRoom());

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
                Intent i = new Intent(HomeActivity.this, MoreActivity.class);
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
