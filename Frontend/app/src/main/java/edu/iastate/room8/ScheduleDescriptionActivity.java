package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity of ScheduleDescription. You will be able to see the description of the created event.
 * This includes the time frame, description, event name, and who created the event.
 * @Author Paul Degnan
 * @Author Jake Vaughn
 */
public class ScheduleDescriptionActivity extends AppCompatActivity {

    private TextView eventName;
    private TextView person;
    private TextView description;
    private TextView startEnd;

    private String eventNameString;
    private String personString;
    private String descriptionString;
    private String startEndString;

    private String TAG = NewListActivity.class.getSimpleName();
    private RequestQueue mQueue;
    SessionManager sessionManager;
    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_description);

        sessionManager = new SessionManager(this);
        eventName = findViewById(R.id.eventNameDescription);
        person = findViewById(R.id.personTextView);
        description = findViewById(R.id.descriptionTextView);
        startEnd = findViewById(R.id.startEndTextView);

        mQueue = Volley.newRequestQueue(this);



    }

    /**
     * Post Request that is used for receiving the event information
     * Sends Keys: EventName
     * Receives Keys: EventName, User, Description, Start, End
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<String, String>();
        params.put("EventName", getIntent().getStringExtra("EXTRA_INFORMATION"));
        JSONObject toPost = new JSONObject(params);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, toPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            eventName.setText(response.getString("EventName"));
                            person.setText(response.getString("User"));
                            description.setText(response.getString("Description"));
                            String start = response.getString("Start");
                            String end = response.getString("End");
                            String temp = start + " - " + end;
                            startEnd.setText(temp);

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
                params.put("EventName", getIntent().getStringExtra("EXTRA_INFORMATION"));
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
