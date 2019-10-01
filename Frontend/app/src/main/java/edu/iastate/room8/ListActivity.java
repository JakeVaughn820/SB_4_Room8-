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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iastate.room8.app.AppController;

public class ListActivity extends AppCompatActivity {

    private TextView titleForList;

    private RequestQueue mQueue;
    private int whichOne;
    private ListView itemsList;
    private Button newListItem;
    private EditText newListItemName;
    private String newListItemNameString;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private String title;
    private String TAG = NewListActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        title = getIntent().getStringExtra("EXTRA_INFORMATION");
        titleForList = findViewById(R.id.TitleForList);
        itemsList = findViewById(R.id.ListActivityList);
        newListItem = findViewById(R.id.AddNewListItem);
        newListItemName = findViewById(R.id.EnterNewListItem);
        mQueue = Volley.newRequestQueue(this);
        whichOne = getIntent().getIntExtra("WHICH", -1);

        titleForList.setText(title);



        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        jsonParse();

        itemsList.setOnItemClickListener(messageClickedHandler);

        newListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO make sure post request works
                newListItemNameString = newListItemName.getText().toString();
                //postRequest();
                newListItemName.setText("");
            }
        });
    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/jqfcl";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("tasks");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

                                items.add(List.getString("contents"));

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


        mQueue.add(request);
    }

    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            String toToast = items.get(position);
            items.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListActivity.this, toToast +" Has been completed", Toast.LENGTH_SHORT).show();
        }
    };

    private void postRequest() {
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/listadd"; //TODO change this for the actual list

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", title);
        params.put("contents", newListItemNameString);

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
                params.put("id", title);
                params.put("contents", "sep 9");

//                params.put("body", "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//        String x = "{\"contents\":\"Hi its Paul\",\"dateCreate\":\"sep 9\"}";
    }

}
