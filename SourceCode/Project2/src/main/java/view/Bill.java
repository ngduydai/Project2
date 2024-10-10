/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.OrderDetailsDAO;
import dao.TableDAO;
import data.GeneralData;
import data.UserData;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
import view.form.Home;

public class Bill extends javax.swing.JFrame {
    public static DefaultTableModel model;
    
    public Bill() {
        initComponents();
        showBill();
        showBillDetails();
    }
    
    private void showBill() {
        String date = LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear();
        String time = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        lbID.setText("Mã Hóa Đơn: " + OrderDetailsDAO.order.getOrderId());
        lbTable.setText("Bàn: " + TableDAO.table.getName());
        lbUser.setText("Nhân Viên thanh toán: " + UserData.fullname);
        lbTime.setText("Thời gian: " + date + " - " + time);
        total.setText("Tổng: " + GeneralData.formatNumber(Home.total));
    }
    
    private void showBillDetails() {
        model = (DefaultTableModel) billDetails.getModel();
        model.setRowCount(0);
        for (int i = 0; i < OrderDetailsDAO.getOrderDetails_Home().size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                OrderDetailsDAO.getOrderDetails_Home().get(i).getNameMenu(),
                OrderDetailsDAO.getOrderDetails_Home().get(i).getQuantity(),
                GeneralData.formatNumber(OrderDetailsDAO.getOrderDetails_Home().get(i).getUnitPrice()),
                GeneralData.formatNumber(OrderDetailsDAO.getOrderDetails_Home().get(i).getIntoMoney())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbID = new javax.swing.JLabel();
        lbTable = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        billDetails = new javax.swing.JTable();
        total = new javax.swing.JLabel();

        setResizable(false);

        lbID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbID.setText("Mã Hóa Đơn:");

        lbTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTable.setText("Bàn:");

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUser.setText("Nhân Viên Thanh Toán:");

        lbTime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTime.setText("Thời gian:");

        billDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên món", "SL", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(billDetails);
        if (billDetails.getColumnModel().getColumnCount() > 0) {
            billDetails.getColumnModel().getColumn(0).setResizable(false);
            billDetails.getColumnModel().getColumn(0).setPreferredWidth(35);
            billDetails.getColumnModel().getColumn(1).setResizable(false);
            billDetails.getColumnModel().getColumn(1).setPreferredWidth(290);
            billDetails.getColumnModel().getColumn(2).setResizable(false);
            billDetails.getColumnModel().getColumn(2).setPreferredWidth(35);
            billDetails.getColumnModel().getColumn(3).setResizable(false);
            billDetails.getColumnModel().getColumn(3).setPreferredWidth(70);
            billDetails.getColumnModel().getColumn(4).setResizable(false);
            billDetails.getColumnModel().getColumn(4).setPreferredWidth(70);
        }

        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total.setText("Tổng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbUser))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTime))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(total)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbID)
                    .addComponent(lbUser))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTime)
                    .addComponent(lbTable))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(total)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (Login.run) {
                    new Bill().setVisible(true);
                }
                else {
                    new Login().setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable billDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbTable;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
