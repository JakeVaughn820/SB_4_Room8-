package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;

public class DayActivity extends AppCompatActivity {
    private TextView date;
    private String dateString;
    private String TAG = NewListActivity.class.getSimpleName();
    private String day;
    private String month;
    private String year;
    private Button buttonAddScheduleItem;

    private ListView listView;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        date = findViewById(R.id.date);
        buttonAddScheduleItem = findViewById(R.id.buttonAddScheduledItem);
        listView = findViewById(R.id.scheduleListView);

        date.setText(getIntent().getStringExtra("EXTRA_INFORMATION"));
        dateString = date.getText().toString();
        day = getIntent().getStringExtra("Day");
        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");

        buttonAddScheduleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DayActivity.this, NewScheduleActivity.class);
                i.putExtra("DATE", dateString);
                startActivity(i);
            }
        });
    }




    private void postRequest() { //TODO use a button to add stuff to schedule and post using this
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Day", day);
        params.put("Month", month);
        params.put("Year", year);
        //TODO send the thing you want to add to the schedule and the time you want to do it at
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
                params.put("Day", day);
                params.put("Month", month);
                params.put("Year", year);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    private void jsonParse() {
//        String url = "https://api.myjson.com/bins/jqfcl";
//        String url = "https://api.myjson.com/bins/w6jix";
//        String url = "https://api.myjson.com/bins/l3r1l";
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("RoomLists");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);
                                //TODO figure out what we want to get from backend, probably the times and things happening at that time
                                //items.add(List.getString("Title"));
                                //description.add(List.getString("Description"));
//                                Toast.makeText(MainListActivity.this, temp, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
}
