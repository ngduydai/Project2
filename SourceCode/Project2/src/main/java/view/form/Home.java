/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.form;

import dao.MenuDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.TableDAO;
import data.GeneralData;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.MenuModel;
import model.OrderDetailsModel;
import model.TableModel;
import view.Bill;

/**
 *
 * @author Buzinbun
 */
public class Home extends javax.swing.JPanel {
    public static int total;
    
    public static DefaultTableModel model;

    public Home() {
        initComponents();
        LoadHome();
    }

    private void LoadHome() {
        showTable();
        showCategory();
        setVisible();
        showOrderDetails();
    }

    // <editor-fold desc="Panel Table">
    public void actionTable(ActionEvent evt, int id, String name, int status, int type) {
        if (type == 1) {
            if (TableDAO.table.getId() == id) {
                TableDAO.table.setId(0);
                TableDAO.table.setStatus(0);
            } else {
                TableDAO.table.setId(id);
                TableDAO.table.setName(name);
                TableDAO.table.setStatus(status);
            }
        } else {
            TableDAO.add();
            showTable();
        }
        setVisible();
        showOrderDetails();
    }

    public void createTable(int id, String name, int status, int btnX, int btnY, int type) {
        JButton btn = new JButton(name);
        btn.setSize(75, 60);
        btn.setLocation(btnX, btnY);
        btn.setFont(new Font("Segoe UI", 1, 18));
        btn.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));

        if (status == 1) {
            btn.setBackground(new java.awt.Color(242, 242, 242));
        }
        if (status == 2) {
            btn.setBackground(new java.awt.Color(0, 139, 0));
        }
        if (status == 3) {
            btn.setBackground(new java.awt.Color(153, 204, 255));
        }

        panelTable.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                actionTable(evt, id, name, status, type);
            }
        });
    }

    private void showTable() {
        panelTable.removeAll();
        panelTable.repaint();
        int btnX = 0;
        int btnY = 0;
        for (TableModel table : TableDAO.getTable()) {
            createTable(table.getId(), table.getName(), table.getStatus(), btnX, btnY, 1);
            btnX += 85;
            if (table.getId() % 3 == 0) {
                btnX = 0;
                btnY += 68;
            }
            TableDAO.lastID = table.getId();
        }
        createTable(-1, "+", 1, btnX, btnY, 2);
    }
    // </editor-fold>

    // <editor-fold desc="Panel Menu">
    private void showCategory() {
        cbCategory.removeAllItems();
        for (String category : MenuDAO.getCategory()) {
            cbCategory.addItem(category);
        }
    }

    private void showMenu() {
        cbMenu.removeAllItems();
        for (MenuModel menu : MenuDAO.getMenu()) {
            cbMenu.addItem(menu.getName());
        }
    }
    // </editor-fold>

    // <editor-fold desc="Panel Order">
    private void setVisible() {
        if (TableDAO.table.getId() == 0) {
            panelOrder.setVisible(false);
        } else {
            panelOrder.setVisible(true);
        }
        panelOrder.setBorder(BorderFactory.createTitledBorder(null, "Bàn đang chọn: " + TableDAO.table.getName(), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 18)));
    }

    private void showOrderDetails() {
        model = (DefaultTableModel) tableOrder.getModel();
        model.setRowCount(0);
        total = 0;
        for (OrderDetailsModel order : OrderDetailsDAO.getOrderDetails_Home()) {
            model.addRow(new Object[]{
                order.getNameMenu(),
                order.getQuantity(),
                GeneralData.formatNumber(order.getUnitPrice()),
                GeneralData.formatNumber(order.getIntoMoney())
            });
            total += order.getIntoMoney();
        }
        setVisibleDetails();
    }

    public void setVisibleDetails() {
        String text;
        if (TableDAO.table.getStatus() == 2) {
            text = "THANH TOÁN VÀ IN HÓA ĐƠN";
        } else {
            text = "DỌN BÀN";
        }

        if (total > 0) {
            lbTotal.setText("Tổng cộng: " + GeneralData.formatNumber(total));
            lbTotal.setVisible(true);
            btnFinal.setText(text);
            btnFinal.setVisible(true);
        } else {
            lbTotal.setVisible(false);
            btnFinal.setVisible(false);
        }

        if (TableDAO.table.getStatus() == 2 && tableOrder.getSelectedRow() >= 0) {
            lbNameMenu.setText(OrderDetailsDAO.getOrderDetails_Home().get(tableOrder.getSelectedRow()).getNameMenu());
            lbNameMenu.setVisible(true);
            txtNoteUpdate.setText(OrderDetailsDAO.getOrderDetails_Home().get(tableOrder.getSelectedRow()).getNote());
            txtNoteUpdate.setVisible(true);
            txtQuantityUpdate.setText(OrderDetailsDAO.getOrderDetails_Home().get(tableOrder.getSelectedRow()).getQuantity() + "");
            txtQuantityUpdate.setVisible(true);
            btnMinusUpdate.setVisible(true);
            btnPlusUpdate.setVisible(true);
            btnUpdate.setVisible(true);
            btnDelete.setVisible(true);
        } else {
            lbNameMenu.setVisible(false);
            txtNoteUpdate.setVisible(false);
            txtQuantityUpdate.setVisible(false);
            btnMinusUpdate.setVisible(false);
            btnPlusUpdate.setVisible(false);
            btnUpdate.setVisible(false);
            btnDelete.setVisible(false);
        }
    }
    
    public void finish() {
        GeneralData.setStatusTable();
        OrderDAO.update();
        TableDAO.update();
        showOrderDetails();
        showTable();
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbMenu = new javax.swing.JComboBox<>();
        imgMenu = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        txtQuantity = new javax.swing.JTextField();
        btnPlus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNote = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        panelOrder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        lbNameMenu = new javax.swing.JLabel();
        txtNoteUpdate = new javax.swing.JTextField();
        btnMinusUpdate = new javax.swing.JButton();
        txtQuantityUpdate = new javax.swing.JTextField();
        btnPlusUpdate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbTotal = new javax.swing.JLabel();
        btnFinal = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelTable = new javax.swing.JPanel();

        setBackground(new java.awt.Color(188, 210, 238));

        panelMenu.setBackground(new java.awt.Color(188, 210, 238));
        panelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thực đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        panelMenu.setPreferredSize(new java.awt.Dimension(500, 594));

        cbCategory.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoryItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Loại:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Menu:");

        cbMenu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMenuItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Giá:");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(0, 139, 0));
        lbPrice.setText("100,000");

        btnMinus.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMinus.setText("-");
        btnMinus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        txtQuantity.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantity.setText("1");
        txtQuantity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
        });

        btnPlus.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPlus.setText("+");
        btnPlus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Số lượng:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Ghi chú:");

        txtNote.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 139, 0));
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(42, 42, 42)
                                .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(30, 30, 30))
                                    .addGroup(panelMenuLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMenuLayout.createSequentialGroup()
                                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(cbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlus)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMinus)
                    .addComponent(jLabel7)
                    .addComponent(lbPrice)
                    .addComponent(jLabel6))
                .addGap(45, 45, 45)
                .addComponent(imgMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelOrder.setBackground(new java.awt.Color(188, 210, 238));
        panelOrder.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chưa chọn bàn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        panelOrder.setLayout(null);

        tableOrder.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "Số lượng", "Đơn giá", "Thành tiền"
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
        jScrollPane2.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setResizable(false);
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(330);
            tableOrder.getColumnModel().getColumn(1).setResizable(false);
            tableOrder.getColumnModel().getColumn(1).setPreferredWidth(80);
            tableOrder.getColumnModel().getColumn(2).setResizable(false);
            tableOrder.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableOrder.getColumnModel().getColumn(3).setResizable(false);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        panelOrder.add(jScrollPane2);
        jScrollPane2.setBounds(5, 27, 678, 300);

        lbNameMenu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbNameMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNameMenu.setText("Tên món");
        panelOrder.add(lbNameMenu);
        lbNameMenu.setBounds(11, 345, 350, 30);

        txtNoteUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        panelOrder.add(txtNoteUpdate);
        txtNoteUpdate.setBounds(11, 393, 380, 40);

        btnMinusUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMinusUpdate.setText("-");
        btnMinusUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinusUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusUpdateActionPerformed(evt);
            }
        });
        panelOrder.add(btnMinusUpdate);
        btnMinusUpdate.setBounds(502, 346, 39, 29);

        txtQuantityUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtQuantityUpdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityUpdate.setText("1");
        txtQuantityUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtQuantityUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityUpdateKeyPressed(evt);
            }
        });
        panelOrder.add(txtQuantityUpdate);
        txtQuantityUpdate.setBounds(547, 346, 39, 29);

        btnPlusUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPlusUpdate.setText("+");
        btnPlusUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlusUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusUpdateActionPerformed(evt);
            }
        });
        panelOrder.add(btnPlusUpdate);
        btnPlusUpdate.setBounds(592, 346, 39, 29);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(70, 130, 180));
        btnUpdate.setText("Cập nhật");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        panelOrder.add(btnUpdate);
        btnUpdate.setBounds(459, 394, 100, 40);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setText("Xóa");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panelOrder.add(btnDelete);
        btnDelete.setBounds(577, 394, 100, 40);

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 139, 0));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("Tổng cộng:");
        lbTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 139, 0)));
        panelOrder.add(lbTotal);
        lbTotal.setBounds(11, 452, 666, 50);

        btnFinal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnFinal.setForeground(new java.awt.Color(0, 139, 0));
        btnFinal.setText("THANH TOÁN VÀ IN HÓA ĐƠN");
        btnFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });
        panelOrder.add(btnFinal);
        btnFinal.setBounds(180, 520, 329, 69);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(30, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 139, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(30, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(30, 20));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Đã thanh toán");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Đang phục vụ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Trống");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bàn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        panelTable.setBackground(new java.awt.Color(188, 210, 238));
        panelTable.setPreferredSize(new java.awt.Dimension(250, 10000));

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)))
                    .addComponent(panelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoryItemStateChanged
        MenuDAO.menu.setCategoryId(cbCategory.getSelectedIndex() + 1);
        showMenu();
        txtQuantity.setText("1");
        txtNote.setText(null);
    }//GEN-LAST:event_cbCategoryItemStateChanged

    private void cbMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMenuItemStateChanged
        if (cbMenu.getSelectedIndex() > -1) {
            MenuDAO.menu.setId(MenuDAO.getMenu().get(cbMenu.getSelectedIndex()).getId());
            MenuDAO.menu.setImg(MenuDAO.getMenu().get(cbMenu.getSelectedIndex()).getImg());
            MenuDAO.menu.setPrice(MenuDAO.getMenu().get(cbMenu.getSelectedIndex()).getPrice());
            imgMenu.setIcon(GeneralData.setIMG(GeneralData.pathFolder + MenuDAO.menu.getImg()));
            lbPrice.setText(GeneralData.formatNumber(MenuDAO.menu.getPrice()));
        }
        txtQuantity.setText("1");
        txtNote.setText(null);
    }//GEN-LAST:event_cbMenuItemStateChanged

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        GeneralData.textKeyPressed(evt, txtQuantity);
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        GeneralData.minusActionPerformed(txtQuantity);
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        GeneralData.plusActionPerformed(txtQuantity);
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        switch (TableDAO.table.getStatus()) {
            case 0 ->
                JOptionPane.showMessageDialog(panelMenu, "Xin vui lòng chọn bàn để đặt", null, JOptionPane.WARNING_MESSAGE);
            case 1 -> {
                OrderDAO.add();
                OrderDetailsDAO.add(txtQuantity, txtNote);
                GeneralData.setStatusTable();
                TableDAO.update();
            }
            case 2 ->
                OrderDetailsDAO.add(txtQuantity, txtNote);
            default ->
                JOptionPane.showMessageDialog(panelMenu, "Bàn này đã được thanh toán", null, JOptionPane.INFORMATION_MESSAGE);
        }
        showTable();
        showOrderDetails();
        txtQuantity.setText("1");
        txtNote.setText(null);
    }//GEN-LAST:event_btnAddActionPerformed

    private void tableOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrderMouseClicked
        setVisibleDetails();
    }//GEN-LAST:event_tableOrderMouseClicked

    private void tableOrderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableOrderKeyReleased
        setVisibleDetails();
    }//GEN-LAST:event_tableOrderKeyReleased

    private void btnMinusUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusUpdateActionPerformed
        GeneralData.minusActionPerformed(txtQuantityUpdate);
    }//GEN-LAST:event_btnMinusUpdateActionPerformed

    private void txtQuantityUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityUpdateKeyPressed
        GeneralData.textKeyPressed(evt, txtQuantityUpdate);
    }//GEN-LAST:event_txtQuantityUpdateKeyPressed

    private void btnPlusUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusUpdateActionPerformed
        GeneralData.plusActionPerformed(txtQuantityUpdate);
    }//GEN-LAST:event_btnPlusUpdateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        OrderDetailsDAO.update(tableOrder, txtQuantityUpdate, txtNoteUpdate);
        showOrderDetails();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(panelOrder, "Bạn có chắc muốn xóa món này khỏi hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            OrderDetailsDAO.delete(tableOrder);
            showOrderDetails();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        if (TableDAO.table.getStatus() == 2) {
            int result = JOptionPane.showConfirmDialog(panelOrder, "Bạn có chắc muốn thanh toán hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                new Bill().setVisible(true);
                finish();
            }
        }
        else if (TableDAO.table.getStatus() == 3) {
            finish();
        }
    }//GEN-LAST:event_btnFinalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFinal;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMinusUpdate;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnPlusUpdate;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbMenu;
    private javax.swing.JLabel imgMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbNameMenu;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelOrder;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtNoteUpdate;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtQuantityUpdate;
    // End of variables declaration//GEN-END:variables
}