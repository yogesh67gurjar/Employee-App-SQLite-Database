package com.example.employee_app_sqlite_database.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.employee_app_sqlite_database.Model.Employee;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = " employeeDatabase ";
    public static final String TABLE_NAME = " EmployeeTable ";

    private static final String KEY_ID = " id ";
    private static final String KEY_NAME = " name ";
    private static final String KEY_FATHER_NAME = " fatherName ";
    private static final String KEY_DOB = " dob ";
    private static final String KEY_GENDER = " gender ";
    private static final String KEY_PHONE = " phone ";
    private static final String KEY_EMAIL = " email ";
    private static final String KEY_ADDRESS = " address ";
    private static final String KEY_EMPLOYEE_ID = " employeeId ";
    private static final String KEY_DESIGNATION = " designation ";
    private static final String KEY_EXPERIENCE = " experience ";
    private static final String KEY_MARITAL_STATUS = " maritalStatus ";
    private static final String KEY_SALARY = " salary ";
    private static final String KEY_IMAGE_PATH = " imagePath ";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + "TEXT," + KEY_FATHER_NAME + "TEXT," + KEY_DOB + "TEXT," + KEY_GENDER + "TEXT," + KEY_PHONE + "TEXT," + KEY_EMAIL + "TEXT," + KEY_ADDRESS + "TEXT," + KEY_EMPLOYEE_ID + "TEXT," + KEY_DESIGNATION + "TEXT," + KEY_EXPERIENCE + "TEXT," + KEY_MARITAL_STATUS + "BOOLEAN," + KEY_SALARY + "FLOAT," + KEY_IMAGE_PATH + "TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEmployee(String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary, String image) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_FATHER_NAME,fatherName);
        values.put(KEY_DOB,dob);
        values.put(KEY_GENDER,gender);
        values.put(KEY_PHONE,phone);
        values.put(KEY_EMAIL,email);
        values.put(KEY_ADDRESS,address);
        values.put(KEY_EMPLOYEE_ID,employeeId);
        values.put(KEY_DESIGNATION,designation);
        values.put(KEY_EXPERIENCE,experience);
        values.put(KEY_MARITAL_STATUS,maritalStatus);
        values.put(KEY_SALARY,salary);
        values.put(KEY_IMAGE_PATH,image);

        db.insert(TABLE_NAME,null,values);
    }

//    public List<Employee> getAllEmployees()
//    {
//        SQLiteDatabase db=this.getReadableDatabase();
//
//    }
}
