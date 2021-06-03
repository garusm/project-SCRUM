package pl.coderslab.dao;


import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {

    private static final String FIND_PLAN_BY_RECIPE_QUERY = "SELECT * FROM recipe_plan WHERE recipe_id = ?";
    private static final String DELETE_RECIPE_BY_ID_QUERY = "DELETE FROM recipe_plan WHERE plan_id = ? AND recipe_id = ?";

    private final static String GET_RECIPES_BY_DAY_AND_PLAN = "select d.display_order ,d.name ,rp.meal_name ,r.name,r.id\n" +
            "from  recipe r\n" +
            "inner join recipe_plan rp on r.id = rp.recipe_id\n" +
            "inner join day_name d on rp.day_name_id = d.id\n" +
            "where rp.plan_id = ? AND rp.day_name_id = ?\n" +
            "order by rp.display_order";
    private final static String GET_DAY_NAME_BY_ID = "select name from day_name where id = ?";

    public List<RecipePlan> findByRecipe(int recipeId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_PLAN_BY_RECIPE_QUERY)) {
            insertStm.setInt(1, recipeId);
            ResultSet resultSet = insertStm.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipePlanToAdd = new RecipePlan();
                recipePlanToAdd.setId(resultSet.getInt("id"));
                recipePlanToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlanToAdd.setMealName(resultSet.getString("meal_name"));
                recipePlanToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlanToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlanToAdd.setPlanId(resultSet.getInt("plan_id"));
                recipePlanList.add(recipePlanToAdd);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    private static final String CREATE_RECIPE_PLAN = "INSERT INTO recipe_plan\n" +
            "(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?)";

    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_RECIPE_PLAN, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                recipePlan.setId(resultSet.getInt(1));
            }
            return recipePlan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeRecipeByIdMealDay(int planId, int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(DELETE_RECIPE_BY_ID_QUERY)) {
            insertStm.setInt(1, planId);
            insertStm.setInt(2, recipeId);
            insertStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<RecipePlan> findAllRecipesByDay(int i, int planId) {
        List<RecipePlan> recpePlanList = new ArrayList<>();
        try (Connection cone = DbUtil.getConnection();) {
            PreparedStatement pre = cone.prepareStatement(GET_RECIPES_BY_DAY_AND_PLAN);
            pre.setInt(1, planId);
            pre.setInt(2, i);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setDayNameId(resultSet.getInt("d.display_order"));
                recipePlan.setDayName(resultSet.getString("d.name"));
                recipePlan.setMealName(resultSet.getString("rp.meal_name"));
                recipePlan.setRecipeName(resultSet.getString("r.name"));
                recipePlan.setRecipeId(resultSet.getInt("r.id"));
                recpePlanList.add(recipePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recpePlanList;
    }

    public static String getDayName(int id) {
        String dayName = "";
        try (Connection cone = DbUtil.getConnection();) {
            PreparedStatement pre = cone.prepareStatement(GET_DAY_NAME_BY_ID);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                dayName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayName;
    }
}