package com.example.ashley.assignment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student);
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
        EditText id = (EditText) findViewById(R.id.editTextStudentIdDisplay);
        EditText lastName = (EditText) findViewById(R.id.editTextLastNameDisplay);
        EditText FirstName = (EditText) findViewById(R.id.editTextfNameDisplay);
        EditText age = (EditText) findViewById(R.id.editTextAgeDisplay);
        EditText Course = (EditText) findViewById(R.id.editTextCourseStudyDisplay);
        EditText Address = (EditText) findViewById(R.id.editTextAddressDisplay);
        RadioButton GenderM = (RadioButton) findViewById(R.id.rbtnMaleDisplay);
        RadioButton GenderF = (RadioButton) findViewById(R.id.rbtnFemaleDisplay);
        ImageView selected_photo = (ImageView) findViewById(R.id.imgStudent);


        DataBaseManager database = new DataBaseManager(this);
        Intent intent = getIntent();
        ArrayList<String> data = database.retrieveRow(intent.getIntExtra("Student id", 0));
        id.setText(String.valueOf(data.get(0)));
        lastName.setText(data.get(1));
        FirstName.setText(data.get(2));
        age.setText(String.valueOf(data.get(4)));
        Course.setText(data.get(5));
        Address.setText(data.get(6));
        String gender = data.get(3);

        File direct = new File(getFilesDir() + "/StudentPhotos");
        String fileName = id.getText().toString() + ".JPEG";
        //Bitmap imageToSave =  ((BitmapDrawable)targetImage.getDrawable()).getBitmap();

        if (direct.exists()) {

            File file = new File(getFilesDir() + "/StudentPhotos/" + fileName);
            if (file.exists()) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(getFilesDir() + "/StudentPhotos/" + fileName, options);
                selected_photo.setImageBitmap(bitmap);
            }
        }

        if(gender.equals("false")){
            GenderM.setChecked(true);
        }else{
            GenderF.setChecked(false);
        }
        id.setEnabled(false);
        lastName.setEnabled(false);
        FirstName.setEnabled(false);
        age.setEnabled(false);
        Course.setEnabled(false);
        Address.setEnabled(false);
        GenderF.setEnabled(false);
        GenderM.setEnabled(false);
    }
    public void examsClicked(View v){
        Intent intented = getIntent();

        Intent intent = new Intent(this, SortExamsActivity.class);
        intent.putExtra("Student id", intented.getIntExtra("Student id", 0));
        this.startActivity(intent);

    }
    public void editStudent(View v){
        EditText id = (EditText) findViewById(R.id.editTextStudentIdDisplay);
        EditText lastName = (EditText) findViewById(R.id.editTextLastNameDisplay);
        EditText FirstName = (EditText) findViewById(R.id.editTextfNameDisplay);
        EditText age = (EditText) findViewById(R.id.editTextAgeDisplay);
        EditText Course = (EditText) findViewById(R.id.editTextCourseStudyDisplay);
        EditText Address = (EditText) findViewById(R.id.editTextAddressDisplay);
        RadioButton GenderM = (RadioButton) findViewById(R.id.rbtnMaleDisplay);
        RadioButton GenderF = (RadioButton) findViewById(R.id.rbtnFemaleDisplay);
        Intent intent = new Intent(DisplayStudentActivity.this, EditStudentActivity.class);
        intent.putExtra("Student id", id.getText().toString());
        intent.putExtra("LastName", lastName.getText().toString());
        intent.putExtra("FirstName", FirstName.getText().toString());
        intent.putExtra("Age", age.getText().toString());
        intent.putExtra("Course", Course.getText().toString());
        intent.putExtra("Address", Address.getText().toString());
        boolean Gender = false;
        if(GenderM.isChecked()){

        }else{
            Gender = true;
        }
        intent.putExtra("Gender",Gender);
        startActivity(intent);
    }
    public void deleteStudent(View v){
        EditText id = (EditText) findViewById(R.id.editTextStudentIdDisplay);
        DataBaseManager database = new DataBaseManager(this);
        database.deleteRow(Integer.parseInt(id.getText().toString()));
        startActivity(new Intent(this, studentListActivity.class));
    }
}
