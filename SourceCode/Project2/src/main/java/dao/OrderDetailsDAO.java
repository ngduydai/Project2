package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.OrderDetailsModel;

public class OrderDetailsDAO extends BaseDAO {
    public static OrderDetailsModel order = new OrderDetailsModel();
    
    public static List<OrderDetailsModel> getOrderDetails_Home() {
        List<OrderDetailsModel> data = new ArrayList<>();
        order.setOrderId(0);
        Connect();
        String sql = "SELECT * FROM project2.orders INNER JOIN project2.order_details ON orders.id = order_details.order_id INNER JOIN project2.menu ON order_details.menu_id = menu.id WHERE table_id = " + TableDAO.table.getId() + " AND status_id != 1";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                OrderDetailsModel e = new OrderDetailsModel(
                    set.getInt("order_details.id"),
                    set.getInt("order_details.order_id"),
                    set.getInt("quantity"),
                    set.getInt("unit_price"),
                    set.getInt("into_money"),
                    set.getString("note"),
                    set.getString("menu_name")
                );
                data.add(e);
                order.setOrderId(set.getInt("order_details.order_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }
    
    public static List<OrderDetailsModel> getOrderDetails_Statistic(int id) {
        List<OrderDetailsModel> data = new ArrayList<>();
        Connect();
        String sql = "SELECT * FROM project2.order_details INNER JOIN project2.menu ON order_details.menu_id = menu.id WHERE order_id = " + id;
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                OrderDetailsModel e = new OrderDetailsModel(
                    set.getInt("order_details.id"),
                    set.getInt("order_details.order_id"),
                    set.getInt("quantity"),
                    set.getInt("unit_price"),
                    set.getInt("into_money"),
                    set.getString("note"),
                    set.getString("menu_name")
                );
                data.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }
    
    public static void add(JTextField txtQuantity, JTextField txtNote) {
        //Panel Menu
        int id = OrderDAO.lastID();
        if (txtQuantity.getText().isBlank()) {
            txtQuantity.setText("1");
        }
        Connect();
        String sql = "SELECT * FROM project2.order_details WHERE order_id = " + order.getOrderId() + " AND menu_id = " + MenuDAO.menu.getId();
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int quantity = set.getInt("quantity") + Integer.parseInt(txtQuantity.getText());
                sql = "UPDATE project2.order_details SET quantity = ?, unit_price = ?, into_money = ? WHERE order_id = " + order.getOrderId() + " AND menu_id = " + MenuDAO.menu.getId();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, quantity);
                statement.setInt(2, MenuDAO.menu.getPrice());
                statement.setInt(3, MenuDAO.menu.getPrice() * quantity);
            }
            else {
                sql = "INSERT INTO project2.order_details(order_id, menu_id, quantity, unit_price, into_money, note) values (?, ?, ?, ?, ?, ?)";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, TableDAO.table.getStatus() == 1 ? id : order.getOrderId());
                statement.setInt(2, MenuDAO.menu.getId());
                statement.setInt(3, Integer.parseInt(txtQuantity.getText()));
                statement.setInt(4, MenuDAO.menu.getPrice());
                statement.setInt(5, MenuDAO.menu.getPrice() * Integer.parseInt(txtQuantity.getText()));
                statement.setString(6, txtNote.getText());
            }
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
    
    public static void update(JTable tableOrder, JTextField txtQuantityUpdate, JTextField txtNoteUpdate) {
        //Panel Order
        if (txtQuantityUpdate.getText().isBlank()) {
            txtQuantityUpdate.setText("1");
        }
        int id = getOrderDetails_Home().get(tableOrder.getSelectedRow()).getId();
        int intoMoney = getOrderDetails_Home().get(tableOrder.getSelectedRow()).getUnitPrice() * Integer.parseInt(txtQuantityUpdate.getText());
        Connect();
        String sql = "UPDATE project2.order_details SET quantity = ?, into_money = ?, note = ? WHERE id = " + id;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(txtQuantityUpdate.getText()));
            statement.setInt(2, intoMoney);
            statement.setString(3, txtNoteUpdate.getText());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
    
    public static void delete(JTable tableOrder) {
        int id = getOrderDetails_Home().get(tableOrder.getSelectedRow()).getId();
        Connect();
        String sql = "DELETE FROM project2.order_details WHERE id = ?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
}