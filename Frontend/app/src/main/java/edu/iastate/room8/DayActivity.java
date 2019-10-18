package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    private RequestQueue mQueue;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
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

        mQueue = Volley.newRequestQueue(this);


        date.setText(getIntent().getStringExtra("EXTRA_INFORMATION"));
        dateString = date.getText().toString();
        day = getIntent().getStringExtra("Day");
        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");

        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        jsonParse();

        buttonAddScheduleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DayActivity.this, NewScheduleActivity.class);
                i.putExtra("DATE", dateString);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(messageClickedHandler);
    }




    private void postRequest() { //TODO put this post request in the "schedule description activity" when made
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Day", day);
        params.put("Month", month);
        params.put("Year", year);
        //TOD
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
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";
        String url = "https://api.myjson.com/bins/xf1fk";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Schedule");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);
                                //TODO figure out what we want to get from backend, probably the times and things happening at that time
                                String start = List.getString("StartTime");
                                String end = List.getString("EndTime");
                                String eventName = List.getString("EventName");
                                String user = List.getString("User");
                                items.add(user + ": " + eventName + "\t" + start + " - " + end);


//                                Toast.makeText(MainListActivity.this, temp, Toast.LENGTH_SHORT).show();
                            }
                            adapter.notifyDataSetChanged();
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
        mQueue.add(request);
    }

    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent i = new Intent(DayActivity.this, ScheduleDescriptionActivity.class);
            i.putExtra("EXTRA_INFORMATION", items.get(position));
            startActivity(i);
        }
    };

}
