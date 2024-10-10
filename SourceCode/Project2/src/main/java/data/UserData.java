package data;

import dao.BaseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import view.Login;
import view.Main;

public class UserData extends BaseDAO {
    public static int id;
    
    public static int role;
    
    public static String username;
    
    public static String fullname;
    
    public static boolean checkAccount(JFrame frame, JTextField txtUser, JPasswordField txtPass) {
        boolean check = true;
        Connect();
        String sql = "SELECT * FROM project2.user WHERE username = '" + txtUser.getText() + "'";
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                if (ValidateForm.getMD5(txtPass.getText()).equals(set.getString("password"))) {
                    id = set.getInt("id");
                    role = set.getInt("role_id");
                    username = set.getString("username");
                    fullname = set.getString("fullname");
                    check = true;
                }
                else {
                    check = false;
                    txtPass.requestFocus();
                    JOptionPane.showMessageDialog(frame, "Mật khẩu không chính xác!", null, JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                check = false;
                txtUser.requestFocus();
                JOptionPane.showMessageDialog(frame, "Tên người dùng không tồn tại!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return check;
    }
    
    public static boolean checkAccount(JPanel panel, JTextField txtUser, int id, int type) {
        boolean check = true;
        Connect();
        String sql = "SELECT * FROM project2.user WHERE username = '" + txtUser.getText() + "'";
        if (type == 1) {
            sql = "SELECT * FROM project2.user WHERE username = '" + txtUser.getText() + "' AND id != " + id;
        }
        try {
            statement = conn.prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                check = false;
                JOptionPane.showMessageDialog(panel, "Tên người dùng đã tồn tại!", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disconnect();
        return check;
    }
    
    public static void Login(JFrame frame, JTextField txtUser, JPasswordField txtPass) {
        if (ValidateForm.ValidateLogin(frame, txtUser, txtPass) && checkAccount(frame, txtUser, txtPass)) {
            frame.setVisible(false);
            new Main().setVisible(Login.run);
        }
    }

    public static void ChangePassword(JFrame frame, JTextField txtUser, JPasswordField txtPass, JPasswordField txtPassNew, JPasswordField txtPassConfirm) {
        if (ValidateForm.ValidateChangePassword(frame, txtUser, txtPass, txtPassNew, txtPassConfirm) && checkAccount(frame, txtUser, txtPass)) {
            Connect();
            String sql = "UPDATE project2.user SET password = ? WHERE username = '" + txtUser.getText() + "'";
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, ValidateForm.getMD5(txtPassNew.getText()));
                statement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
            }
            Disconnect();
            JOptionPane.showMessageDialog(frame, "Đổi mật khẩu thành công!", null, JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            new Login().setVisible(Login.run);
        }
    }
}