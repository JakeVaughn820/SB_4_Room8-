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

public class MainActivity extends AppCompatActivity {


    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginbtn;
    private Button signUpBtn;
    private int loginAttemps = 5;
    private TextView loginAttempsTextView;
    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

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

    private void validate2(String success, String userID){
        if(success.equals("1")){

            Intent i = new Intent(MainActivity.this, NewUserRoomJoin.class);
            i.putExtra("USER_ID", userID);
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

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Email", userNameEditText.getText().toString());
        params.put("Password", passwordEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Success");
                            String userID = response.getString("UserID");
                            validate2(success, userID);
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
                params.put("Email", userNameEditText.getText().toString());
                params.put("Password", passwordEditText.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}
