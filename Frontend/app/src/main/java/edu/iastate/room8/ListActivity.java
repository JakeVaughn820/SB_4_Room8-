package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.iastate.room8.utils.*;
import edu.iastate.room8.app.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private EditText newListName;
    private Button btn_back;
    private RequestQueue mQueue;
    private TextView Text_View_List;
    private TextView msgResponse;
    private Button newList;
    private String TAG = ListActivity.class.getSimpleName();

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        msgResponse = findViewById(R.id.msgResponse);
        mTextViewResult = findViewById(R.id.text_view_result);
        newList = findViewById(R.id.newList);
        newListName = findViewById(R.id.newListName);
        btn_back = findViewById(R.id.btn_back);
        Text_View_List = findViewById(R.id.Text_View_List);

        mQueue = Volley.newRequestQueue(this);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
                //TODO, actually create the new list item.
                //TODO see how to use the newly implemented volley stuff with backend
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

    private void jsonParse() {
        String url = "\"https://api.androidhive.info/volley/person_array.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("List");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                String id = List.getString("id");
                                String contents = List.getString("contents");
                                String dateCreate = List.getString("dateCreate");

                                mTextViewResult.append("* " + contents + "\n");
                                Text_View_List.append("* " + contents + "\n");
                            }
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
}
