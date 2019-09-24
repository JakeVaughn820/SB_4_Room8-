package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class NewListActivity extends AppCompatActivity {

    private EditText newListName;
    private Button btn_back;
    private Button newList;
    private String TAG = NewListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        newList = findViewById(R.id.newList);
        newListName = findViewById(R.id.newListName);
        btn_back = findViewById(R.id.btn_back);




        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO, actually create the new list item.
                //TODO see how to use the newly implemented volley stuff with backend
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewListActivity.this, MainListActivity.class);
                startActivity(i);
            }
        });
    }

//    /**
//     * Making json array request
//     * */
//    private void makeJsonArrayReq() {
//        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        msgResponse.setText(response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(req,
//                tag_json_arry);
//
//        // Cancelling request
//        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
//    }

}
