/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Font;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author Mihiran Jayawardhana
 */
public class Return_Products extends javax.swing.JFrame {

    public String returnInvoiceID;
    public String productID;
    public String productName;
    public String PurchasedQty;
    public String itemPrice;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");

    /**
     * Creates new form Return_Products
     */
    public Return_Products() {
        initComponents();
        SoledProductsWithWarrenty();
        jTable1.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        this.setIconImage(new ImageIcon(getClass().getResource("/images/LogoIcon.png")).getImage());
    }

    public void SoledProductsWithWarrenty() {

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = MySQL.search("SELECT * FROM `product_has_warrenty` INNER JOIN `product` ON `product_has_warrenty`.`product_id`=`product`.`id` INNER JOIN `warrenty` ON `product_has_warrenty`.`warrenty_id`=`warrenty`.`id` INNER JOIN `invoice_item` ON `product_has_warrenty`.`invoice_item_id`=`invoice_item`.`id` INNER JOIN `invoice` ON `invoice_item`.`invoice_id`=`invoice`.`id` INNER JOIN `customer` ON `invoice`.`customer_id`=`customer`.`id` WHERE `warrenty`.`name` != 'No Warrenty' ORDER BY `invoice`.`unique_id` ASC");

            while (rs.next()) {

                Vector v = new Vector();

                returnInvoiceID = rs.getString("invoice.unique_id");
                v.add(returnInvoiceID);

                String invDate = rs.getString("invoice.date_time");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(invDate);
                String warrentyStartDate = formatter.format(date);

                v.add(rs.getString("invoice.date_time"));

                v.add(rs.getString("customer.mobile"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("invoice_item.item_price"));
                v.add(rs.getString("warrenty.name"));

                String wPeriod = rs.getString("warrenty.name");

                int j = 0;
                if (wPeriod.equals("1 Month")) {
                    j = 1;
                } else if (wPeriod.equals("2 Months")) {
                    j = 2;
                } else if (wPeriod.equals("3 Months")) {
                    j = 3;
                } else if (wPeriod.equals("6 Months")) {
                    j = 6;
                } else if (wPeriod.equals("8 Months")) {
                    j = 8;
                } else if (wPeriod.equals("12 Months")) {
                    j = 12;
                } else if (wPeriod.equals("24 Months")) {
                    j = 24;
                } else if (wPeriod.equals("36 Months")) {
                    j = 36;
                } else if (wPeriod.equals("60 Months")) {
                    j = 60;
                }
                String dw = LocalDate.parse(warrentyStartDate).plusMonths(j).toString();
                v.add(dw);

                dtm.addRow(v);

            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SoledProductsWithWarrenty(String Text) {

        String notLike = "No Warrenty";

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = MySQL.search("SELECT * FROM `product_has_warrenty` INNER JOIN `product` ON `product_has_warrenty`.`product_id`=`product`.`id` INNER JOIN `warrenty` ON `product_has_warrenty`.`warrenty_id`=`warrenty`.`id` INNER JOIN `invoice_item` ON `product_has_warrenty`.`invoice_item_id`=`invoice_item`.`id` INNER JOIN `invoice` ON `invoice_item`.`invoice_id`=`invoice`.`id` INNER JOIN `customer` ON `invoice`.`customer_id`=`customer`.`id` WHERE (`invoice`.`unique_id` LIKE '" + Text + "%' OR `customer`.`mobile` LIKE '" + Text + "%') AND  `warrenty`.`name` NOT LIKE '" + notLike + "%'  ORDER BY `invoice`.`unique_id` ASC ");

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getString("invoice.unique_id"));

                String invDate = rs.getString("invoice.date_time");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(invDate);
                String warrentyStartDate = formatter.format(date);

                v.add(rs.getString("invoice.date_time"));

                v.add(rs.getString("customer.mobile"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("invoice_item.item_price"));
                v.add(rs.getString("warrenty.name"));

                String wPeriod = rs.getString("warrenty.name");

                int j = 0;
                if (wPeriod.equals("1 Month")) {
                    j = 1;
                } else if (wPeriod.equals("2 Months")) {
                    j = 2;
                } else if (wPeriod.equals("3 Months")) {
                    j = 3;
                } else if (wPeriod.equals("6 Months")) {
                    j = 6;
                } else if (wPeriod.equals("8 Months")) {
                    j = 8;
                } else if (wPeriod.equals("12 Months")) {
                    j = 12;
                } else if (wPeriod.equals("24 Months")) {
                    j = 24;
                } else if (wPeriod.equals("36 Months")) {
                    j = 36;
                } else if (wPeriod.equals("60 Months")) {
                    j = 60;
                }
                String dw = LocalDate.parse(warrentyStartDate).plusMonths(j).toString();
                v.add(dw);

                dtm.addRow(v);

            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Return Products");

        jPanel1.setBackground(new java.awt.Color(255, 239, 239));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Soled products with warrenty");

        jPanel2.setBackground(new java.awt.Color(255, 249, 249));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Date & Time", "Customer Mobile", "Product ID", "Product Name", "Qty", "Item Price", "W-Period", "W-Expire"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 249, 249));

        jButton1.setBackground(new java.awt.Color(230, 183, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 255));
        jButton1.setText("Return Product");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (jTable1.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a product to return", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTable1.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "Please select a single product to return", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            int selectedRow = jTable1.getSelectedRow();

            returnInvoiceID = jTable1.getValueAt(selectedRow, 0).toString();
            productID = jTable1.getValueAt(selectedRow, 3).toString();
            productName = jTable1.getValueAt(selectedRow, 4).toString();
            PurchasedQty = jTable1.getValueAt(selectedRow, 5).toString();
            itemPrice = jTable1.getValueAt(selectedRow, 6).toString();

            Return_Product_Invoice returnproductinvoicepage = new Return_Product_Invoice(this);
            returnproductinvoicepage.leftItemCount(returnInvoiceID);
            returnproductinvoicepage.setVisible(true);

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        SoledProductsWithWarrenty(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyTyped

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Return_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Return_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Return_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Return_Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Return_Products().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
