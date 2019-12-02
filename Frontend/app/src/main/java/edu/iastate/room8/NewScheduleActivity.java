package edu.iastate.room8;

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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity NewSchedule. You can create a new event in the schedule for the day on your rooms events page.
 * The new event added will be viewable in DayActivity.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class NewScheduleActivity extends AppCompatActivity {
    /**
     * Edit Text with the user input for the start time
     */
    private EditText startTime;
    /**
     * Edit Text with the user input for the end time
     */
    private EditText endTime;
    /**
     * Edit Text with the user input for the event name
     */
    private EditText eventName;
    /**
     * Edit Text with the user input for the event description
     */
    private EditText eventDescription;
    /**
     * String that holds the start time
     */
    private String startTimeString;
    /**
     * String that holds the end time
     */
    private String endTimeString;
    /**
     * String that holds the event name
     */
    private String eventNameString;
    /**
     * String that holds the event description
     */
    private String eventDescriptionString;
    /**
     * String that golds the date
     */
    private String date;
    /**
     * Session Manager
     */
    SessionManager sessionManager;
    /**
     * Tag with the current activity
     */
    private String TAG = NewListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        sessionManager = new SessionManager(this);

        Button addNewEventButton = findViewById(R.id.addNewEventButton);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        eventName = findViewById(R.id.eventName);
        eventDescription = findViewById(R.id.eventDescription);

        date = getIntent().getStringExtra("DATE");

        addNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimeString = startTime.getText().toString();
                endTimeString = endTime.getText().toString();
                eventNameString = eventName.getText().toString();
                eventDescriptionString = eventDescription.getText().toString();
                postRequest();
            }
        });
    }

    /**
     * PostRequest that creates a new event on a day specified by the user.
     * Sends Keys: EventName, StartTime, EndTime, EventDescription, date.
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<>();
        params.put("EventName", eventNameString);
        params.put("StartTime", startTimeString);
        params.put("EndTime", endTimeString);
        params.put("EventDescription", eventDescriptionString);
        params.put("Date", date);

        JSONObject toPost = new JSONObject(params);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, toPost,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
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
                params.put("EventName", eventNameString);
                params.put("StartTime", startTimeString);
                params.put("EndTime", endTimeString);
                params.put("EventDescription", eventDescriptionString);
                params.put("Date", date);
                return params;
            }
        };
        //These tags will be used to cancel the requests
         String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
