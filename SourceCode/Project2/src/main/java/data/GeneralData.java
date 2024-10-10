package data;

import dao.TableDAO;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneralData {
    public static String pathFolder = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\Project2\\src\\main\\java\\img\\";
    
    public static String extensionFile = ".jpg";
    
    public static String pathFile;
    
    public static void LoadForm(JPanel panelUser, JLabel labelUser, JLabel lbFullname, JLabel lbUser) {
        lbFullname.setText(UserData.fullname);
        lbUser.setText(UserData.username);
        if (UserData.role != 1) {
            panelUser.setVisible(false);
            labelUser.setVisible(false);
        }
    }
    
    public static int countFile() {
        File[] files = new File(GeneralData.pathFolder).listFiles();
        if (files == null) {
            return 1;
        }
        return files.length + 1;
    }

    public static void copyFile(String pathName) {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            File oldFile = new File(pathName);
            File newFile = new File(GeneralData.pathFolder + countFile() + GeneralData.extensionFile);
            inStream = new FileInputStream(oldFile);
            outStream = new FileOutputStream(newFile);

            int length;
            byte[] b = new byte[1024];
            while ((length = inStream.read(b)) > 0) {
                outStream.write(b, 0, length);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeneralData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(GeneralData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void chooseFile(JLabel img) {
        JFileChooser choose = new JFileChooser();
        choose.showOpenDialog(null);
        pathFile = choose.getSelectedFile().getAbsolutePath();
        File newFile = new File(pathFolder + countFile() + extensionFile);
        img.setIcon(setIMG(pathFile));
        img.setText(newFile.getName());
    }
    
    public static ImageIcon setIMG(String img) {
        ImageIcon myImage = new ImageIcon(img);
        Image scaleIMG = myImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(scaleIMG);
        return image;
    }
    
    public static String formatNumber(int number) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(number);
    }
    
    public static void setStatusTable() {
        switch (TableDAO.table.getStatus()) {
            case 1 -> TableDAO.table.setStatus(2);
            case 2 -> TableDAO.table.setStatus(3);
            default -> TableDAO.table.setStatus(1);
        }
    }

    public static void textKeyPressed(KeyEvent evt, JTextField text) {
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || (evt.getKeyChar() >= KeyEvent.VK_0 && evt.getKeyChar() <= KeyEvent.VK_9)) {
            text.setEditable(true);
        } else {
            text.setEditable(false);
        }
    }

    public static void minusActionPerformed(JTextField text) {
        if (text.getText().isBlank()) {
            text.setText("1");
        } else {
            int quantity = Integer.parseInt(text.getText());
            if (quantity > 1) {
                text.setText(quantity - 1 + "");
            }
        }
    }

    public static void plusActionPerformed(JTextField text) {
        if (text.getText().isBlank()) {
            text.setText("1");
        } else {
            int quantity = Integer.parseInt(text.getText());
            text.setText(quantity + 1 + "");
        }
    }
}