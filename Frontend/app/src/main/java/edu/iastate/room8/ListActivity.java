package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class ListActivity extends AppCompatActivity {

    private Button newList;
    private EditText newListName;
    private Button btn_back;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
    }
}
