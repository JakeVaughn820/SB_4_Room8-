package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        jsonParse();
        toAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make sure post request works
                //postRequest();
                stringToAddText = toAddText.getText().toString();
                toAddText.setText("");
            }
        });

    }
    private void jsonParse() {
        String url = "https://api.myjson.com/bins/o1jlx";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Message");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                String id = List.getString("id");
                                String contents = List.getString("contents");



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
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd"; //TODO change this for the bulletin

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "User"); //TODO When the user makes their login they should provide a name. This name will be put here.
        params.put("contents", stringToAddText);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(params),
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
                params.put("contents", "Hi its Paul");
                params.put("dateCreate", "sep 9");

//                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
    }

}
