package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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



        btn_new_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainListActivity.this, NewListActivity.class);
                startActivity(i);
            }
        });

        jsonParse();

    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/jqfcl";

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

                                adapter.add(contents);
                                Text_View_List.append("* " + contents + "\n");
                            }
                        } catch (JSONException e) {
                            Text_View_List.setText("Something went wrong!>!>!>");
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
