package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginbtn;
    private Button signUpBtn;
    private int loginAttemps = 5;
    private TextView loginAttempsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginbtn = findViewById(R.id.loginbtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        loginAttempsTextView = findViewById(R.id.loginAttempsTextView);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(userNameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent i = new Intent(MainActivity.this, RegisterActivity.class);
               startActivity(i);
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("")) && (userPassword.equals(""))){

            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);

        }else{

            loginAttemps--;
            loginAttempsTextView.setText("Incorrect User Name or Password" + "\n" +
                    "Login Attemps Left: " + loginAttemps
                    + "\n" + userName + " " + userPassword);

            if (loginAttemps == 0){
                loginbtn.setEnabled(false);

            }
        }
    }

    //TODO put post/parse that will see if email and password is correct
    //TODO possibly take in the entire users stuff and parse through it? seems too much but easy
}
