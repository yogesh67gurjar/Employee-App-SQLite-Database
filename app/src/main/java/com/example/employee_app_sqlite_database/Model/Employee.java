package com.example.employee_app_sqlite_database.Model;

public class Employee {

    private int id;
    private String name;
    private String fatherName;
    private String dob;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String employeeId;
    private String designation;
    private String experience;
    private boolean maritalStatus;
    private float salary;
    private String imagePath;

    public Employee() {
    }

    public Employee(int id, String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary, String imagePath) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.employeeId = employeeId;
        this.designation = designation;
        this.experience = experience;
        this.maritalStatus = maritalStatus;
        this.salary = salary;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", designation='" + designation + '\'' +
                ", experience='" + experience + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", salary=" + salary +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
