package com.example.ashley.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {
    ListView listView;
    ToDoListAdapter ToDoAdapter;
    ArrayList<ToDoListClass> ToDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
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
        listView = (ListView) findViewById(R.id.ToDoList);
        ToDoAdapter = new ToDoListAdapter(this,ToDoList);
        listView.setAdapter(ToDoAdapter);
}
    public void addClicked(View v){
        startActivity(new Intent(this, AddToDoActivity.class));
        }
    public void backClicked(View v){
        startActivity(new Intent(this, SortToDoActivity.class));
        }
}
