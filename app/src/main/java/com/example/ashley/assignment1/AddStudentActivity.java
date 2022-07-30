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

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
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

        DataBaseManager database = new DataBaseManager(this);
        EditText id = (EditText) findViewById(R.id.editTextStudentId);
        EditText lastName = (EditText) findViewById(R.id.editTextLastName);
        EditText FirstName = (EditText) findViewById(R.id.editTextfName);
        EditText age = (EditText) findViewById(R.id.editTextAge);
        EditText Course = (EditText) findViewById(R.id.editTextCourseStudy);
        EditText Address = (EditText) findViewById(R.id.editTextAddress);

        RadioButton GenderM = (RadioButton) findViewById(R.id.rbtnMale);
        RadioButton GenderF = (RadioButton) findViewById(R.id.rbtnFemale);
        boolean Gender = false;
        if(GenderM.isChecked()){

        }else{
            Gender = true;
        }
        database.addRow(Integer.parseInt(id.getText().toString()), FirstName.getText().toString(), lastName.getText().toString(),
                Gender, Course.getText().toString(), Integer.parseInt(age.getText().toString()), Address.getText().toString() );
        startActivity(new Intent(this, studentListActivity.class));
    }

    public void cancelClicked(View v) {
        startActivity(new Intent(this, studentListActivity.class));
    }
}