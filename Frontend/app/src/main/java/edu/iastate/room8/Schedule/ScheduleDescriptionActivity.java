package edu.iastate.room8.Schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
import edu.iastate.room8.utils.SessionManager;

/**
 * This class is used for the activity of ScheduleDescription. You will be able to see the description of the created event.
 * This includes the time frame, description, event name, and who created the event.
 *
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class ScheduleDescriptionActivity extends AppCompatActivity {
    /**
     * Text View with the event name
     */
    private TextView eventName;
    /**
     * Text View with the person who created the event
     */
    private TextView person;
    /**
     * Text View with the description of the event
     */
    private TextView description;
    /**
     * Text View with the time frame of the event
     */
    private TextView startEnd;
    /**
     * Tag with the current activity
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Session Manager
     */
    SessionManager sessionManager;

    /**
     * Method that runs on creation
     *
     * @param savedInstanceState saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_description);

        sessionManager = new SessionManager(this);
        eventName = findViewById(R.id.eventNameDescription);
        person = findViewById(R.id.personTextView);
        description = findViewById(R.id.descriptionTextView);
        startEnd = findViewById(R.id.startEndTextView);
    }

    /**
     * Post Request that is used for receiving the event information
     * Sends Keys: EventName
     * Receives Keys: EventName, User, Description, Start, End
     */
    private void postRequest() { //TODO may want to change to a jsonparse? depends on how backend wants to do it
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<>();
        params.put("EventName", getIntent().getStringExtra("EXTRA_INFORMATION"));
        params.put("DateString", getIntent().getStringExtra("DATE"));
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
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("EventName", getIntent().getStringExtra("EXTRA_INFORMATION"));
                return params;
            }
        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
