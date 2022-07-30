package com.example.ashley.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseManagerToDo {

    private static final String DB_TABLE = "Item";
    public static final String DB_NAME = "ToDoList3";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "
            + DB_TABLE + " (Id INT, Title TEXT, Location TEXT, Complete BOOLEAN);";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DataBaseManagerToDo(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DataBaseManagerToDo openReadable() throws android.database.SQLException {

        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }
    public void close() {
        helper.close();
    }
    public boolean addRowToDo(String ToDoTitle, String location, Boolean checked ) {
            synchronized (this.db) {

                ContentValues newToDo = new ContentValues();
                ArrayList<String> ids = retrieveId();
                Integer newId = 0;
                if(ids.size() !=0){
                    newId = Integer.parseInt(ids.get(ids.size() - 1)) + 1;
                }

                newToDo.put("Id",newId);
                newToDo.put("Title", ToDoTitle);
                newToDo.put("Location", location);
                newToDo.put("Complete", checked);
                try {
                    db.insertOrThrow(DB_TABLE, null, newToDo);
                } catch (Exception e) {
                    Log.e("Error in inserting rows", e.toString());
                    e.printStackTrace();
                    return false;
                }
                //db.close();
                return true;
            }
        }
        public boolean deleteRowToDo(Integer Id){
            try {
                db.delete(DB_TABLE,"Id="+Id, null);
            } catch (Exception e) {
                Log.e("Error in deleting row", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }

    public boolean editToDo(Integer id, String ToDoTitle, String location, Boolean checked) {
        synchronized (this.db) {

            ContentValues updateToDo = new ContentValues();
            updateToDo.put("Title", ToDoTitle);
            updateToDo.put("Location", location);
            updateToDo.put("Complete", checked);

            try {
                db.update(DB_TABLE, updateToDo, "Id="+id, null);
            } catch (Exception e) {
                Log.e("Error in updating table", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }
    }
    public ArrayList<String> retrieveRowToDo(Integer id) {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"Id", "Title", "Location", "Complete"};
        Cursor cursor = db.query(DB_TABLE, columns, "Id="+id, null, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("Id")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Title")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Location")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Complete")));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    public ArrayList<String> retrieveId() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"Id"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("Id")));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }
    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper (Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Products table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}