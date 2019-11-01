package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity of the specific day chosen from the schedule.
 * Can see what is happening on the day for everyone in your room.
 * @Author Paul Degnan
 * @Author Jake Vaughn
 */
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
    private ArrayList<String> eventNames;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    SessionManager sessionManager;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        sessionManager = new SessionManager(this);

        date = findViewById(R.id.date);
        buttonAddScheduleItem = findViewById(R.id.buttonAddScheduledItem);
        listView = findViewById(R.id.scheduleListView);

        mQueue = Volley.newRequestQueue(this);


        date.setText(getIntent().getStringExtra("EXTRA_INFORMATION"));
        dateString = date.getText().toString();
        day = getIntent().getStringExtra("Day");
        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");
        eventNames = new ArrayList<String>();
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


    /**
     * Used for testing mockito like they do in the tutorial
     * @return JSONObject to be used
     */
    public JSONObject jsonGetSchedule(){
        return null;
    }

    /**
     * Used to parse JSON Objects in DayActivity
     * Will get the events for the day selected by the User and display them in a list
     * Receives: Header: Schedule. Keys: StartTime. EndTime. EventName. User.
     * @throws JSONException
     */
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
                                eventNames.add(eventName);

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

    /**
     * An onClickListener for a list. Used to look at the description of an event.
     * Will open up a new activity when any event is clicked on.
     */
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent i = new Intent(DayActivity.this, ScheduleDescriptionActivity.class);
            i.putExtra("EXTRA_INFORMATION", eventNames.get(position));
            startActivity(i);
        }
    };

}
