package com.example.employee_app_sqlite_database.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.employee_app_sqlite_database.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "  employeeDatabase  ";
    public static final String TABLE_NAME = "  EmployeeTable  ";

    private static final String KEY_ID = "  id  ";
    private static final String KEY_NAME = "  name  ";
    private static final String KEY_FATHER_NAME = "  fatherName  ";
    private static final String KEY_DOB = "  dob   ";
    private static final String KEY_GENDER = "  gender  ";
    private static final String KEY_PHONE = "  phone  ";
    private static final String KEY_EMAIL = "  email  ";
    private static final String KEY_ADDRESS = "  address  ";
    private static final String KEY_EMPLOYEE_ID = "  employeeId  ";
    private static final String KEY_DESIGNATION = "  designation  ";
    private static final String KEY_EXPERIENCE = "  experience  ";
    private static final String KEY_MARITAL_STATUS = "  maritalStatus  ";
    private static final String KEY_SALARY = "  salary  ";
    private static final String KEY_IMAGE_PATH = "  imagePath  ";


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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_FATHER_NAME, fatherName);
        values.put(KEY_DOB, dob);
        values.put(KEY_GENDER, gender);
        values.put(KEY_PHONE, phone);
        values.put(KEY_EMAIL, email);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_EMPLOYEE_ID, employeeId);
        values.put(KEY_DESIGNATION, designation);
        values.put(KEY_EXPERIENCE, experience);
        values.put(KEY_MARITAL_STATUS, maritalStatus);
        values.put(KEY_SALARY, salary);
        values.put(KEY_IMAGE_PATH, image);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateEmployee(int id, String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // us hi field ko likho jisko update krwana he
        values.put(KEY_NAME, name);
        values.put(KEY_FATHER_NAME, fatherName);
        values.put(KEY_DOB, dob);
        values.put(KEY_GENDER, gender);
        values.put(KEY_PHONE, phone);
        values.put(KEY_EMAIL, email);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_EMPLOYEE_ID, employeeId);
        values.put(KEY_DESIGNATION, designation);
        values.put(KEY_EXPERIENCE, experience);
        values.put(KEY_MARITAL_STATUS, maritalStatus);
        values.put(KEY_SALARY, salary);
        values.put(KEY_IMAGE_PATH, image);

        db.update(TABLE_NAME, values, KEY_ID + " =?", new String[]{String.valueOf(id)});
    }

    public List<Employee> getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Employee> allEmployees = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            Employee e = new Employee();
            e.setId(cursor.getInt(0));
            e.setName(cursor.getString(1));
            e.setFatherName(cursor.getString(2));
            e.setDob(cursor.getString(3));
            e.setGender(cursor.getString(4));
            e.setPhone(cursor.getString(5));
            e.setEmail(cursor.getString(6));
            e.setAddress(cursor.getString(7));
            e.setEmployeeId(cursor.getString(8));
            e.setDesignation(cursor.getString(9));
            e.setExperience(cursor.getString(10));
            e.setMaritalStatus(cursor.getInt(11) > 0);
            e.setSalary(cursor.getFloat(12));
            e.setImagePath(cursor.getString(13));

            allEmployees.add(e);
        }
        cursor.close();
//        db.close();
        return allEmployees;
    }

    public Employee getEmployeeById(int id) {

        Employee e = new Employee();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
            e.setId(cursor.getInt(0));
            e.setName(cursor.getString(1));
            e.setFatherName(cursor.getString(2));
            e.setDob(cursor.getString(3));
            e.setGender(cursor.getString(4));
            e.setPhone(cursor.getString(5));
            e.setEmail(cursor.getString(6));
            e.setAddress(cursor.getString(7));
            e.setEmployeeId(cursor.getString(8));
            e.setDesignation(cursor.getString(9));
            e.setExperience(cursor.getString(10));
            e.setMaritalStatus(cursor.getInt(11) > 0);
            e.setSalary(cursor.getFloat(12));
            e.setImagePath(cursor.getString(13));
        }

        db.close();
        return e;
    }

    public void deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " =?", new String[]{String.valueOf(id)});
        // here the ? will be replaced by the id
        // here we have separated whereClause and whereArgs
        db.close();
    }

    public void deleteAllEmployees() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}