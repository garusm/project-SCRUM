package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "PlanList", urlPatterns = "/app/plan/list")

public class PlanList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao pd = new PlanDao();
        List<Plan> planList = pd.findAll();

        Collections.sort(planList, Collections.reverseOrder());


        request.setAttribute("planList", planList);

        getServletContext().getRequestDispatcher("/planList.jsp").forward(request, response);

    }
}
