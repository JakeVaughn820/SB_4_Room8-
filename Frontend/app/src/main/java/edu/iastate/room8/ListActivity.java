package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
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

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity of the list you chose. The list you chose will have tasks and subtasks
 * Clicking on a task in completion mode deletes it. Clicking on a task not in completion mode brings you to the subtask activity.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class ListActivity extends AppCompatActivity {
    /**
     * Text view with title for list activity
     */
    private TextView titleForList;
    /**
     * Text view for the description under the title
     */
    private TextView descriptionUnderTitle;
    /**
     * Request queue
     */
    private RequestQueue mQueue;
    /**
     * integer that holds the position of the item
     */
    private int whichOne;
    /**
     * String that holds the description
     */
    private String description;
    /**
     * List View with list of tasks
     */
    private ListView itemsList;
    /**
     * Button to add a new task to the list
     */
    private Button newListItem;
    /**
     * Edit Text that will be added when the button is pressed
     */
    private EditText newListItemName;
    /**
     * String that holds the user input
     */
    private String newListItemNameString;
    /**
     * Switch that switches between completion mode and subtask mode
     */
    private Switch switchList;
    /**
     * Boolean for whether or not the switch is on(true) or off(false)
     */
    private Boolean switchOn;
    /**
     * ArrayList with the tasks for the list
     */
    private ArrayList<String> items;
    /**
     * Adapter for the list view
     */
    private ArrayAdapter<String> adapter;
    /**
     * String with the title
     */
    private String title;
    /**
     * Tag with class
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Used to stop json request
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    /**
     * session manager
     */
    SessionManager sessionManager;
    /**
     * mWebSocketClient used for connecting websocket to server.
     */
    private WebSocketClient mWebSocketClient;
    /**
     * Task ID array list
     */
    private ArrayList<String> taskID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        //connectWebSocket();
        setContentView(R.layout.activity_list);
        title = getIntent().getStringExtra("EXTRA_INFORMATION");
        titleForList = findViewById(R.id.TitleForList);
        itemsList = findViewById(R.id.ListActivityList);
        newListItem = findViewById(R.id.AddNewListItem);
        newListItemName = findViewById(R.id.EnterNewListItem);
        descriptionUnderTitle = findViewById(R.id.descriptionUnderTitle);
        switchList = findViewById(R.id.switchList);

        mQueue = Volley.newRequestQueue(this);
        whichOne = getIntent().getIntExtra("WHICH", -1);
        description = getIntent().getStringExtra("DESCRIPTION_INFORMATION");
        descriptionUnderTitle.setText(description);
        titleForList.setText(title);

        items = new ArrayList<String>();
        taskID = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        switchOn=false;

        jsonParse();
        //postRequestForParse();

        if(sessionManager.getPermission().equals("Viewer")){
            newListItem.setVisibility(View.INVISIBLE);
            newListItemName.setVisibility(View.INVISIBLE);
            switchList.setVisibility(View.INVISIBLE);
        }else{
            newListItem.setVisibility(View.VISIBLE);
            newListItemName.setVisibility(View.VISIBLE);
            switchList.setVisibility(View.VISIBLE);
        }

        itemsList.setOnItemClickListener(messageClickedHandler);

        newListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newListItemNameString = newListItemName.getText().toString();
                postRequest();
//                sendMessage(view);
                newListItemName.setText("");
            }
        });

        switchList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switchOn = true;
                }else{
                    switchOn = false;
                }
            }
        });
    }


    /**
     * Used to parse JSON Objects in ListActivity
     * Will get the tasks for the list selected by the user and displays them in a list
     * @throws JSONException
     */
    private void jsonParse() {
        //String url = "https://api.myjson.com/bins/jqfcl";
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/gettasks";
        url = url + "/" + sessionManager.getRoomid() + "/" + getIntent().getStringExtra("LISTID") + "/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("TaskList");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                items.add(List.getString("Contents"));
                                taskID.add(List.getString("Id"));
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
     * onClickedListener for the list. When the switch is on you can complete and delete items of the list.
     * When the switch is off it will bring you to the subtasks for the task.
     */
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            if(switchOn){
                String toToast = items.get(position);
                items.remove(position);
                postRequestDelete(taskID.get(position));
                taskID.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(ListActivity.this, toToast +" Has been completed", Toast.LENGTH_SHORT).show();
            }else{
                Intent i = new Intent(ListActivity.this, SubtaskActivity.class);
                i.putExtra("EXTRA_INFORMATION", items.get(position));
                i.putExtra("TASKID", taskID.get(position));
                i.putExtra("LISTID", getIntent().getStringExtra("LISTID"));
                startActivity(i);
            }
        }
    };

    /**
     * PostRequest that creates a new task in the list. It sends the name of the list to add to and the task
     * that the user wants to add
     * Sending keys: ListName, Task
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/addtask";
        url = url + "/" + sessionManager.getRoomid() + "/" + getIntent().getStringExtra("LISTID") + "/" + sessionManager.getID() + "/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Contents", newListItemNameString);

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
                params.put("Task", newListItemNameString);

//                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
    }

    /**
     * Connects to web sockets for bulletin
     */
    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("wss://echo.websocket.org");
        } catch (
                URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("If this shows up onOpen is working");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.add(message);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    /**
     * Sends the message to the web socket
     * @param view
     */
    public void sendMessage(View view) {
        mWebSocketClient.send(newListItemNameString);
    }

//    private void postRequestForParse() {
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("ListName", title);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("List");
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//                                JSONObject List = jsonArray.getJSONObject(i);
//
//                                items.add(List.getString("Contents"));
//                            }
//                            adapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("ListName", title);
//                params.put("Task", newListItemNameString);
//
////                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
////        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
//    }
    /**
     * PostRequest that creates a new task in the list. It sends the name of the list to add to and the task
     * that the user wants to add
     * Sending keys: ListName, Task
     */
    private void postRequestDelete(String taskId) {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/deletetask";
        url = url + "/" + sessionManager.getRoomid() + "/" + sessionManager.getID() + "/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("taskId", taskId);

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
                params.put("Task", newListItemNameString);

//                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
    }
}
