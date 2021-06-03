package pl.coderslab.dao;


import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY =
            "UPDATE recipe SET name = ?, ingredients = ?, description = ?, updated = ?, created = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";
    private static final String READ_ALL_ADMIN_RECIPES_QUERY = "select * from recipe where admin_id=?;";

    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_RECIPE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getIngredients());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setString(4, recipe.getCreated());
            preparedStatement.setString(5, recipe.getUpdated());
            preparedStatement.setInt(6, recipe.getPreparation_time());
            preparedStatement.setString(7, recipe.getPreparation());
            preparedStatement.setInt(8, recipe.getAdmin_id());

            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Recipe read(int recipeID) {
        Recipe recipe = new Recipe();
        try (Connection con = DbUtil.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(READ_RECIPE_QUERY);
            stmt.setInt(1, recipeID);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                recipe.setId(result.getInt("id"));
                recipe.setName(result.getString("name"));
                recipe.setDescription(result.getString("description"));
                recipe.setIngredients(result.getString("ingredients"));
                recipe.setCreated(result.getString("created"));
                recipe.setUpdated(result.getString("updated"));
                recipe.setPreparation(result.getString("preparation"));
                recipe.setPreparation_time(result.getInt("preparation_time"));
                recipe.setAdmin_id(result.getInt("admin_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public void delete(int deleteID) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement prep = conn.prepareStatement(DELETE_RECIPE_QUERY);
            prep.setInt(1, deleteID);
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection cone = DbUtil.getConnection();
             PreparedStatement pre = cone.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(rs.getInt("id"));
                recipeToAdd.setName(rs.getString("name"));
                recipeToAdd.setDescription(rs.getString("description"));
                recipeToAdd.setIngredients(rs.getString("ingredients"));
                recipeToAdd.setPreparation(rs.getString("preparation"));
                recipeToAdd.setUpdated(rs.getString("updated"));
                recipeToAdd.setPreparation_time(rs.getInt("preparation_time"));
                recipeToAdd.setCreated(rs.getString("created"));
                recipeToAdd.setAdmin_id(rs.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public void update(Recipe recipe) {
        try (Connection cont = DbUtil.getConnection();
             PreparedStatement prstmt = cont.prepareStatement(UPDATE_RECIPE_QUERY)) {
            prstmt.setInt(9, recipe.getId());
            prstmt.setString(2, recipe.getName());
            prstmt.setString(3, recipe.getIngredients());
            prstmt.setString(4, recipe.getDescription());
            prstmt.setString(6, recipe.getUpdated());
            prstmt.setString(1, recipe.getCreated());
            prstmt.setDouble(7, recipe.getPreparation_time());
            prstmt.setString(5, recipe.getPreparation());
            prstmt.setInt(8, recipe.getAdmin_id());

            prstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> allAdminRecipes(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection cone = DbUtil.getConnection();) {
            PreparedStatement pre = cone.prepareStatement(READ_ALL_ADMIN_RECIPES_QUERY);
            pre.setInt(1, adminId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(rs.getInt("id"));
                recipeToAdd.setName(rs.getString("name"));
                recipeToAdd.setDescription(rs.getString("description"));
                recipeToAdd.setIngredients(rs.getString("ingredients"));
                recipeToAdd.setPreparation(rs.getString("preparation"));
                recipeToAdd.setUpdated(rs.getString("updated"));
                recipeToAdd.setPreparation_time(rs.getInt("preparation_time"));
                recipeToAdd.setCreated(rs.getString("created"));
                recipeToAdd.setAdmin_id(rs.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }


    public int quantityOfAdminRecipes(int adminId) {
        List<Recipe> allUsersRecipes = allAdminRecipes(adminId);
        return allUsersRecipes.size();
    }

}



