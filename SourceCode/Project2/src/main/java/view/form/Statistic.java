/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.form;

import dao.OrderDAO;
import dao.OrderDetailsDAO;
import data.GeneralData;
import data.SetupDate;
import javax.swing.table.DefaultTableModel;
import model.OrderDetailsModel;
import model.OrderModel;

public class Statistic extends javax.swing.JPanel {
    public static int total;
    
    public static DefaultTableModel modelOrder;
    
    public static DefaultTableModel modelOrderDetails;
    
    public Statistic() {
        initComponents();
        SetupDate.load = false;
        SetupDate.getYear(cbMonth, cbYear);
        SetupDate.getDay(cbDay, cbMonth, cbYear);
        showOrder(null);
    }
    
    private void showOrder(String search) {
        modelOrder = (DefaultTableModel) tableOrder.getModel();
        modelOrder.setRowCount(0);
        total = 0;
        for (OrderModel order : OrderDAO.getOrder(search)) {
            modelOrder.addRow(new Object[]{
                order.getId(),
                order.getFullname(),
                GeneralData.formatNumber(order.getTotal()),
                order.getTime()
            });
            total += order.getTotal();
        }
        lbTotal.setText("Tổng cộng: " + GeneralData.formatNumber(total));
    }
    
    private void showOrderDetails(int id) {
        modelOrderDetails = (DefaultTableModel) tableOrderDetails.getModel();
        modelOrderDetails.setRowCount(0);
        int stt = 1;
        for (OrderDetailsModel order : OrderDetailsDAO.getOrderDetails_Statistic(id)) {
            modelOrderDetails.addRow(new Object[]{
                stt++,
                order.getNameMenu(),
                order.getQuantity(),
                GeneralData.formatNumber(order.getUnitPrice()),
                GeneralData.formatNumber(order.getIntoMoney())
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrderDetails = new javax.swing.JTable();
        cbDay = new javax.swing.JComboBox<>();
        cbMonth = new javax.swing.JComboBox<>();
        cbYear = new javax.swing.JComboBox<>();
        cbStatistic = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnStatistic = new javax.swing.JButton();

        setBackground(new java.awt.Color(188, 210, 238));

        tableOrder.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MHĐ", "Nhân Viên", "Tổng", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrder.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableOrder.setRowHeight(30);
        tableOrder.getTableHeader().setResizingAllowed(false);
        tableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOrderMouseClicked(evt);
            }
        });
        tableOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableOrderKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setResizable(false);
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableOrder.getColumnModel().getColumn(1).setResizable(false);
            tableOrder.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableOrder.getColumnModel().getColumn(2).setResizable(false);
            tableOrder.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableOrder.getColumnModel().getColumn(3).setResizable(false);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(275);
        }

        tableOrderDetails.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableOrderDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        tableOrderDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableOrderDetails.setRowHeight(30);
        jScrollPane2.setViewportView(tableOrderDetails);
        if (tableOrderDetails.getColumnModel().getColumnCount() > 0) {
            tableOrderDetails.getColumnModel().getColumn(0).setResizable(false);
            tableOrderDetails.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableOrderDetails.getColumnModel().getColumn(1).setResizable(false);
            tableOrderDetails.getColumnModel().getColumn(1).setPreferredWidth(400);
            tableOrderDetails.getColumnModel().getColumn(2).setResizable(false);
            tableOrderDetails.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableOrderDetails.getColumnModel().getColumn(3).setResizable(false);
            tableOrderDetails.getColumnModel().getColumn(3).setPreferredWidth(135);
            tableOrderDetails.getColumnModel().getColumn(4).setResizable(false);
            tableOrderDetails.getColumnModel().getColumn(4).setPreferredWidth(135);
        }

        cbDay.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày" }));
        cbDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbMonth.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng" }));
        cbMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMonthItemStateChanged(evt);
            }
        });

        cbYear.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm" }));
        cbYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbYearItemStateChanged(evt);
            }
        });

        cbStatistic.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbStatistic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tìm theo ngày", "Tìm theo tháng" }));
        cbStatistic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chi tiết Hóa Đơn");

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 139, 0));
        lbTotal.setText("Tổng cộng:");

        btnStatistic.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnStatistic.setText("Thống kê");
        btnStatistic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnStatistic)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStatistic))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbTotal)
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMonthItemStateChanged
        SetupDate.getDay(cbDay, cbMonth, cbYear);
    }//GEN-LAST:event_cbMonthItemStateChanged

    private void cbYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbYearItemStateChanged
        SetupDate.getDay(cbDay, cbMonth, cbYear);
    }//GEN-LAST:event_cbYearItemStateChanged

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        String day = cbDay.getItemAt(cbDay.getSelectedIndex());
        String month = cbMonth.getItemAt(cbMonth.getSelectedIndex());
        String year = cbYear.getItemAt(cbYear.getSelectedIndex());
        switch (cbStatistic.getSelectedIndex()) {
            case 0 -> showOrder(null);
            case 1 -> showOrder(year + "-" + month + "-" + day);
            default -> showOrder(year + "-" + month);
        }
        modelOrderDetails = (DefaultTableModel) tableOrderDetails.getModel();
        modelOrderDetails.setRowCount(0);
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void tableOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrderMouseClicked
        String day = cbDay.getItemAt(cbDay.getSelectedIndex());
        String month = cbMonth.getItemAt(cbMonth.getSelectedIndex());
        String year = cbYear.getItemAt(cbYear.getSelectedIndex());
        if (tableOrder.getSelectedRow() >= 0) {
            switch (cbStatistic.getSelectedIndex()) {
                case 0 -> showOrderDetails(OrderDAO.getOrder(null).get(tableOrder.getSelectedRow()).getId());
                case 1 -> showOrderDetails(OrderDAO.getOrder(year + "-" + month + "-" + day).get(tableOrder.getSelectedRow()).getId());
                default -> showOrderDetails(OrderDAO.getOrder(year + "-" + month).get(tableOrder.getSelectedRow()).getId());
            }
        }
        else {
            modelOrderDetails = (DefaultTableModel) tableOrderDetails.getModel();
            modelOrderDetails.setRowCount(0);
        }
    }//GEN-LAST:event_tableOrderMouseClicked

    private void tableOrderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableOrderKeyReleased
        String day = cbDay.getItemAt(cbDay.getSelectedIndex());
        String month = cbMonth.getItemAt(cbMonth.getSelectedIndex());
        String year = cbYear.getItemAt(cbYear.getSelectedIndex());
        if (tableOrder.getSelectedRow() >= 0) {
            switch (cbStatistic.getSelectedIndex()) {
                case 0 -> showOrderDetails(OrderDAO.getOrder(null).get(tableOrder.getSelectedRow()).getId());
                case 1 -> showOrderDetails(OrderDAO.getOrder(year + "-" + month + "-" + day).get(tableOrder.getSelectedRow()).getId());
                default -> showOrderDetails(OrderDAO.getOrder(year + "-" + month).get(tableOrder.getSelectedRow()).getId());
            }
        }
        else {
            modelOrderDetails = (DefaultTableModel) tableOrderDetails.getModel();
            modelOrderDetails.setRowCount(0);
        }
    }//GEN-LAST:event_tableOrderKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStatistic;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbStatistic;
    private javax.swing.JComboBox<String> cbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTable tableOrderDetails;
    // End of variables declaration//GEN-END:variables
}
