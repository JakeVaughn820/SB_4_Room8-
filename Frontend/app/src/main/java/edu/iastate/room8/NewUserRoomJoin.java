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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;

public class NewUserRoomJoin extends AppCompatActivity {

    private Button newRoomCreate;
    private Button joinRoom;
    private Button logout;
    private EditText joinRoomEditText;
    private ListView list;
    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_room_join);

        newRoomCreate = findViewById(R.id.NewRoomCreate);
        joinRoom = findViewById(R.id.RoomJoin);
        joinRoomEditText = findViewById(R.id.roomIdEditText);
        list = findViewById(R.id.RoomList);
        logout = findViewById(R.id.logoutButton);

        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        newRoomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO post that creates new room
                Intent j = new Intent(NewUserRoomJoin.this, HomeActivity.class);
                startActivity(j);
            }
        });

        joinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO post that sees if room exists and return if true then calls method that does stuff
                Intent j = new Intent(NewUserRoomJoin.this, HomeActivity.class);
                startActivity(j);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO put session manager logout here, for now will put temporary intent
                Intent i = new Intent(NewUserRoomJoin.this, LoginActivity.class);
                startActivity(i);
            }
        });

        list.setOnItemClickListener(messageClickedHandler);//
    }


    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent i = new Intent(NewUserRoomJoin.this, HomeActivity.class);
            i.putExtra("EXTRA_INFORMATION", items.get(position));
            startActivity(i);
        }
    };


    private void postRequestForParse() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("UserID", getIntent().getStringExtra("USER_ID"));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("Rooms");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                items.add(List.getString("Room"));
                            }
                            adapter.notifyDataSetChanged();
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
                params.put("UserID", getIntent().getStringExtra("USER_ID"));

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void postRequestCreate() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("User", getIntent().getStringExtra("USER_ID"));
        params.put("CreateRoom", "Yes");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String userID = response.getString("RoomID");
                            //TODO do something with ID
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

    private void postRequestJoin() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd";

        Map<String, String> params = new HashMap<String, String>();
        params.put("User", getIntent().getStringExtra("USER_ID"));
        params.put("RoomID", joinRoomEditText.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String success = response.getString("Success");
                            if(success.equals("1")){
                                //TODO
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
