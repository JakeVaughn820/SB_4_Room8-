package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class BulletinActivity extends AppCompatActivity {
    private JsonParser jsonParser;
    private TextView textView;
    private RequestQueue mQueue;
    private Button toAddButton;
    private TextView toAddText;
    private String stringToAddText;
    private String TAG = NewListActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
//TODO maybe try and make each person color coded?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);
        mQueue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.textView);
        toAddButton = findViewById(R.id.buttonForAdd);
        toAddText = findViewById(R.id.messageToAdd);
        jsonParser = new JsonParser();

        try{
            jsonParse();  //Parses through the json given to frontend from back end
        }catch(JSONException e){
            textView.setText("Something went wrong!>!>!>");
            e.printStackTrace();
        }

        toAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make sure post request works
                stringToAddText = toAddText.getText().toString();
                if(stringToAddText.equals("")){
                    Toast.makeText(BulletinActivity.this, "Must input a message to display on the bulletin board", Toast.LENGTH_LONG).show();
                }else{
                    postRequest();
                }
                toAddText.setText("");
            }
        });
    }
    public void jsonParse() throws JSONException {
        String url = "https://api.myjson.com/bins/1g4fnt";
//
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/bulletin";
//        JSONObject json = jsonParser.jsonParse(url);
//        JSONArray jsonArray = json.getJSONArray("BulletinBoard");
//
//        for (int i = 0; i < jsonArray.length(); i++){
//            JSONObject List = jsonArray.getJSONObject(i);
//            String id = List.getString("User");
//            String contents = List.getString("Contents");
//            textView.append(Html.fromHtml("<b>"+ id + ": </b>"));
//            textView.append(contents + "\n");
//        }
//        mQueue.add(jsonParser.returnRequest());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("BulletinBoard");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                String id = List.getString("User");
                                String contents = List.getString("Contents");
                                textView.append(Html.fromHtml("<b>"+ id + ": </b>"));
                                textView.append(contents + "\n");
                            }
                        } catch (JSONException e) {
                            textView.setText("Something went wrong!>!>!>");
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

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/bulletin"; //TODO change this for the bulletin

        Map<String, String> params = new HashMap<String, String>();
        params.put("User", "User"); //TODO When the user makes their login they should provide a name. This name will be put here.
        params.put("Contents", stringToAddText);
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
                params.put("User", "User");
                params.put("Contents", stringToAddText);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
