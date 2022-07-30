package com.example.ashley.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void addClicked(View v){

        DataBaseManagerToDo database = new DataBaseManagerToDo(this);
        EditText title = (EditText) findViewById(R.id.editText2);
        EditText location = (EditText) findViewById(R.id.editTextToDoLocation);

        database.addRowToDo(title.getText().toString(), location.getText().toString(), false);
        startActivity(new Intent(this, ToDoListActivity.class));
    }

    public void cancelClicked(View v) {
        startActivity(new Intent(this, ToDoListActivity.class));
    }
}
