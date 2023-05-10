package com.example.employee_app_sqlite_database.BottomSheetDialogFragment;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.employee_app_sqlite_database.Database.DatabaseHelper;
import com.example.employee_app_sqlite_database.MainActivity;
import com.example.employee_app_sqlite_database.R;
import com.example.employee_app_sqlite_database.databinding.FragmentAddEmployeeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;

public class AddEmployee extends BottomSheetDialogFragment {
    public static final int IMAGE_CODE = 987;
    FragmentAddEmployeeBinding binding;

    DatabaseHelper databaseHelper;

    Context context;
    String gender;
    String name;
    String fatherName;
    String dob;
    String phone;
    String email;
    String address;
    String employeeId;
    String designation;
    String experience;
    String salary;
    String imagePath = null;

    public AddEmployee(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEmployeeBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        databaseHelper = new DatabaseHelper(context);

        if (getArguments() != null) {
            binding.saveBtn.setText("save changes");
            binding.nameEt.setText(getArguments().getString("Name"));
            binding.fatherNameEt.setText(getArguments().getString("FatherName"));
            binding.dobEt.setText(getArguments().getString("Dob"));
            binding.phoneEt.setText(getArguments().getString("Phone"));
            binding.emailEt.setText(getArguments().getString("Email"));
            binding.addressEt.setText(getArguments().getString("Address"));
            binding.employeeId.setText(getArguments().getString("EmployeeId"));
            binding.designitionEt.setText(getArguments().getString("Designation"));
            binding.experienceEt.setText(getArguments().getString("Experience"));
            binding.salaryEt.setText(String.valueOf(getArguments().getFloat("Salary")));
            if (getArguments().getString("imagePath") != null) {
                getUriFromImagePath(getArguments().getString("imagePath"));
            }


            if (getArguments().getString("Gender") != null) {
                if (getArguments().getString("Gender").equals("male")) {
                    binding.rbMale.setChecked(true);
                } else {
                    binding.rbFemale.setChecked(true);
                }
            }


            if (getArguments().getBoolean("MaritalStatus")) {
                binding.rbMarried.setChecked(true);
            } else {
                binding.rbUnmarried.setChecked(true);
            }
        }

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgIntent = new Intent(Intent.ACTION_PICK);
                imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent, IMAGE_CODE);
            }
        });
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedIdGender = binding.rgGender.getCheckedRadioButtonId();
                RadioButton genderRb = (RadioButton) view.findViewById(selectedIdGender);

                int selectedIdMaritalStatus = binding.rgMaritalStatus.getCheckedRadioButtonId();
                RadioButton maritalStatus = (RadioButton) view.findViewById(selectedIdMaritalStatus);
                boolean marriage = false;


                if (getArguments() != null) {
                    int Id = getArguments().getInt("Id");


                    name = binding.nameEt.getText().toString();
                    fatherName = binding.fatherNameEt.getText().toString();
                    dob = binding.dobEt.getText().toString();
                    phone = binding.phoneEt.getText().toString();
                    email = binding.emailEt.getText().toString();
                    address = binding.addressEt.getText().toString();
                    employeeId = binding.employeeId.getText().toString();
                    designation = binding.designitionEt.getText().toString();
                    experience = binding.experienceEt.getText().toString();
                    salary = binding.salaryEt.getText().toString();
                    gender = genderRb.getText().toString();
                    if (maritalStatus.getText().toString().equals("married")) {
                        marriage = true;
                    }
                    addNewEmployeeFunc(Id, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, marriage, Float.parseFloat(salary), imagePath);

                } else {
                    name = binding.nameEt.getText().toString();
                    fatherName = binding.fatherNameEt.getText().toString();
                    dob = binding.dobEt.getText().toString();
                    phone = binding.phoneEt.getText().toString();
                    email = binding.emailEt.getText().toString();
                    address = binding.addressEt.getText().toString();
                    employeeId = binding.employeeId.getText().toString();
                    designation = binding.designitionEt.getText().toString();
                    experience = binding.experienceEt.getText().toString();
                    salary = binding.salaryEt.getText().toString();
                    if (selectedIdGender == -1) {
                        gender = null;
                    } else {
                        gender = genderRb.getText().toString();
                    }

                    if (selectedIdMaritalStatus != -1) {
                        if (maritalStatus.getText().toString().equals("married")) {
                            marriage = true;
                        }
                    }

                    if (!binding.salaryEt.getText().toString().isEmpty()) {
                        addNewEmployeeFunc(0, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, marriage, Float.parseFloat(salary), imagePath);
                    } else {
                        addNewEmployeeFunc(0, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, marriage, 0, imagePath);
                    }

                }

            }
        });


        return (binding.getRoot());
    }

    private void getUriFromImagePath(String imageP) {
        File imageFile;
        Uri imageUri;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            imageFile = new File(imageP);
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageFile.getName());
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            imagePath = getRealPathFromURI(imageUri);
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            binding.imageView.setImageBitmap(bitmap);
        } else {
            imageFile = new File(imageP);
            imageUri = Uri.fromFile(imageFile);
            imagePath = getRealPathFromURI(imageUri);
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            binding.imageView.setImageBitmap(bitmap);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_CODE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    binding.imageView.setImageURI(selectedImageUri);
                    imagePath = getRealPathFromURI(data.getData());
                }
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void addNewEmployeeFunc(int Id, String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary, String image) {

        if (Id == 0) {
            // add flow
            databaseHelper.addEmployee(name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, maritalStatus, salary, image);
//            databaseHelper.employeeDao().addEmployee(new Employee(name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, maritalStatus, salary, image));
            Toast.makeText(context, "employee added successfully", Toast.LENGTH_SHORT).show();
        } else {
            // update flow
//            databaseHelper.employeeDao().updateEmployee(new Employee(Id, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, maritalStatus, salary, image));
            Toast.makeText(context, "employee updated successfully", Toast.LENGTH_SHORT).show();
        }
        this.dismiss();

//        ((MainActivity) context).showEmployees();
    }
}