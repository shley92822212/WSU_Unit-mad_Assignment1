package com.example.ashley.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class EditToDoActivity extends AppCompatActivity {
    Integer ToDoID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);
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
        EditText title = (EditText) findViewById(R.id.editTextTitleEditMe);
        EditText location = (EditText) findViewById(R.id.editTextLocationEditMe);
        CheckBox done = (CheckBox) findViewById(R.id.checkBoxDoneEditMe);

        DataBaseManagerToDo database = new DataBaseManagerToDo(this);
        Intent intent = getIntent();
        ToDoID = intent.getIntExtra("ToDoID",0 );
        ArrayList<String> data = database.retrieveRowToDo(ToDoID);

        title.setText(data.get(1));
        location.setText(data.get(2));
        if(data.get(3).equals("1")){
            done.setChecked(true);
        }
    }

    public void editClickedToDo(View v){
        DataBaseManagerToDo database = new DataBaseManagerToDo(this);
        EditText title = (EditText) findViewById(R.id.editTextTitleEditMe);
        EditText location = (EditText) findViewById(R.id.editTextLocationEditMe);
        CheckBox done = (CheckBox) findViewById(R.id.checkBoxDoneEditMe);
        boolean doneOrNot = true;
        if(done.isChecked()){

        }else{
            doneOrNot = false;
        }
        database.editToDo(ToDoID, title.getText().toString(), location.getText().toString(), doneOrNot);
        startActivity(new Intent(this, ToDoListActivity.class));
    }
    public void cancelEditClicked(View v) {
        startActivity(new Intent(this, ToDoListActivity.class));
    }
}
