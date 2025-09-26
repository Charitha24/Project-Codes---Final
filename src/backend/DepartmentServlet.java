package backend;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DepartmentServlet extends HttpServlet {
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        try {
            departmentDAO = new DepartmentDAO(DBUtil.getConnection());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Department> departments = departmentDAO.getAllDepartments();
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("[");
            for (int i = 0; i < departments.size(); i++) {
                Department dep = departments.get(i);
                out.printf("{\"id\":%d,\"name\":\"%s\"}", dep.getId(), dep.getName());
                if (i != departments.size()-1) out.println(",");
            }
            out.println("]");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Department dep = new Department(0, name);
        try {
            departmentDAO.createDepartment(dep);
            resp.getWriter().println("Department created!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Department dep = new Department(id, name);
        try {
            departmentDAO.updateDepartment(dep);
            resp.getWriter().println("Department updated!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            departmentDAO.deleteDepartment(id);
            resp.getWriter().println("Department deleted!");
        } catch (SQLException e) {
            resp.sendError(500, e.getMessage());
        }
    }
}
