package edu.iastate.room8.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        sessionManager = new SessionManager(this);

        Button newList = findViewById(R.id.newList);
        newListName = findViewById(R.id.newListName);
        Button btn_back = findViewById(R.id.btn_back);
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

        Map<String, String> params = new HashMap<>();
        params.put("Title", newListNameString);
        params.put("Description", descriptionTextString);
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
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Title", newListNameString);
                params.put("Description", descriptionTextString);
                return params;
            }
        };
        //These tags will be used to cancel the requests
        String tag_json_obj = "jobj_req";
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
