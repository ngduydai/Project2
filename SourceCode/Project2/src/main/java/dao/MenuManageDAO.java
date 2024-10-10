/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.MenuManageModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MenuManageDAO extends BaseDAO {
    public static List<String> getCat(int id) {
        List<String> tableList = new ArrayList<>();
        Connect();
        try {
            String sql = "SELECT * FROM category WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
               String cat = set.getString("category_name");
               tableList.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return tableList;
    }
    public static List<MenuManageModel> getList() {
        List<MenuManageModel> dataList = new ArrayList<>();
        Connect();
        try {
            String sql = "SELECT * FROM menu";
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                MenuManageModel m = new MenuManageModel(
                        set.getInt("id"),
                        set.getInt("category_id"),
                        set.getString("menu_name"),
                        set.getInt("price"),
                        set.getString("image"));
                dataList.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return dataList;
    }
    public static void update(MenuManageModel m) {
        
        Connect();
        try {
            String sql = "Update menu SET category_id = ?,menu_name = ? , price = ? , image =? WHERE id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, m.getCategory_id());
            statement.setString(2, m.getName());
            statement.setInt(3, m.getPrice());
            statement.setString(4, m.getImg());
            statement.setInt(5, m.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }

    public static void delete(int id) {
        Connect();
        try {
            String sql = "delete from menu WHERE id =?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }

    public static void insert(MenuManageModel menus) {
        Connect();
        try {
            String sql = "insert into menu(category_id,menu_name,price,image) VALUES (?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, menus.getCategory_id());
            statement.setString(2, menus.getName());
            statement.setInt(3, menus.getPrice());
            statement.setString(4, menus.getImg());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
}
