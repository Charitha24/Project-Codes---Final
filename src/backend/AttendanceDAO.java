package backend;

import java.sql.*;
import java.util.*;

public class AttendanceDAO {
    private Connection conn;
    public AttendanceDAO(Connection conn) { this.conn = conn; }

    public void markAttendance(Attendance att) throws SQLException {
        String sql = "INSERT INTO Attendance (employeeId, date, inTime, outTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, att.getEmployeeId());
            stmt.setDate(2, att.getDate());
            stmt.setTime(3, att.getInTime());
            stmt.setTime(4, att.getOutTime());
            stmt.executeUpdate();
        }
    }

    public List<Attendance> getAttendanceByEmployee(int employeeId) throws SQLException {
        List<Attendance> records = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE employeeId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    records.add(new Attendance(
                            rs.getInt("id"),
                            rs.getInt("employeeId"),
                            rs.getDate("date"),
                            rs.getTime("inTime"),
                            rs.getTime("outTime")
                    ));
                }
            }
        }
        return records;
    }
}
