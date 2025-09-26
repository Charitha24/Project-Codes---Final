package backend;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class PayrollServlet extends HttpServlet {
    private PayrollDAO payrollDAO;

    @Override
    public void init() throws ServletException {
        try {
            payrollDAO = new PayrollDAO(DBUtil.getConnection());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Payroll> payrolls = payrollDAO.getAllPayrolls();
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("[");
            for (int i = 0; i < payrolls.size(); i++) {
                Payroll pr = payrolls.get(i);
                out.printf("{\"id\":%d,\"employeeId\":%d,\"salary\":%.2f,\"payDate\":\"%s\"}", pr.getId(), pr.getEmployeeId(), pr.getSalary(), pr.getPayDate());
                if (i != payrolls.size()-1) out.println(",");
            }
            out.println("]");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        Date payDate = Date.valueOf(req.getParameter("payDate"));
        Payroll pr = new Payroll(0, employeeId, salary, payDate);
        try {
            payrollDAO.createPayroll(pr);
            resp.getWriter().println("Payroll created!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        Date payDate = Date.valueOf(req.getParameter("payDate"));
        Payroll pr = new Payroll(id, 0, salary, payDate);
        try {
            payrollDAO.updatePayroll(pr);
            resp.getWriter().println("Payroll updated!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            payrollDAO.deletePayroll(id);
            resp.getWriter().println("Payroll deleted!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }
}
