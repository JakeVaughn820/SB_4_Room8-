package edu.iastate.room8.Settings.UserSettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.Sessions.SessionManager;

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
    private String UserName; //TODO fix warnings
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
        Button btnChangeName = findViewById(R.id.btnChangeName);
        Button btnChangeEamil = findViewById(R.id.btnChangeEmail);
        Button btnChangePass = findViewById(R.id.btnChangePass);
        Button btnlogout = findViewById(R.id.btnLogout);

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserID = sessionManager.getID();
                UserName = sessionManager.getName();
                UserEmail = sessionManager.getEmail();
                userNameEditTextString = userNameEditText.getText().toString();
                sessionManager.setName(userEmailEditTextString);
                postRequestName();
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
                postRequestEmail();
            }
        }));

        btnChangePass.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view){
                UserID = sessionManager.getID();
                UserName = sessionManager.getName();
                UserEmail = sessionManager.getEmail();
                passwordEditTextString = passwordEditText.getText().toString();
                postRequestPass();
            }
        }));

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    private void postRequestName() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/ChangeName";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<>();
        params.put("ID", UserID); //email to register
        params.put("Name", userNameEditTextString); //name to register

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

        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void postRequestEmail() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/ChangeEmail";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<>();
        params.put("ID", UserID); //password to register
        params.put("Email", userEmailEditTextString); //email to register

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

        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    private void postRequestPass() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/ChangePass";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<>();
        params.put("ID", UserID); //name to register
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

        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}
