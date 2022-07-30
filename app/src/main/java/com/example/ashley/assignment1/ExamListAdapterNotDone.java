package com.example.ashley.assignment1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamListAdapterNotDone extends ArrayAdapter<ExamListClass> {
        private android.content.Context Context;
        private List<ExamListClass> ExamList = new ArrayList<>();

        public ExamListAdapterNotDone(@NonNull Context context, ArrayList<ExamListClass> list, Integer studentId) {
            super(context, 0 , list);
            Context = context;
            ExamList = list;
            DataBaseManagerExam db = new DataBaseManagerExam(context);
            ArrayList<String> ExamTitle = db.retrieveIdStudent(studentId);
            for (String id: ExamTitle) {
                ArrayList<String> ExamInfo = db.retrieveRowExam(Integer.parseInt(id));
                Date examDate = new Date();
                Date currentDate = new Date();
                try {
                    examDate = new SimpleDateFormat("dd/MM/yyyy").parse(ExamInfo.get(2));
                }catch(ParseException e){

                }
                if(examDate.after(currentDate)) {
                    ExamList.add(new ExamListClass(ExamInfo.get(1), ExamInfo.get(2), ExamInfo.get(3), Integer.parseInt(ExamInfo.get(5)), Integer.parseInt(ExamInfo.get(0)), ExamInfo.get(4)));
                }
                }
        }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(Context).inflate(R.layout.exam_list, parent, false);

        final ExamListClass currentExam = ExamList.get(position);

        CheckBox select = (CheckBox) listItem.findViewById(R.id.checkBoxExam);
        select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    currentExam.setDeleteMe(true);
                }else{
                    currentExam.setDeleteMe(false);
                }
            }
        });
        TextView title = (TextView) listItem.findViewById(R.id.txtExamTitle);
        title.setText(currentExam.getTitle());
        TextView date = (TextView) listItem.findViewById(R.id.txtExamDate);
        date.setText(currentExam.getDate());
        TextView time = (TextView) listItem.findViewById(R.id.txtExamTime);
        time.setText(currentExam.getTime());
        TextView location = (TextView) listItem.findViewById(R.id.txtExamLocation);
        location.setText(currentExam.getLocation());

        return listItem;
    }
    public void deleteMe(){
        DataBaseManagerExam db = new DataBaseManagerExam(Context);

        for (ExamListClass exam: ExamList) {
            if(exam.getDeleteMe()){
                db.deleteRowExam(exam.getExamId());
            }
        }
    }

}
