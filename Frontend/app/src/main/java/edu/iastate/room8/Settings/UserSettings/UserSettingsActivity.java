package edu.iastate.room8.Settings.UserSettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.iastate.room8.List.NewListActivity;
import edu.iastate.room8.R;

public class UserSettingsActivity extends AppCompatActivity {
    /**
     * Edit Text with the user input for the new registered users username
     */
    private EditText userNameEditText;
    /**
     * Edit Text with the user input for the new registered users email
     */
    private EditText userEmailEditText;
    /**
     * Edit Text with the user input for the new registered users password
     */
    private EditText passwordEditText;
    /**
     * Button to change User Name
     */
    private Button btnChangeName;
    /**
     * Button to change User Email
     */
    private Button btnChangeEamil;
    /**
     * Button to change User Pass
     */
    private Button btnChangePass;
    /**
     * Button to change Logout
     */
    private Button btnlogout;
    /**
     * String with the user input for the new registered users username
     */
    private String userNameEditTextString;
    /**
     * String with the user input for the new registered users email
     */
    private String userEmailEditTextString;
    /**
     * String with the user input for the new registered users password
     */
    private String passwordEditTextString;
    /**
     * Tag with the current activity
     */
    private String TAG = NewListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //initializing variables
        userNameEditText = findViewById(R.id.userNameEditText);
        userEmailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.userPasswordEditText);
        btnChangeName = findViewById(R.id.btnChangeName);
        btnChangeEamil = findViewById(R.id.btnChangeEmail);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnlogout = findViewById(R.id.btnLogout);


    }
}
