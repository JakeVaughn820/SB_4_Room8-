package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class NewRoomActivity extends AppCompatActivity {

    private EditText etNewRoom;
    private Button btnCreateRoom;
    private Button btnJoinRoom;
    private EditText etInviteCode;
    private String inviteCode;
    private String room;
    private SessionManager sessionManager;
    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);
        sessionManager = new SessionManager(this);

        etNewRoom = findViewById(R.id.etNewRoom);
        btnCreateRoom = findViewById(R.id.btnCreateRoom);
        btnJoinRoom = findViewById(R.id.btnJoinRoom);
        etInviteCode = findViewById(R.id.etInviteCode);

        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room = etNewRoom.getText().toString();
                newRoomPostRequest();
                sessionManager.setRoom(room);
                Intent i = new Intent(NewRoomActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inviteCode = etInviteCode.getText().toString();
                invitePostRequest();
                sessionManager.setRoom(room);
                Intent i = new Intent(NewRoomActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    private void invitePostRequest() {
        String url = "";

        Map<String, String> params = new HashMap<String, String>();
        params.put("InviteCode", inviteCode);
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
                params.put("Room", room);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void newRoomPostRequest() {
        String url = "";

        Map<String, String> params = new HashMap<String, String>();
        params.put("Room", room);
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
                params.put("Room", room);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
