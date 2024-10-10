package dao;

import data.GeneralData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TableModel;

public class TableDAO extends BaseDAO {
    public static int lastID;
    
    public static TableModel table = new TableModel();

    public static List<TableModel> getTable() {
        List<TableModel> data = new ArrayList<>();
        Connect();
        String sql = "SELECT * FROM project2.table";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                TableModel e = new TableModel(
                        set.getInt("id"),
                        set.getString("table_name"),
                        set.getInt("status_id")
                );
                data.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return data;
    }

    public static void add() {
        Connect();
        String sql = "INSERT INTO project2.table(table_name, status_id) values (?, ?)";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, "B" + (lastID + 1));
            statement.setInt(2, 1);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }

    public static void update() {
        Connect();
        String sql = "UPDATE project2.table SET status_id = ? WHERE id = " + table.getId();
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, table.getStatus());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
    }
}