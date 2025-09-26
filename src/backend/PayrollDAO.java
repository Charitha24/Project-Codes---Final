package backend;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PayrollDAO {
    private Connection conn;
    public PayrollDAO(Connection conn) { this.conn = conn; }

    public void createPayroll(Payroll pr) throws SQLException {
        String sql = "INSERT INTO Payroll (employeeId, salary, payDate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pr.getEmployeeId());
            stmt.setDouble(2, pr.getSalary());
            stmt.setDate(3, pr.getPayDate());
            stmt.executeUpdate();
        }
    }

    public List<Payroll> getPayrollsByEmployee(int employeeId) throws SQLException {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE employeeId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                payrolls.add(new Payroll(
                        rs.getInt("id"),
                        rs.getInt("employeeId"),
                        rs.getDouble("salary"),
                        rs.getDate("payDate")
                ));
            }
        }
        return payrolls;
    }

    public List<Payroll> getAllPayrolls() throws SQLException {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM Payroll";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                payrolls.add(new Payroll(
                        rs.getInt("id"),
                        rs.getInt("employeeId"),
                        rs.getDouble("salary"),
                        rs.getDate("payDate")
                ));
            }
        }
        return payrolls;
    }

    public void updatePayroll(Payroll pr) throws SQLException {
        String sql = "UPDATE Payroll SET salary=?, payDate=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, pr.getSalary());
            stmt.setDate(2, pr.getPayDate());
            stmt.setInt(3, pr.getId());
            stmt.executeUpdate();
        }
    }

    public void deletePayroll(int id) throws SQLException {
        String sql = "DELETE FROM Payroll WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public void processPayroll(int employeeId) {
        // Example: Insert a payroll record for the employee with a dummy salary & today's date
        double salary = 50000.00; // You would get this from your business logic or employee data
        Date payDate = new Date(System.currentTimeMillis());

        String sql = "INSERT INTO payroll (employeeId, salary, payDate) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setDouble(2, salary);
            stmt.setDate(3, payDate);
            stmt.executeUpdate();
            System.out.println("Payroll processed for employeeId: " + employeeId);
        } catch (SQLException e) {
            System.err.println("Error processing payroll for employeeId " + employeeId);
            e.printStackTrace();
        }
    }
}
