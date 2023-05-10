package com.example.employee_app_sqlite_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.employee_app_sqlite_database.Adapter.EmployeeAdapter;
import com.example.employee_app_sqlite_database.BottomSheetDialogFragment.AddEmployee;
import com.example.employee_app_sqlite_database.Database.DatabaseHelper;
import com.example.employee_app_sqlite_database.Model.Employee;
import com.example.employee_app_sqlite_database.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Employee> employeeList;
    EmployeeAdapter employeeAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(MainActivity.this);

        binding.addFirstEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEmployee addEmp = new AddEmployee(MainActivity.this);
                addEmp.show(getSupportFragmentManager(), addEmp.getTag());
            }
        });

        binding.addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.addFirstEmployee.performClick();
            }
        });
    }

    public void showEmployees() {
//        if (databaseHelper.addEmployee().getAllEmployees().size() > 0) {
//            employeeList = databaseHelper.employeeDao().getAllEmployees();
//            employeeAdapter = new EmployeeAdapter(employeeList, MainActivity.this, databaseHelper);
//            binding.recyclerViewEmployees.setAdapter(employeeAdapter);
//            binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//            binding.nothingFoundCard.setVisibility(View.GONE);
//            binding.deleteAllBtn.setVisibility(View.VISIBLE);
//            binding.recyclerViewEmployees.setVisibility(View.VISIBLE);
//            binding.searchView.setVisibility(View.VISIBLE);
//
//        } else {
//            binding.deleteAllBtn.setVisibility(View.GONE);
//            binding.nothingFoundCard.setVisibility(View.VISIBLE);
//            binding.recyclerViewEmployees.setVisibility(View.GONE);
//            binding.searchView.setVisibility(View.GONE);
//        }
    }
}