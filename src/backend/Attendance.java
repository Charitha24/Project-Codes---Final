package backend;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
    private int id;
    private int employeeId;
    private Date date;
    private Time inTime;
    private Time outTime;

    public Attendance() {}

    public Attendance(int id, int employeeId, Date date, Time inTime, Time outTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
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

    // Getter and Setter for date
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    // Getter and Setter for inTime
    public Time getInTime() {
        return inTime;
    }
    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    // Getter and Setter for outTime
    public Time getOutTime() {
        return outTime;
    }
    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }
}
