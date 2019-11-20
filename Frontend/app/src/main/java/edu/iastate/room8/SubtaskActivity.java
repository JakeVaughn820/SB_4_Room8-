package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity SubTask. You will be able to see and create subtasks in this class.
 * You get to this class by clicking on the task in ListActivity.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class SubtaskActivity extends AppCompatActivity {
    /**
     * Text View with title for subtasks
     */
    private TextView titleForSubTask;
    /**
     * Request Queue
     */
    private RequestQueue mQueue;
    /**
     * List View with the subtasks
     */
    private ListView itemsSubTask;
    /**
     * Button that adds a new sub task
     */
    private Button newSubTaskItem;
    /**
     * User input for the new subtask item name
     */
    private EditText newSubTaskItemName;
    /**
     * String with the user input for the new subtask item
     */
    private String newSubTaskItemNameString;
    /**
     * ArrayList for List View
     */
    private ArrayList<String> items;
    /**
     * Adapter for List View
     */
    private ArrayAdapter<String> adapter;
    /**
     * String with the title of the page
     */
    private String title;
    /**
     * Tag with the activity currently in
     */
    private String TAG = SubtaskActivity.class.getSimpleName();
    /**
     * Used to stop requests
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    /**
     * Session Manager
     */
    SessionManager sessionManager;

    /**
     * Text View that shows if the subtasks have all been completed or not
     */
    private TextView completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.activity_subtask);
        title = getIntent().getStringExtra("EXTRA_INFORMATION");
        titleForSubTask = findViewById(R.id.TitleForSubTask);
        itemsSubTask = findViewById(R.id.SubTaskActivityList);
        newSubTaskItem = findViewById(R.id.AddNewSubTaskItem);
        newSubTaskItemName = findViewById(R.id.EnterNewSubTaskItem);
        completed = findViewById(R.id.textViewCompleted);

        mQueue = Volley.newRequestQueue(this);
        titleForSubTask.setText(title);

        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsSubTask.setAdapter(adapter);

        jsonParse();
        //postRequestForParse();

        if(sessionManager.getPermission().equals("Viewer")){
            newSubTaskItem.setVisibility(View.INVISIBLE);
            newSubTaskItemName.setVisibility(View.INVISIBLE);
        }else{
            newSubTaskItem.setVisibility(View.VISIBLE);
            newSubTaskItemName.setVisibility(View.VISIBLE);
        }
        if(!sessionManager.getPermission().equals("Viewer")) {
            itemsSubTask.setOnItemClickListener(messageClickedHandler);
        }
        newSubTaskItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newSubTaskItemNameString = newSubTaskItemName.getText().toString();
                postRequest();
                newSubTaskItemName.setText("");
                completed.setText("");
            }
        });
    }
//
    /**
     * Used to parse JSON Objects in SubtaskActivity
     * Will get the subtasks for the task selected by the user and displays them in a list.
     * @throws JSONException
     */
    private void jsonParse() {
        String url = "https://api.myjson.com/bins/jqfcl";
//        String url =

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("List");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                items.add(List.getString("contents"));
                                items.add(List.getString("dateCreate"));
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
     * Used as an onClickedListener for a list that will delete a subtask
     */
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            String toToast = items.get(position);
            items.remove(position);
            adapter.notifyDataSetChanged();
            if(items.size()==0){
                Toast.makeText(SubtaskActivity.this, "Congratulations you've completed all the subtasks!", Toast.LENGTH_LONG).show();
                completed.setText("You have completed all subtasks!");
            }else{
                Toast.makeText(SubtaskActivity.this, toToast +" Has been completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * Used for testing mockito like they do in the tutorial
     * @return JSONObject to be used
     */
    public JSONObject jsonGetSubtask(){
        return null;
    }


    /**
     * post that creates a new subtask
     * Sends keys: ListName, Task
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("ListName", title);
        params.put("Task", newSubTaskItemNameString);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
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
                params.put("ListName", title);
                params.put("Task", newSubTaskItemNameString);

//                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
    }

}
