package edu.iastate.room8.Settings.UserSettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.List.NewListActivity;
import edu.iastate.room8.R;
import edu.iastate.room8.RegisterLogin.RegisterActivity;
import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;

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
     * String with the user's ID
     */
    private String UserID;
    /**
     * String with the user's Name
     */
    private String UserName;
    /**
     * String with the user's email
     */
    private String UserEmail;
    /**
     * String with the user's password
     */
    private String UserPass;
    /**
     * Tag with the current activity
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Session Manager variable
     */
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        sessionManager = new SessionManager(this);
        //initializing variables
        userNameEditText = findViewById(R.id.userNameEditText);
        userEmailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.userPasswordEditText);
        btnChangeName = findViewById(R.id.btnChangeName);
        btnChangeEamil = findViewById(R.id.btnChangeEmail);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnlogout = findViewById(R.id.btnLogout);

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserID = sessionManager.getID();
                UserName = sessionManager.getName();
                UserEmail = sessionManager.getEmail();
                userNameEditTextString = userNameEditText.getText().toString();
                sessionManager.setName(userEmailEditTextString);
            }
        });

        btnChangeEamil.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserID = sessionManager.getID();
                UserName = sessionManager.getName();
                UserEmail = sessionManager.getEmail();
                userEmailEditTextString = userEmailEditText.getText().toString();
                sessionManager.setEmail(userEmailEditText.getText().toString());
            }
        }));

        btnChangePass.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view){
                UserID = sessionManager.getID();
                UserName = sessionManager.getName();
                UserEmail = sessionManager.getEmail();
                passwordEditTextString = passwordEditText.getText().toString();

            }
        }));
    }

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/register";

        Map<String, String> params = new HashMap<>();
        params.put("Name", userNameEditTextString); //name to register
        params.put("Email", userEmailEditTextString); //email to register
        params.put("Password", passwordEditTextString); //password to register

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Response");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //on error for json
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() { //header for json
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() { //parameter for json
                Map<String, String> params = new HashMap<>();
                params.put("Name", userNameEditTextString);
                params.put("Email", userEmailEditTextString);
                params.put("Password", passwordEditTextString);
                return params;
            }
        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
