package edu.iastate.room8;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

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

public class SettingsActivity extends AppCompatActivity {
    static SessionManager sessionManager;
    static EditTextPreference newName, newEmail, newPass;
    /**
     * Tag with current activity name
     */
    private static String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private static String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        sessionManager = new SessionManager(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);



            newName = findPreference("userName");
            newEmail = findPreference("userEmail");
            newPass = findPreference("userPass");

            newName.setText(sessionManager.getName());
            newEmail.setText(sessionManager.getEmail());

            newName.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {

                }
            });

            newEmail.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    sessionManager.setEmail(editText.getText().toString());
                }
            });

            newPass.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {

                }
            });
        }
    }

    /**
     * PostRequest used to see if the email and password are in the server.
     * If the email and password correspond to a user in the database the server will
     * return a "Success" and the user will be brought the the rooms activity
     */
    private static void postNewName() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/newName";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Username", newName.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Response");
                            String userID = response.getString("UserId");
                            String userName = response.getString("Username");
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
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}