package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainListActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private TextView Text_View_List;
    private Button btn_new_list;


    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        btn_new_list = findViewById(R.id.btn_create_new_list);
        mQueue = Volley.newRequestQueue(this);
        itemsList = findViewById(R.id.itemsList);

        btn_new_list.setText("+"); //added this as a fix to the + not displaying
        items = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);
//        jsonParse();
        //TODO might try and put an on click listener to be able to go into the list and look at the list
        //TODO or just add sublist functionality, not sure which Thane wants

        btn_new_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainListActivity.this, NewListActivity.class);
                startActivity(i);
            }
        });


        itemsList.setOnItemClickListener(messageClickedHandler);


    }

    @Override
    public void onResume() { //after pressing "done" the list should now update
        super.onResume();
        items.clear();
        jsonParse();
    }

    private void jsonParse() {
//        String url = "https://api.myjson.com/bins/jqfcl";
        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/list";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("List");

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject List = jsonArray.getJSONObject(i);

//                                String id = List.getString("id");
                                items.add(List.getString("contents"));
                                String dateCreate = List.getString("dateCreate");

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
            Intent i = new Intent(MainListActivity.this, ListActivity.class);
            i.putExtra("EXTRA_INFORMATION", items.get(position));
            i.putExtra("WHICH", position);
            startActivity(i);
        }
    };

}
