package backend;

import java.sql.*;
import java.util.*;

public class DepartmentDAO {
    private Connection conn;
    public DepartmentDAO(Connection conn) { this.conn = conn; }

    public void createDepartment(Department dep) throws SQLException {
        String sql = "INSERT INTO Department (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dep.getName());
            stmt.executeUpdate();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM Department";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }
        return departments;
    }

    public void updateDepartment(Department dep) throws SQLException {
        String sql = "UPDATE Department SET name=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dep.getName());
            stmt.setInt(2, dep.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteDepartment(int id) throws SQLException {
        String sql = "DELETE FROM Department WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
