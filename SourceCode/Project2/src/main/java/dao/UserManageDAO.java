/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.UserManageModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class UserManageDAO extends BaseDAO {

    public static List<UserManageModel> getList() {
        List<UserManageModel> dataList = new ArrayList<>();
        Connect();
        try {
            String sql = "SELECT * FROM user";
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                UserManageModel u;
                u = new UserManageModel(
                        set.getInt("id"),
                        set.getInt("role_id"),
                        set.getString("fullname"),
                        set.getString("birthday"),
                        set.getString("gender"),
                        set.getString("phone_number"),
                        set.getString("address"),
                        set.getInt("salary"),
                        set.getString("email"),
                        set.getString("username"),
                        set.getString("password"),
                        set.getString("work"));
                dataList.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return dataList;
    }

    public static void insert(UserManageModel user) {
        Connect();
        try {
            String sql = "Insert into user(role_id ,username ,password ,fullname ,birthday ,gender ,email ,phone_number ,address ,salary ,work) VALUES (2 ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?) ";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.setString(4, user.getBirthday());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPhone());
            statement.setString(8, user.getAddress());
            statement.setInt(9, user.getSalary());
            statement.setString(10, user.getWork());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
public static void update(UserManageModel u, JTextField password){
    Connect();
        try {
            String sql = "update user SET username = ? , fullname = ? , birthday= ? , gender = ? , email = ? , phone_number = ? , address = ? , salary = ? WHERE id = " + u.getId();
            if (!password.getText().isBlank()) {
                sql = "update user SET username = ? , fullname = ? , birthday= ? , gender = ? , email = ? , phone_number = ? , address = ? , salary = ?, password = ? WHERE id = " + u.getId();
            }
            statement = conn.prepareStatement(sql);
             statement.setString(1, u.getUsername());
             statement.setString(2, u.getFullname());
             statement.setString(3, u.getBirthday());
             statement.setString(4, u.getGender());
             statement.setString(5, u.getEmail());
             statement.setString(6, u.getPhone());
             statement.setString(7, u.getAddress());
             statement.setInt(8, u.getSalary());
             if (!password.getText().isBlank()) {
                 statement.setString(9, u.getPassword());
             }
             statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
}
    public static void delete(int id) {
        Connect();
        try {
            String sql = "delete form user WHERE id=?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
}
