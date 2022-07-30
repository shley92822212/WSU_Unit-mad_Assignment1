package com.example.ashley.assignment1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class SortToDoActivity extends AppCompatActivity {

    ListView listView;
    ListView listView2;

    ToDoListAdapterDone ToDoAdapterDone;
    ToDoListAdapterNotDone ToDoAdapterNotDone;

    ArrayList<ToDoListClass> ToDoList = new ArrayList<>();
    ArrayList<ToDoListClass> ToDoListNotDone = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_to_do);
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
        listView = (ListView) findViewById(R.id.ListviewDone);
        ToDoAdapterDone = new ToDoListAdapterDone(this,ToDoList);
        listView.setAdapter(ToDoAdapterDone);

        listView2 = (ListView) findViewById(R.id.listViewNot);
        ToDoAdapterNotDone = new ToDoListAdapterNotDone(this,ToDoListNotDone);
        listView2.setAdapter(ToDoAdapterNotDone);
    }


}
