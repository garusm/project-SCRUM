package pl.coderslab.web;

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
import java.util.*;

@WebServlet(name = "AppDashboard", urlPatterns = "/app")
public class AppDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        int id = admin.getId();

        RecipeDao r = new RecipeDao();
        int quantityOfUsersRecipes = r.quantityOfAdminRecipes(id);
        session.setAttribute("quantityOfUsersRecipes", quantityOfUsersRecipes);

        PlanDao pd = new PlanDao();
        Plan lastPlan = pd.getTheLastPlan();
        session.setAttribute("lastPlan", lastPlan);
        int quantityOfUsersPlans = pd.quantityOfAdminRecipes(id);
        session.setAttribute("quantityOfUsersPlans", quantityOfUsersPlans);

        List<Recipe> adminRecipes = r.allAdminRecipes(id);
        session.setAttribute("adminRecipes", adminRecipes);

        List<Plan> adminPlans = pd.allAdminPlans(id);
        session.setAttribute("adminPlans", adminPlans);

        Map<String, List<RecipePlan>> displayList = new HashMap<>();
        RecipePlanDao rpd = new RecipePlanDao();
        for (int i = 1; i < 8; i++) {

            List<RecipePlan> rp = rpd.findAllRecipesByDay(i, lastPlan.getId());
            if (rp == null || rp.isEmpty()) {

            } else {
                displayList.put(rpd.getDayName(i), rp);
            }
        }

        session.setAttribute("displayList", displayList);
        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);
    }
}
