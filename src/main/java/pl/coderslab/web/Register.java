package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.xml.transform.sax.SAXResult;
import java.io.IOException;

@WebServlet(name = "Registration", urlPatterns = "/register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if (password.equals(repassword)) {
            AdminDao adminDao = new AdminDao();
            if (adminDao.validatePassword(password)) {
                Admin newAdmin = new Admin(firstName, lastName, email, password, 0, 0);
                adminDao.create(newAdmin);
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/register");
            }
        } else {
            response.sendRedirect("/register");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
