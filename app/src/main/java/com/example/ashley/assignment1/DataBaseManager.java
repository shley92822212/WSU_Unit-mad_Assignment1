package com.example.ashley.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseManager {

    private static final String DB_TABLE = "StudentInfo";
    public static final String DB_NAME = "Student";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "
            + DB_TABLE + " (StudentID INTEGER, FirstName TEXT, LastName TEXT, Gender BOOLEAN," +
            "CourseStudy TEXT, Age INTEGER, Address TEXT);";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DataBaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DataBaseManager openReadable() throws android.database.SQLException {

        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }
    public void close() {
        helper.close();
    }
    public boolean addRow(Integer id, String first, String last, Boolean gender, String course, Integer age, String address) {
            synchronized (this.db) {

                ContentValues newStudentt = new ContentValues();
                newStudentt.put("StudentID", id);
                newStudentt.put("FirstName", first);
                newStudentt.put("LastName", last);
                newStudentt.put("Age", age);
                newStudentt.put("Gender", gender);
                newStudentt.put("CourseStudy", course);
                newStudentt.put("Address", address);
                try {
                    db.insertOrThrow(DB_TABLE, null, newStudentt);
                } catch (Exception e) {
                    Log.e("Error in inserting rows", e.toString());
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
    public boolean deleteRow(Integer id){
        try {
            db.delete(DB_TABLE,"StudentID="+id, null);
        } catch (Exception e) {
            Log.e("Error in deleting row", e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean edit(Integer id, String first, String last, Boolean gender, String course, Integer age, String address) {
        synchronized (this.db) {

            ContentValues updateStudent = new ContentValues();
            updateStudent.put("FirstName", first);
            updateStudent.put("LastName", last);
            updateStudent.put("Age", age);
            updateStudent.put("Gender", gender);
            updateStudent.put("CourseStudy", course);
            updateStudent.put("Address", address);
            try {
                db.update(DB_TABLE, updateStudent, "StudentID="+id, null);
            } catch (Exception e) {
                Log.e("Error in updating table", e.toString());
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    public ArrayList<String> retrieveRow(Integer ID) {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"StudentID", "LastName", "FirstName", "Gender", "Age", "CourseStudy", "Address"};
        Cursor cursor = db.query(DB_TABLE, columns, "StudentID = " + ID, null, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("StudentID")));
            productRows.add(cursor.getString(cursor.getColumnIndex("LastName")));
            productRows.add(cursor.getString(cursor.getColumnIndex("FirstName")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Gender")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Age")));
            productRows.add(cursor.getString(cursor.getColumnIndex("CourseStudy")));
            productRows.add(cursor.getString(cursor.getColumnIndex("Address")));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    public ArrayList<String> retrieveRows() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"StudentID", "LastName", "FirstName", "Gender", "Age", "CourseStudy", "Address"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productRows.add(Integer.toString(cursor.getInt(0)) + ", " + cursor.getString(1) + ", " + Float.toString(cursor.getFloat(2)));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    public ArrayList<String> retrieveID() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"StudentID"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            productRows.add(cursor.getString(cursor.getColumnIndex("StudentID")));
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
