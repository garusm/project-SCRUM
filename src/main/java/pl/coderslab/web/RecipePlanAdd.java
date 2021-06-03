package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipePlanAdd", value = "/app/recipe/plan/add")
public class RecipePlanAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.findAll();
        request.setAttribute("recipe", recipeList);

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAll();
        request.setAttribute("choosePlan", planList);

        getServletContext().getRequestDispatcher("/recipePlanAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer plan = Integer.parseInt(request.getParameter("choosePla"));
        String name = request.getParameter("name");
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer recipie = Integer.parseInt(request.getParameter("recipie"));
        Integer day = Integer.parseInt(request.getParameter("day"));

        System.out.println(plan);
        System.out.println(name);
        System.out.println(number);
        System.out.println(recipie);
        System.out.println(day);

        RecipePlan recipePlan = new RecipePlan(recipie, name, number, day, plan);
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.create(recipePlan);

        getServletContext().getRequestDispatcher("/recipePlanAdd.jsp").forward(request, response);
    }
}
