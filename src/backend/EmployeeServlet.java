package backend;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import backend.EmployeeDAO;
import backend.Employee;
import backend.DBUtil;



public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
            employeeDAO = new EmployeeDAO(DBUtil.getConnection());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("[");
            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                out.printf("{\"id\":%d,\"name\":\"%s\",\"department\":\"%s\",\"contact\":\"%s\"}", emp.getId(), emp.getName(), emp.getDepartment(), emp.getContact());
                if (i != employees.size()-1) out.println(",");
            }
            out.println("]");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String contact = req.getParameter("contact");
        Employee emp = new Employee(0, name, department, contact);
        try {
            employeeDAO.createEmployee(emp);
            resp.getWriter().println("Employee created!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String contact = req.getParameter("contact");
        Employee emp = new Employee(id, name, department, contact);
        try {
            employeeDAO.updateEmployee(emp);
            resp.getWriter().println("Employee updated!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            employeeDAO.deleteEmployee(id);
            resp.getWriter().println("Employee deleted!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }
}