package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PlanAdd", urlPatterns = "/app/plan/add")
public class PlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String planName = request.getParameter("pName");
        String planDescription = request.getParameter("pDescription");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        int adminId = admin.getId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();

        Plan newPlan = new Plan(planName, planDescription, dateFormat.format(dateobj), adminId);
        PlanDao pd = new PlanDao();

        pd.create(newPlan);

        response.sendRedirect("/app/plan/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/planAdd.jsp").forward(request, response);
    }
}
