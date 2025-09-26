package backend;

import java.sql.Date;

public class Payroll {
    private int id;
    private int employeeId;
    private double salary;
    private Date payDate;

    public Payroll() {}

    public Payroll(int id, int employeeId, double salary, Date payDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.salary = salary;
        this.payDate = payDate;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for employeeId
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Getter and Setter for salary
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Getter and Setter for payDate
    public Date getPayDate() {
        return payDate;
    }
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
