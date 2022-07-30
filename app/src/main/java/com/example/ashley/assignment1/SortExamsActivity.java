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

public class SortExamsActivity extends AppCompatActivity {

    ListView listView;
    ListView listView2;

    ExamListAdapterDone ExamAdapterDone;
    ExamListAdapterNotDone ExamAdapterNotDone;

    ArrayList<ExamListClass> ExamList = new ArrayList<>();
    ArrayList<ExamListClass> ExamListNotDone = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_exams);
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
        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listViewNotExam);
        ExamAdapterDone = new ExamListAdapterDone(this,ExamList,intent.getIntExtra("Student id", 0));
        listView.setAdapter(ExamAdapterDone);

        listView2 = (ListView) findViewById(R.id.ListviewDoneExam);
        ExamAdapterNotDone = new ExamListAdapterNotDone(this,ExamListNotDone, intent.getIntExtra("Student id", 0));
        listView2.setAdapter(ExamAdapterNotDone);

    }
    public void deleteClicked(View v){
        ExamAdapterDone.deleteMe();
        ExamAdapterNotDone.deleteMe();
        Intent intented = getIntent();
        Intent intent = new Intent(SortExamsActivity.this, SortExamsActivity.class);
        intent.putExtra("Student id", intented.getIntExtra("Student id", 0));
        startActivity(intent);
    }

    public void addClicked(View v){
        Intent intented = getIntent();
        Intent intent = new Intent(SortExamsActivity.this, AddExamActivity.class);
        intent.putExtra("Student id", intented.getIntExtra("Student id", 0));
        startActivity(intent);
    }

}
