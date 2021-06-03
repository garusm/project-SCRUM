package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminDao {
    private static Pattern CHECK_PASSWORD = Pattern.compile("^(?=.*[a-z]).{6,}$");
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String READ_ADMIN_ON_ID = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_ON_ID = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";
    public static final String DELETE_ADMIN_ON_ID = "DELETE FROM admins WHERE id = ?";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, hashPassword(admin.getPassword()));
            insertStm.setInt(5, admin.getSuperadmin());
            insertStm.setInt(6, admin.getEnable());

            insertStm.executeUpdate();
            ResultSet resultSet = insertStm.getGeneratedKeys();

            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
                System.out.println(resultSet);
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Admin read(int adminId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(READ_ADMIN_ON_ID);
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Admin admin1 = new Admin(adminId);
                admin1.setFirstName(rs.getString(2));
                admin1.setLastName(rs.getString(3));
                admin1.setEmail(rs.getString(4));
                admin1.setPassword(rs.getString(5));
                admin1.setSuperadmin(rs.getInt(6));
                admin1.setEnable(rs.getInt(7));

                return admin1;
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void update(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_ADMIN_ON_ID);
            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setInt(5, admin.getSuperadmin());
            stmt.setInt(6, admin.getEnable());
            stmt.setInt(7, admin.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int adminId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(DELETE_ADMIN_ON_ID);
            stmt.setInt(1, adminId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("połączyłeś się");
            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }


    public static Admin login(String adminEmail, String adminPassword) {
        List<Admin> allAdmins = findAll();
        for (Admin admin : allAdmins) {
            if (admin.getEmail().equalsIgnoreCase(adminEmail)) {
                System.out.println("true " + admin.getEmail());
                if (BCrypt.checkpw(adminPassword, admin.getPassword())) {
                    return admin;
                }

            }
        }
        return null;
    }

    public static boolean validatePassword(String password) {
        Matcher matcher = CHECK_PASSWORD.matcher(password);
        return matcher.matches();
    }

}



