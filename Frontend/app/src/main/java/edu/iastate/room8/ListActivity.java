package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

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

public class ListActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private EditText newListName;
    private Button btn_back;
    private RequestQueue mQueue;
    private TextView Text_View_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button newList = findViewById(R.id.newList);
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

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/rlimp";

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
