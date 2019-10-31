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
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;

public class NewUserRoomJoin extends AppCompatActivity {

    private Button newRoomCreate;
    private Button joinRoom;
    private Button logout;
    private RequestQueue mQueue;

    private EditText joinRoomEditText;
    private EditText newRoomCreateEditText;
    private ListView list;
    private String TAG = NewListActivity.class.getSimpleName();
    SessionManager sessionManager;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        ids = new ArrayList<String>();

        jsonParse();

        newRoomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postRequestCreate();
                items.clear();
                ids.clear();
                jsonParse();

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


    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            sessionManager.addRoom(ids.get(position));
            Intent i = new Intent(NewUserRoomJoin.this, HomeActivity.class);
            startActivity(i);
        }
    };

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
