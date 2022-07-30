package com.example.ashley.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseManagerExam {

    private static final String DB_TABLE = "Exam";
    public static final String DB_NAME = "ExamList";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "
            + DB_TABLE + " (ExamId INT, Title TEXT, Date TEXT, Time TEXT, Location TEXT, StudentId INT);";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DataBaseManagerExam(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DataBaseManagerExam openReadable() throws android.database.SQLException {

        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }
    public void close() {
        helper.close();
    }
    public boolean addRowExam(String ExamTitle,String date, String time, String location, Integer studentId) {
            synchronized (this.db) {

                ContentValues newExam = new ContentValues();
                ArrayList<String> ids = retrieveIdExam();
                Integer newId = 0;
                if(ids.size() !=0){
                    newId = Integer.parseInt(ids.get(ids.size() - 1)) + 1;
                }

                newExam.put("ExamId",newId);
                newExam.put("Title", ExamTitle);
                newExam.put("Date", date);
                newExam.put("Time", time);
                newExam.put("Location", location);
                newExam.put("StudentId", studentId);

                try {
                    db.insertOrThrow(DB_TABLE, null, newExam);
                } catch (Exception e) {
                    Log.e("Error in inserting rows", e.toString());
                    e.printStackTrace();
                    return false;
                }
                //db.close();
                return true;
            }
        }
        public boolean deleteRowExam(Integer Id){
            try {
                db.delete(DB_TABLE,"ExamId="+Id, null);
            } catch (Exception e) {
                Log.e("Error in deleting row", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }

    public boolean editExam(Integer ExamId, String ExamTitle,String date, String time, String location, Integer studentId) {
        synchronized (this.db) {

            ContentValues updateExam = new ContentValues();

            updateExam.put("ExamId", ExamId);
            updateExam.put("Title", ExamTitle);
            updateExam.put("Date", date);
            updateExam.put("Time", time);
            updateExam.put("Location", location);
            updateExam.put("StudentId", studentId);

            try {
                db.update(DB_TABLE, updateExam, "ExamId="+ExamId, null);
            } catch (Exception e) {
                Log.e("Error in updating table", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }
    }
    public ArrayList<String> retrieveRowExam(Integer id) {
        ArrayList<String> productRows = new ArrayList<String>();

        String[] columns = new String[] {"ExamId", "Title", "Date", "Time", "Location", "StudentId"};
        Cursor cursor = db.query(DB_TABLE, columns, "ExamId="+id, null, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("ExamId")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Title")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Date")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Time")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Location")));
            productRows.add(cursor.getString(cursor.getColumnIndex("StudentId")));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    public ArrayList<String> retrieveIdStudent(Integer studentId) {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"ExamId"};
        Cursor cursor = db.query(DB_TABLE, columns, "StudentId="+studentId, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("ExamId")));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }
    public ArrayList<String> retrieveIdExam() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"ExamId"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("ExamId")));
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