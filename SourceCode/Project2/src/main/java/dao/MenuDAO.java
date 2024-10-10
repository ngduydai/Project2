package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MenuModel;

public class MenuDAO extends BaseDAO {
    public static MenuModel menu = new MenuModel();
    
    public static List<String> getCategory() {
        List<String> data = new ArrayList<>();
        Connect();
        String sql = "SELECT * FROM project2.category";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                data.add(set.getString("category_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }
    
    public static List<MenuModel> getMenu() {
        List<MenuModel> data = new ArrayList<>();
        Connect();
        String sql = "SELECT * FROM project2.menu WHERE category_id = " + menu.getCategoryId();
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                MenuModel e = new MenuModel(
                    set.getInt("id"),
                    set.getInt("category_id"),
                    set.getString("menu_name"),
                    set.getInt("price"),
                    set.getString("image")
                );
                data.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }
}