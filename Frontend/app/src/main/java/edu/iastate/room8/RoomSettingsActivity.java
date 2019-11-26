package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
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

public class RoomSettingsActivity extends AppCompatActivity {
    /**
     * List View with list of users
     */
    private ListView itemsList;
    /**
     * Items in the list view
     */
    private ArrayList<String> items;
    /**
     * Adapter for List View
     */
    private ArrayAdapter<String> adapter;
    /**
     * Array list for permissions
     */
    private ArrayList<String> permissions;
    /**
     * Array list for users
     */
    private ArrayList<String> users;
    /**
     * Request Queue
     */
    private RequestQueue mQueue;
    /**
     * Tag with class
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Session Manager
     */
    SessionManager sessionManager;
    /**
     * Deletes room when clicked
     */
    private Button deleteRoom;
    /**
     * Switch that turns on or off deleting a user
     */
    private Switch deleteSwitch;
    /**
     * Holds whether or not the switch is on
     */
    private boolean switchOn;
    private ArrayList<String> usersID;
    /**
     *     These tags will be used to cancel the requests
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_settings);
        itemsList = findViewById(R.id.userList);
        deleteRoom = findViewById(R.id.deleteRoom);
        deleteSwitch = findViewById(R.id.switchDeleteFromRoom);
        sessionManager = new SessionManager(this);

        users = new ArrayList<>();
        permissions = new ArrayList<>();
        usersID = new ArrayList<>();
        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        switchOn = false;
        mQueue = Volley.newRequestQueue(this);

        deleteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switchOn = true;
                }else{
                    switchOn = false;
                }
            }
        });
        itemsList.setOnItemClickListener(messageClickedHandler);

        deleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequestDelete();
                sessionManager.removeRoom(sessionManager.getRoom(), sessionManager.getID());
                sessionManager.leaveRoom();
            }
        });

        jsonParse();


    }

    /**
     * onClickedListener for a list. Will change permission of user.
     */
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            if(!switchOn){
                String toSend;
                if(permissions.get(position).equals("Viewer")){
                    toSend = "ADMIN";
                }else{
                    toSend = "VIEWER";
                }
                postRequest(users.get(position), toSend, usersID.get(position));
                if(permissions.get(position).equals("Viewer")){
                    permissions.set(position, "Editor");
                    items.set(position, users.get(position)+": Editor");
                    Toast.makeText(RoomSettingsActivity.this, users.get(position)+" has been changed to an Editor", Toast.LENGTH_SHORT).show();
                }else if(permissions.get(position).equals("Editor")){
                    permissions.set(position, "Viewer");
                    items.set(position, users.get(position)+": Viewer");
                    Toast.makeText(RoomSettingsActivity.this, users.get(position)+" has been changed to a Viewer", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }else{
                postRequestDeleteUserFromRoom(position);
                items.remove(position);
                users.remove(position);
                permissions.remove(position);
                usersID.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void jsonParse() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/getroommembers";
        url = url + "/" + sessionManager.getRoomid() + "/" + sessionManager.getID() + "/";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Users");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);
                                items.add(List.getString("Name") + ": " + List.getString("Role"));
                                permissions.add(List.getString("Role"));
                                users.add(List.getString("Name"));
                                usersID.add(List.getString("UserId"));
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

    public JSONObject jsonGetRoomSettings(){
        return null;
    }

    /**
     * PostRequest that tells the server it wants to change the users permission
     * Sends Keys:
     */
    private void postRequest(String user, String permission, String userID) {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/roomsettings";
        url = url + "/" + sessionManager.getRoomid() + "/" + userID;

        Map<String, String> params = new HashMap<String, String>();
        params.put("Role", permission);
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Title", "ye");
                params.put("Description","if u see this i messed up");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     * PostRequest that tells the server it wants to change the users permission
     * Sends Keys:
     */
    private void postRequestDelete() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/room/delete";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<String, String>();
        params.put("RoomId", sessionManager.getRoomid());
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Title", "ye");
                params.put("Description","if u see this i messed up");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     * PostRequest that tells the server it wants to change the users permission
     * Sends Keys:
     */
    private void postRequestDeleteUserFromRoom(int position) {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/room/kick";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<String, String>();
        params.put("UserId", usersID.get(position));
        params.put("RoomId", sessionManager.getRoomid());
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Title", "ye");
                params.put("Description","if u see this i messed up");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
