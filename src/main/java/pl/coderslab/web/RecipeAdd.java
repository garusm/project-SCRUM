package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RecipeAdd", value = "/app/recipe/add")
public class RecipeAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/recipeAdd.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();

        Recipe recipe = new Recipe();
        recipe.setName(request.getParameter("name"));
        recipe.setDescription(request.getParameter("description"));
        recipe.setPreparation_time(Integer.parseInt(request.getParameter("preparationTime")));
        recipe.setPreparation(request.getParameter("preparation"));
        recipe.setIngredients(request.getParameter("ingredients"));
        recipe.setAdmin_id(admin.getId());
        recipe.setUpdated(dateFormat.format(dateobj));
        recipe.setCreated(dateFormat.format(dateobj));

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.create(recipe);

        response.sendRedirect("/");

    }
}
