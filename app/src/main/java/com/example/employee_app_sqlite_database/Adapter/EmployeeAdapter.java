package com.example.employee_app_sqlite_database.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee_app_sqlite_database.BottomSheetDialogFragment.AddEmployee;
import com.example.employee_app_sqlite_database.Database.DatabaseHelper;
import com.example.employee_app_sqlite_database.MainActivity;
import com.example.employee_app_sqlite_database.Model.Employee;
import com.example.employee_app_sqlite_database.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    List<Employee> employeeList;
    Context context;
    DatabaseHelper databaseHelper;

    public EmployeeAdapter(List<Employee> employeeList, Context context, DatabaseHelper databaseHelper) {
        this.employeeList = employeeList;
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    // method for filtering our recyclerview items.
    public void filterList(List<Employee> filterlist) {
        employeeList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        Employee singleUnit = employeeList.get(position);
        holder.name.setText(singleUnit.getName());
        holder.phone.setText(singleUnit.getPhone());
        holder.eId.setText(singleUnit.getEmployeeId());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("Id", singleUnit.getId());
                bundle.putString("Name", singleUnit.getName());
                bundle.putString("FatherName", singleUnit.getFatherName());
                bundle.putString("Dob", singleUnit.getDob());
                bundle.putString("Gender", singleUnit.getGender());
                bundle.putString("Phone", singleUnit.getPhone());
                bundle.putString("Email", singleUnit.getEmail());
                bundle.putString("Address", singleUnit.getAddress());
                bundle.putString("EmployeeId", singleUnit.getEmployeeId());
                bundle.putString("Designation", singleUnit.getDesignation());
                bundle.putString("Experience", singleUnit.getExperience());
                bundle.putBoolean("MaritalStatus", singleUnit.isMaritalStatus());
                bundle.putFloat("Salary", singleUnit.getSalary());
                bundle.putString("imagePath", singleUnit.getImagePath());

                AddEmployee addEmp = new AddEmployee(context);
                FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                addEmp.setArguments(bundle);
                addEmp.show(fm, addEmp.getTag());
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context)
                        .setTitle("Do u really want to remove this employee ???")
                        .setPositiveButton("yes proceed",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Employee e = databaseHelper.getEmployeeById(singleUnit.getId());
                                        databaseHelper.deleteEmployee(singleUnit.getId());
                                        Toast.makeText(context, e.getName() + " removed successfully", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) context).showEmployees();
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        );
                b.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, eId;
        CardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardEmployee);
            name = itemView.findViewById(R.id.nameEt);
            phone = itemView.findViewById(R.id.phoneEt);
            eId = itemView.findViewById(R.id.eIdEt);
        }
    }
}
