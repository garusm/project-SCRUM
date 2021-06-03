package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PlanDetails", urlPatterns = "/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int planId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();

        PlanDao pd = new PlanDao();
        Plan plan = pd.read(planId);
        session.setAttribute("plan", plan);

        Map<String, List<RecipePlan>> displayList = new HashMap<>();
        RecipePlanDao rpd = new RecipePlanDao();

        for (int i = 1; i < 8; i++) {
            List<RecipePlan> rp = rpd.findAllRecipesByDay(i, planId);
            for (RecipePlan recipe : rp) {
                System.out.println(recipe.getMealName());
            }

            if (rp == null || rp.isEmpty()) {

            } else {
                displayList.put(rpd.getDayName(i), rp);
            }
        }


        session.setAttribute("displayList", displayList);


        getServletContext().getRequestDispatcher("/planDetails.jsp").forward(request, response);
    }
}
