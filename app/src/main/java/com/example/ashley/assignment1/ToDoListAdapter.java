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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<ToDoListClass> {
        private android.content.Context Context;
        private List<ToDoListClass> ToDoList = new ArrayList<>();

        public ToDoListAdapter(@NonNull Context context, ArrayList<ToDoListClass> list) {
            super(context, 0 , list);
            Context = context;
            ToDoList = list;
            DataBaseManagerToDo db = new DataBaseManagerToDo(context);
            ArrayList<String> ToDoTitle = db.retrieveId();
            for (String id: ToDoTitle) {
                ArrayList<String> ToDoInfo = db.retrieveRowToDo(Integer.parseInt(id));
                boolean checked = false;
                if(ToDoInfo.get(3).equals("1")){
                    checked = true;
                }
                ToDoList.add(new ToDoListClass(ToDoInfo.get(1),checked, Integer.parseInt(ToDoInfo.get(0))));
            }
        }
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(Context).inflate(R.layout.todo_list, parent, false);

        final ToDoListClass currentToDo = ToDoList.get(position);

        Button select = (Button) listItem.findViewById(R.id.btnToDoSelect);
        select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Context, EditToDoActivity.class);
                intent.putExtra("ToDoID", currentToDo.getId());
                Context.startActivity(intent);
            }
        });
        TextView title = (TextView) listItem.findViewById(R.id.txtToDoTitle);
        title.setText(currentToDo.getTitle());
        CheckBox done = (CheckBox) listItem.findViewById(R.id.checkBoxDone);
        if(currentToDo.getDone()){
            done.setChecked(true);
        }

        return listItem;
    }
}
