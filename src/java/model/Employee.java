package model;

public class Employee {
    private int id;
    private int salary;
    private String hireDate;
    private String leaveDate;

    public Employee() {
    }

    public Employee(int id, int salary, String hireDate, String leaveDate) {
        this.id = id;
        this.salary = salary;
        this.hireDate = hireDate;
        this.leaveDate = leaveDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }
}
