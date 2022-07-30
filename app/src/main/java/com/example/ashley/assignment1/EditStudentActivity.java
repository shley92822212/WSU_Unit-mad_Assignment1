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

public class EditStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
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
        EditText id = (EditText) findViewById(R.id.editTextStudentIdEditMe);
        EditText lastName = (EditText) findViewById(R.id.editTextLastNameEditMe);
        EditText FirstName = (EditText) findViewById(R.id.editTextfNameEditMe);
        EditText age = (EditText) findViewById(R.id.editTextAgeEditMe);
        EditText Course = (EditText) findViewById(R.id.editTextCourseStudyEditMe);
        EditText Address = (EditText) findViewById(R.id.editTextAddressEditMe);

        RadioButton GenderM = (RadioButton) findViewById(R.id.rbtnMaleEditMe);
        RadioButton GenderF = (RadioButton) findViewById(R.id.rbtnFemaleEditMe);

        Intent intent = getIntent();
        id.setText(intent.getStringExtra("Student id"));
        id.setEnabled(false);
        lastName.setText(intent.getStringExtra("LastName"));
        FirstName.setText(intent.getStringExtra("FirstName"));
        age.setText(intent.getStringExtra("Age"));
        Course.setText(intent.getStringExtra("Course"));
        Address.setText(intent.getStringExtra("Address"));
        Boolean gender = intent.getBooleanExtra("Gender", false);
        if(gender = false){
            GenderM.setChecked(true);
        }else{
            GenderF.setChecked(true);
        }
    }

    public void editClicked(View v){
        DataBaseManager database = new DataBaseManager(this);
        EditText id = (EditText) findViewById(R.id.editTextStudentIdEditMe);
        EditText lastName = (EditText) findViewById(R.id.editTextLastNameEditMe);
        EditText FirstName = (EditText) findViewById(R.id.editTextfNameEditMe);
        EditText age = (EditText) findViewById(R.id.editTextAgeEditMe);
        EditText Course = (EditText) findViewById(R.id.editTextCourseStudyEditMe);
        EditText Address = (EditText) findViewById(R.id.editTextAddressEditMe);

        RadioButton GenderM = (RadioButton) findViewById(R.id.rbtnMaleEditMe);
        boolean Gender = false;
        if(GenderM.isChecked()){

        }else{
            Gender = true;
        }
        database.edit(Integer.parseInt(id.getText().toString()), FirstName.getText().toString(), lastName.getText().toString(),
                Gender, Course.getText().toString(), Integer.parseInt(age.getText().toString()), Address.getText().toString() );
        startActivity(new Intent(this, studentListActivity.class));
    }
    public void cancelEditClicked(View v) {
        startActivity(new Intent(this, studentListActivity.class));
    }
}