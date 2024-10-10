package data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.MainModel;

public class MainController {
    public static JLabel label;
    
    public static List<MainModel> listScreen = new ArrayList<>();
    
    public static void LoadScreen(JPanel panelSelect, JPanel panelView, JLabel labelSelect, JPanel panelHome, JPanel panelStatistic, JPanel panelMenu, JPanel panelUser, JLabel labelHome, JLabel labelStatistic, JLabel labelMenu, JLabel labelUser, JLabel lbFullname, JLabel lbUser) {
        label = labelSelect;
        panelView.removeAll();
        panelView.setLayout(new BorderLayout());
        panelView.add(panelSelect);
        panelView.repaint();
        panelHome.setBackground(new Color(188, 210, 238));
        
        listScreen.add(new MainModel(labelHome, panelHome));
        listScreen.add(new MainModel(labelStatistic, panelStatistic));
        listScreen.add(new MainModel(labelMenu, panelMenu));
        listScreen.add(new MainModel(labelUser, panelUser));
    }
    
    public static void mouseEntered(JPanel panel) {
        panel.setBackground(new Color(188, 210, 238));
    }
    
    public static void mouseExited(JPanel panel, JLabel labelSelect) {
        if (label != labelSelect) {
            panel.setBackground(new Color(162, 181, 205));
        }
    }
    
    public static void mouserClicked(JPanel panelSelect, JPanel panelView, JLabel labelSelect) {
        label = labelSelect;
        panelView.removeAll();
        panelView.setLayout(new BorderLayout());
        panelView.add(panelSelect);
        panelView.validate();
        panelView.repaint();
        setChangeBackground();
    }
    
    public static void setChangeBackground() {
        for (MainModel view : listScreen) {
            if (view.getLabel() == label) {
                view.getPanel().setBackground(new Color(188, 210, 238));
            }
            else {
                view.getPanel().setBackground(new Color(162, 181, 205));
            }
        }
    }
}