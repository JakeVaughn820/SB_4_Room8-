package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListActivity extends AppCompatActivity {

    private Button newList;
    private EditText newListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        newList = findViewById(R.id.newList);
        newListName = findViewById(R.id.newListName);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO, actually create the new list item.
                //TODO see how to use the newly implemented volley stuff with backend
            }
        });
    }
}
