package dynamodb;

public class LeaveRequest {
    private String requestId;
    private int employeeId;
    private String fromDate;
    private String toDate;
    private String status;

    // No-argument constructor
    public LeaveRequest() {}

    // Parameterized constructor
    public LeaveRequest(String requestId, int employeeId, String fromDate, String toDate, String status) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    // Getter and Setter for requestId
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    // Getter and Setter for employeeId
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Getter and Setter for fromDate
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    // Getter and Setter for toDate
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}