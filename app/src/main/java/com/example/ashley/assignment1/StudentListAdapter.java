package com.example.ashley.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends ArrayAdapter<StudentListClass> {
        private android.content.Context Context;
        private List<StudentListClass> studentList = new ArrayList<>();

        public StudentListAdapter(@NonNull Context context, ArrayList<StudentListClass> list) {
            super(context, 0 , list);
            Context = context;
            studentList = list;
            DataBaseManager db = new DataBaseManager(context);
            ArrayList<String> StudentId = db.retrieveID();
            for (String id: StudentId) {
                ArrayList<String> studentInfo = db.retrieveRow(Integer.parseInt(id));
                studentList.add(new StudentListClass(Integer.parseInt(studentInfo.get(0)), studentInfo.get(2), studentInfo.get(1)));
            }
        }
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(Context).inflate(R.layout.student_list, parent, false);

        final StudentListClass currentStudent = studentList.get(position);

        Button select = (Button) listItem.findViewById(R.id.btnSelect);
        select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Context, DisplayStudentActivity.class);
                intent.putExtra("Student id", currentStudent.getStudentNumber());
                Context.startActivity(intent);
            }
        });
        TextView Id = (TextView) listItem.findViewById(R.id.txtStudentNumber);
        Id.setText(String.valueOf(currentStudent.getStudentNumber()));
        TextView Fname = (TextView) listItem.findViewById(R.id.txtFName);
        Fname.setText(currentStudent.getFirstName());
        TextView Lname = (TextView) listItem.findViewById(R.id.txtLName);
        Lname.setText(currentStudent.getLastName());
        return listItem;
    }
}
