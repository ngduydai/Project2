package model;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainModel {
    public JLabel label;
    
    public JPanel panel;

    public MainModel() {
    }

    public MainModel(JLabel label, JPanel panel) {
        this.label = label;
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}