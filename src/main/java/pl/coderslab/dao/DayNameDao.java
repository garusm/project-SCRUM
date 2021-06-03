package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    private static final String FIND_ALL_DAYS_QUERY = "SELECT * FROM day_name;";

    public List<DayName> findAll() {
        List<DayName> dayList = new ArrayList<>();
        try (Connection cone = DbUtil.getConnection();
             PreparedStatement pre = cone.prepareStatement(FIND_ALL_DAYS_QUERY);
             ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                DayName dayToAdd = new DayName();
                dayToAdd.setId(rs.getInt("id"));
                dayToAdd.setName(rs.getString("name"));
                dayToAdd.setDisplayOrder(rs.getInt("display_order"));
                dayList.add(dayToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayList;
    }
}
