package pl.coderslab.dao;

import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description, created, admin_id) VALUES (?,?,?,?);";
    private static final String READ_PLAN_ON_ID = "SELECT * FROM plan WHERE id = ?;";
    private static final String UPDATE_PLAN_ON_ID = "UPDATE plan SET name = ?, description = ?, created = ?, admin_id = ? WHERE id = ?;";
    public static final String DELETE_PLAN_ON_ID = "DELETE FROM plan WHERE id = ?";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String READ_ALL_ADMIN_PLANS_QUERY = "select * from plan where admin_id=?;";

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());

            insertStm.executeUpdate();
            ResultSet resultSet = insertStm.getGeneratedKeys();

            if (resultSet.next()) {
                plan.setId(resultSet.getInt(1));
                System.out.println(resultSet);
            }
            return plan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Plan read(int planId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(READ_PLAN_ON_ID);
            stmt.setInt(1, planId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Plan plan1 = new Plan(planId);
                plan1.setName(rs.getString(2));
                plan1.setDescription(rs.getString(3));
                plan1.setCreated(rs.getString(4));
                plan1.setAdminId(rs.getInt(5));

                return plan1;
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_PLAN_ON_ID);
            stmt.setString(1, plan.getName());
            stmt.setString(2, plan.getDescription());
            stmt.setString(3, plan.getCreated());
            stmt.setInt(4, plan.getAdminId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(DELETE_PLAN_ON_ID);
            stmt.setInt(1, planId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("połączyłeś się");
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }


    public Plan getTheLastPlan() {
        List<Plan> allPlans = findAll();
        Collections.sort(allPlans, Collections.reverseOrder());
        return allPlans.get(0);
    }

    public int quantityOfAdminRecipes(int adminId) {
        List<Plan> allUsersPlans = allAdminPlans(adminId);
        return allUsersPlans.size();
    }

    public List<Plan> allAdminPlans(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection cone = DbUtil.getConnection();) {
            PreparedStatement pre = cone.prepareStatement(READ_ALL_ADMIN_PLANS_QUERY);
            pre.setInt(1, adminId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }
}
