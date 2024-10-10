package dao;

import data.UserData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderModel;
import view.form.Home;

public class OrderDAO extends BaseDAO {
    public static List<OrderModel> getOrder(String search) {
        List<OrderModel> data = new ArrayList<>();
        Connect();
        String sql = "SELECT * FROM project2.orders INNER JOIN project2.user ON orders.user_id = user.id WHERE status_id != 2 AND time LIKE '%" + search + "%'";
        if (search == null) {
            sql = "SELECT * FROM project2.orders INNER JOIN project2.user ON orders.user_id = user.id WHERE status_id != 2 ORDER BY orders.id ASC";
        }
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                OrderModel e = new OrderModel(
                    set.getInt("id"),
                    set.getInt("user_id"),
                    set.getInt("table_id"),
                    set.getInt("status_id"),
                    set.getInt("total"),
                    set.getString("fullname"),
                    set.getTimestamp("time")
                );
                data.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }
    
    public static int lastID() {
        //Lấy giá trị id cuối cùng của bảng orders
        int id = 0;
        Connect();
        String sql = "SELECT * FROM project2.orders";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                id = set.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return id;
    }
    
    public static void add() {
        Connect();
        String sql = "INSERT INTO project2.orders(user_id, table_id, status_id, total, time) values (?, ?, 2, 0, ?)";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, UserData.id);
            statement.setInt(2, TableDAO.table.getId());
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
    
    public static void update() {
        Connect();
        String sql = "UPDATE project2.orders SET status_id = ?, total = ? WHERE id = " + OrderDetailsDAO.order.getOrderId();
        if (TableDAO.table.getStatus() == 3) {
            sql = "UPDATE project2.orders SET status_id = ?, total = ?, time = ? WHERE id = " + OrderDetailsDAO.order.getOrderId();
        }
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, TableDAO.table.getStatus());
            statement.setInt(2, Home.total);
            if (TableDAO.table.getStatus() == 3) {
                statement.setTimestamp(3, Timestamp.from(Instant.now()));
            }
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
}