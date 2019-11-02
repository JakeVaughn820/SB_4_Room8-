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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity NewUserRoomJoin. You can create a new room which you can access in this.
 * You can join a new room too with the room ID. You can access any of the rooms you have already joined too.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class NewUserRoomJoin extends AppCompatActivity {
    /**
     * Button that when pressed creates a new room with the name of the edit text
     */
    private Button newRoomCreate;
    /**
     * Button that will join the room with ID the user input
     */
    private Button joinRoom;
    /**
     * Button that logs out
     */
    private Button logout;
    /**
     * Request Queue
     */
    private RequestQueue mQueue;
    /**
     * User input for ID of room to join
     */
    private EditText joinRoomEditText;
    /**
     * User input for the name of the new room they want to create
     */
    private EditText newRoomCreateEditText;
    /**
     * List View with all the rooms the user is in
     */
    private ListView list;
    /**
     * Tag with the activity currently in
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * Session Manager
     */
    SessionManager sessionManager;
    /**
     *     These tags will be used to cancel the requests
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    /**
     * ArrayList with items for List View
     */
    private ArrayList<String> items;
    /**
     * Adapter for List View
     */
    private ArrayAdapter<String> adapter;
    /**
     * ids of the rooms parsed
     */
    private ArrayList<String> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_room_join);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        //postRequestForParse();

        newRoomCreate = findViewById(R.id.NewRoomCreate);
        newRoomCreateEditText = findViewById(R.id.RoomNameCreate);
        joinRoom = findViewById(R.id.RoomJoin);
        joinRoomEditText = findViewById(R.id.roomIdEditText);
        list = findViewById(R.id.RoomList);
        logout = findViewById(R.id.logoutButton);

        mQueue = Volley.newRequestQueue(this);

        items = new ArrayList<String>();

        items.add("Test Room");
        sessionManager.addRoom("Test Room");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);

        ids = new ArrayList<String>();


        jsonParse();

        newRoomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newRoomCreateEditText.getText().toString().equals("")){
                    Toast.makeText(NewUserRoomJoin.this, "Must input a room name!", Toast.LENGTH_SHORT).show();
                }else{
                    postRequestCreate();
                    items.clear();
                    ids.clear();
                    jsonParse();
                }
            }
        });

        joinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postRequestJoin();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

        list.setOnItemClickListener(messageClickedHandler);
    }

    /**
     * list onClickListener. Goes to homeActivity and sets the room of the user to the correct room.
     */
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            sessionManager.addRoom(ids.get(position));
            Intent i = new Intent(NewUserRoomJoin.this, HomeActivity.class);
            startActivity(i);
        }
    };

    /**
     * Used to parse JSON Objects in NewUserRoomJoin
     * Will get the rooms the user has joined and display them in a list
     * Receives Header: Rooms. Keys: Title, ID
     * @throws JSONException
     */
    private void jsonParse() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
        url = url + "/" + sessionManager.getID();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Rooms");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);
                                items.add(List.getString("Title"));
                                ids.add(List.getString("Id"));
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
        AppController.getInstance().addToRequestQueue(request);
//        mQueue.add(request);

    }

    /**
     * post that creates a new room in the database.
     * Sends Keys: Title
     */
    private void postRequestCreate() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<String, String>();
//        params.put("User", sessionManager.getID());
        params.put("Title", newRoomCreateEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

//                        try {
//                            sessionManager.addRoom(response.getString("RoomId"));
//                            Intent j = new Intent(NewUserRoomJoin.this, HomeActivity.class);
//                            startActivity(j);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
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
                params.put("User", getIntent().getStringExtra("USER_ID"));
                params.put("CreateRoom", "Yes");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

//    private void postRequestForParse() {
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("UserID", getIntent().getStringExtra("USER_ID"));
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("Rooms");
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//                                JSONObject List = jsonArray.getJSONObject(i);
//
//                                items.add(List.getString("Room"));
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
//                params.put("UserID", getIntent().getStringExtra("USER_ID"));
//
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//    }
    /**
     * post that lets the user join a new room
     * Sends Keys: Title, RoomId
     */
    private void postRequestJoin() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
        url = url + "/" + sessionManager.getID();

        Map<String, String> params = new HashMap<String, String>();
        params.put("User", sessionManager.getID());
        params.put("RoomId", joinRoomEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Response");
                            if(success.equals("Success")){
                                sessionManager.addRoom(joinRoomEditText.getText().toString());
                                items.clear();
                                ids.clear();
                                jsonParse();
                            }else{
                                Toast.makeText(NewUserRoomJoin.this, "Room does not exist!", Toast.LENGTH_SHORT).show();
                            }
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
                params.put("User", getIntent().getStringExtra("USER_ID"));
                params.put("CreateRoom", "Yes");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
