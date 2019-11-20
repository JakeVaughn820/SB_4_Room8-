package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity NewList. You can create a new list which you can access back in MainList.
 * You can add a description for the list but don't have to.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class NewListActivity extends AppCompatActivity {
    /**
     * Edit Text with the user inputs for name of the new list item
     */
    private EditText newListName;
    /**
     * Edit Text with the user inputs for the description of the new list item
     */
    private EditText descriptionText;
    /**
     * String that holds description
     */
    private String descriptionTextString;
    /**
     * Button that takes you back to the Main List Activity
     */
    private Button btn_back;
    /**
     * Button that when pressed adds new list
     */
    private Button newList;
    /**
     * String that holds the name of the new list
     */
    private String newListNameString;
    /**
     * Tag with current activity
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        sessionManager = new SessionManager(this);

        newList = findViewById(R.id.newList);
        newListName = findViewById(R.id.newListName);
        btn_back = findViewById(R.id.btn_back);
        descriptionText = findViewById(R.id.descriptionText);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newListNameString = newListName.getText().toString();
                descriptionTextString = descriptionText.getText().toString();
                if(newListNameString.equals("")){
                    Toast.makeText(NewListActivity.this, "Must put something in the 'enter name for new list' line!", Toast.LENGTH_LONG).show();
                }else{
                    postRequest();
                    finish();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * PostRequest that tells the server it wants to add a new list.
     * Sends Keys: Title, Description
     */
    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/addlist";
        url = url + "/" + sessionManager.getRoomid() + "/" + sessionManager.getID()+ "/";
//        Toast.makeText(this, url, Toast.LENGTH_LONG).show();

        Map<String, String> params = new HashMap<String, String>();
        params.put("Title", newListNameString);
        params.put("Description", descriptionTextString);
//        Toast.makeText(this, params.get("Title"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, params.get("Description"), Toast.LENGTH_SHORT).show();
        JSONObject toPost = new JSONObject(params);
//        Toast.makeText(this, toPost.toString(), Toast.LENGTH_LONG).show();
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
                params.put("Title", newListNameString);
                params.put("Description", descriptionTextString);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
