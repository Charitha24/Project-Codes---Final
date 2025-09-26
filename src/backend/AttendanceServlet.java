package backend;



import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class AttendanceServlet extends HttpServlet {
    private AttendanceDAO attendanceDAO;

    @Override
    public void init() throws ServletException {
        try {
            attendanceDAO = new AttendanceDAO(DBUtil.getConnection());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        Date date = Date.valueOf(req.getParameter("date"));
        Time inTime = Time.valueOf(req.getParameter("inTime"));
        Time outTime = Time.valueOf(req.getParameter("outTime"));
        Attendance att = new Attendance(0, employeeId, date, inTime, outTime);

        try {
            attendanceDAO.markAttendance(att);
            resp.getWriter().println("Attendance marked!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        try {
            List<Attendance> records = attendanceDAO.getAttendanceByEmployee(employeeId);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("[");
            for (int i = 0; i < records.size(); i++) {
                Attendance att = records.get(i);
                out.printf("{\"id\":%d,\"employeeId\":%d,\"date\":\"%s\",\"inTime\":\"%s\",\"outTime\":\"%s\"}", att.getId(), att.getEmployeeId(), att.getDate(), att.getInTime(), att.getOutTime());
                if (i != records.size()-1) out.println(",");
            }
            out.println("]");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }
}
