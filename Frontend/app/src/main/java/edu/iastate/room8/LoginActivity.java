package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity of login. If you don't have a login you can press the register button.
 * Use email and password to login.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * User input for email
     */
    private EditText userEmailEditText;
    /**
     * User input for password
     */
    private EditText passwordEditText;
    /**
     * Button that will send the email and password to be checked for logging in
     */
    private Button loginbtn;
    /**
     * Button that will send user to register page
     */
    private Button signUpBtn;
    /**
     * Integer with amount of login attempts they get
     */
    private int loginAttemps = 5;
    /**
     * Text View that shows the amount of login attempts left
     */
    private TextView loginAttempsTextView;
    /**
     * Tag with current activity name
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Session Manager
     */
    SessionManager sessionManager;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        userEmailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginbtn = findViewById(R.id.loginbtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        loginAttempsTextView = findViewById(R.id.loginAttempsTextView);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(userEmailEditText.getText().toString(), passwordEditText.getText().toString());
                postRequest();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(i);
            }
        });
    }

    /**
     * Validation method to see if the email and password correspond to a user in the database. (Old method for testing)
     * @param userName_Email email the user typed in
     * @param userPassword password the user typed in
     */
    private void validate(String userName_Email, String userPassword){
        //TOD Get user names email and password see if any match.
        //TOD these will need to be gotten from the database
        //TOD I would recommend posting a request for the userName which can
        //TOD be given as either the user name or the Email of the person
        //TOD and then also getting their password. So it can be checked below.
        String getUser = "";
        String getEmail = "";
        String getPassword = "";
        String id = "whatever we want the id to be";
        String getRoom = "not null";  //Set this to equal if user is in a room. If it is null the user isn't in a room

        //This if matches the (username or email) and password with those on the database
        if((userName_Email.equals(getUser) || userName_Email.equals(getEmail)) && (userPassword.equals(getPassword))){
            sessionManager.createSession(getUser, getEmail, id);  //Creates a new session where the user is logged in

                Intent i = new Intent(LoginActivity.this, NewUserRoomJoin.class);
                startActivity(i);


        }else{

            loginAttemps--;
            loginAttempsTextView.setText("Incorrect User Name or Password" + "\n" +
                    "Login Attemps Left: " + loginAttemps
                    + "\n" + userName_Email + " " + userPassword);

            if (loginAttemps == 0){
                //TOD set up a timeout for this so it is not perma disabled.
                loginbtn.setEnabled(false);

            }
        }
    }

    /**
     * Validation method to see if the email and password correspond to a user in the database.
     * @param success Whether or not the login attempt was successful
     * @param userID ID of the user that logged in
     * @param userEmail email of the user that logged in
     * @param userName username of the user that logged in
     */
    private void validate2(String success, String userID, String userEmail, String userName){
        if(success.equals("Success")){

            Intent i = new Intent(LoginActivity.this, NewUserRoomJoin.class);
            sessionManager.createSession(userName, userEmail, userID);  //Creates a new session where the user is logged in
            startActivity(i);

        }else{

            loginAttemps--;
            loginAttempsTextView.setText("Incorrect User Name or Password" + "\n" +
                    "Login Attemps Left: " + loginAttemps
                    + "\n");

            if (loginAttemps == 0){
                loginbtn.setEnabled(false);

            }
        }
    }

    /**
     * PostRequest used to see if the email and password are in the server.
     * If the email and password correspond to a user in the database the server will
     * return a "Success" and the user will be brought the the rooms activity
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Email", userEmailEditText.getText().toString());
        params.put("Password", passwordEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Response");
                            String userID = response.getString("UserId");
                            String userEmail = userEmailEditText.getText().toString();
                            String userName = response.getString("Username");
                            Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            validate2(success, userID, userEmail, userName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Email", userEmailEditText.getText().toString());
                params.put("Password", passwordEditText.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}
