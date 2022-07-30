package com.example.ashley.assignment1;

public class ToDoListClass {
    private  String title;

    private boolean done;

    Integer id;

    public ToDoListClass(String todoTitle, boolean checked, Integer sentId){
        title = todoTitle;
        done = checked;
        id = sentId;
    }

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean getDone(){
        return  done;
    }
}
