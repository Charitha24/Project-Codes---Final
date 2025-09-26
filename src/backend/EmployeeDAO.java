package backend;

import java.sql.*;
import java.util.*;
import backend.Employee;


public class EmployeeDAO {
    private Connection conn;
    public EmployeeDAO(Connection conn) { this.conn = conn; }

    public void createEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO Employee (name, department, contact) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getDepartment());
            stmt.setString(3, emp.getContact());
            stmt.executeUpdate();
        }
    }

    public Employee getEmployee(int id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("contact")
                );
            } else return null;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("contact")
                ));
            }
        }
        return employees;
    }

    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE Employee SET name=?, department=?, contact=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getDepartment());
            stmt.setString(3, emp.getContact());
            stmt.setInt(4, emp.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}