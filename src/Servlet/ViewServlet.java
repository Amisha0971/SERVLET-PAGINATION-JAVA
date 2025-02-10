package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.EmpDao;
import Model.Emp;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String spageid = request.getParameter("page");
        String name= request.getParameter("name");
        String salaryParam =request.getParameter("salary");
        int pageid = Integer.parseInt(spageid);
        int total = 5; // Number of records per page
        int start = (pageid - 1) * total; // Calculate starting record

        // Get employees from DAO
        EmpDao empDao = new EmpDao(); // Assuming EmpDao is the DAO class
        List<Emp> le = null;
        try {
            le = empDao.getPaginatedEmployees(start, total); // Correct method to fetch paginated data
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
            return;
        }

        // Generate HTML table for displaying employee records
        out.print("<h1>Page No: " + spageid + "</h1>");
        out.print("<table border='1' cellpadding='4' width='60%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Salary</th></tr>");
        for (Emp e : le) {
            out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getSalary() + "</td></tr>");
        }
        out.print("</table>");

        // Adding pagination links
        out.print("<a href='ViewServlet?page=1'>1</a> ");
        out.print("<a href='ViewServlet?page=2'>2</a> ");
        out.print("<a href='ViewServlet?page=3'>3</a> ");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
