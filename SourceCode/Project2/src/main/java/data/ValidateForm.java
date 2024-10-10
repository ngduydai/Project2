package data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ValidateForm {
    //Chỉ có chữ, khoảng trắng và '
    public static String fullnameRegex = "^[a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ'\\s]{1,}$";
    
    //Tên người dùng phải bắt đầu bằng chữ và có từ 3-15 ký tự. Tên người dùng có thể chứa các ký tự đặc biệt: dấu gạch dưới và dấu chấm.
    public static String usernameRegex = "^[a-zA-Z][\\w._]{2,14}$";
    
    //Mật khẩu phải bắt đầu bằng chữ in hoa và có từ 6-30 ký tự. Mật khẩu có ít nhất 1 ký tự đặc biệt.
    public static String passwordRegex = "^[A-Z](?=.*[`~!@#$%^&*()-=_+\\[\\]{}|;:,.<>\\/?\\\"'])[\\w`~!@#$%^&*()-=_+\\[\\]{}|;:,.<>\\/?\\\"']{5,29}$";
    
    public static String emailRegex = "^[\\w_.]{5,33}[@][\\w]{2,9}[.][a-zA-Z]{2,9}$";
    
    public static String phoneRegex = "^[0][0-9]{9}$";
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, data);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static boolean ValidateLogin(JFrame frame, JTextField txtUser, JPasswordField txtPass) {
        if (txtUser.getText().isBlank()) {
            txtUser.requestFocus();
            JOptionPane.showMessageDialog(frame, "Xin vui lòng nhập tên người dùng!", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtPass.getText().isBlank()) {
            txtPass.requestFocus();
            JOptionPane.showMessageDialog(frame, "Xin vui lòng nhập mật khẩu!", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static boolean ValidateChangePassword(JFrame frame, JTextField txtUser, JPasswordField txtPass, JPasswordField txtPassNew, JPasswordField txtPassConfirm) {
        if (!ValidateLogin(frame, txtUser, txtPass)) {
            return false;
        }
        if (txtPassNew.getText().isBlank() || !Pattern.matches(passwordRegex, txtPassNew.getText())) {
            txtPassNew.requestFocus();
            JOptionPane.showMessageDialog(frame, "Mật khẩu mới phải bắt đầu bằng chữ in hoa và có từ 6-30 ký tự. Mật khẩu có ít nhất 1 ký tự đặc biệt.", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtPassNew.getText().equals(txtPass.getText())) {
            txtPassNew.requestFocus();
            JOptionPane.showMessageDialog(frame, "Mật khẩu mới phải khác với mật khẩu cũ!", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtPassConfirm.getText().equals(txtPassNew.getText())) {
            txtPassConfirm.requestFocus();
            JOptionPane.showMessageDialog(frame, "Mật khẩu xác nhận chưa khớp với mật khẩu mới!", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}