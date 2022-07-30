package com.example.ashley.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);
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

        DataBaseManagerExam database = new DataBaseManagerExam(this);
        EditText title = (EditText) findViewById(R.id.editTextExamName);
        EditText location = (EditText) findViewById(R.id.editTextLocation);
        EditText time = (EditText) findViewById(R.id.editTextTime);
        EditText date = (EditText) findViewById(R.id.editTextDate);
        Intent intent = getIntent();

        database.addRowExam(title.getText().toString(), date.getText().toString(),
                time.getText().toString(), location.getText().toString(), intent.getIntExtra("Student id", 0));
        Intent test = new Intent(this, SortExamsActivity.class);
        test.putExtra("Student id", intent.getIntExtra("Student id", 0));
        startActivity(test);
    }
}
