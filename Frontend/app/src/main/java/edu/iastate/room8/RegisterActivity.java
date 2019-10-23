package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText userEmailEditText;
    private EditText passwordEditText;
    private EditText passwordEditTextCheck;
    private String userNameEditTextString;
    private String userEmailEditTextString;
    private String passwordEditTextString;
    private String passwordCheckTextString;

    private Button btnRegister;
    private Button btnLogin;
    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = findViewById(R.id.userEmailEditText);
        userEmailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.userPasswordEditText);
        passwordEditTextCheck = findViewById(R.id.userPasswordCheckEditText);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmailEditTextString = userEmailEditText.getText().toString();
                userNameEditTextString = userNameEditText.getText().toString();
                passwordEditTextString = passwordEditText.getText().toString();
                passwordCheckTextString = passwordEditTextCheck.getText().toString();

                if(userNameEditTextString.equals("")){
                    Toast.makeText(RegisterActivity.this, "Must input a username!", Toast.LENGTH_SHORT).show();
                }else if(userEmailEditTextString.equals("")){
                    Toast.makeText(RegisterActivity.this, "Must input an email!", Toast.LENGTH_SHORT).show();
                }else if(passwordEditTextString.equals("")){
                    Toast.makeText(RegisterActivity.this, "Must input a password", Toast.LENGTH_SHORT).show();
                }else if(passwordEditTextString.length()<8){
                    Toast.makeText(RegisterActivity.this, "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
                }else if(!passwordEditTextString.equals(passwordCheckTextString)){
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else{
                    postRequest();
//                    finish();
                }
            }
        });
    }


    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/register";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Name", userNameEditTextString);
        params.put("Email", userEmailEditTextString);
        params.put("Password", passwordEditTextString);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Response");
                            if(success.equals("Success")){
                                finish();
                                Toast.makeText(RegisterActivity.this, "Successfully created account!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Username/Email already in use.", Toast.LENGTH_SHORT).show();
                            }
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
                params.put("Name", userNameEditTextString);
                params.put("Email", userEmailEditTextString);
                params.put("Password", passwordEditTextString);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
