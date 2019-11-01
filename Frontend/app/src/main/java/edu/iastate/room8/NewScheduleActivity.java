package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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
 * @Author Paul Degnan
 * @Author Jake Vaughn
 */
public class NewScheduleActivity extends AppCompatActivity {

    private TextView addNewEventTextView;

    private Button addNewEventButton;

    private EditText startTime;
    private EditText endTime;
    private EditText eventName;
    private EditText eventDescription;

    private String startTimeString;
    private String endTimeString;
    private String eventNameString;
    private String eventDescriptionString;
    SessionManager sessionManager;

    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        sessionManager = new SessionManager(this);

        addNewEventTextView = findViewById(R.id.addNewEventTextView);
        addNewEventButton = findViewById(R.id.addNewEventButton);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        eventName = findViewById(R.id.eventName);
        eventDescription = findViewById(R.id.eventDescription);



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

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<String, String>();
        params.put("EventName", eventNameString);
        params.put("StartTime", startTimeString);
        params.put("EndTime", endTimeString);
        params.put("EventDescription", eventDescriptionString);


//        Toast.makeText(this, params.get("Title"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, params.get("Description"), Toast.LENGTH_SHORT).show();
        JSONObject toPost = new JSONObject(params);
//        Toast.makeText(this, toPost.toString(), Toast.LENGTH_SHORT).show();
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("EventName", eventNameString);
                params.put("StartTime", startTimeString);
                params.put("EndTime", endTimeString);
                params.put("EventDescription", eventDescriptionString);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
