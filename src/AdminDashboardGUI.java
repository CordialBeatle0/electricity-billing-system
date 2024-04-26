import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Assar
 */
public class AdminDashboardGUI extends javax.swing.JFrame {

    /**
     * Creates new form AdminDashboardGUI
     */
    Admin admin;

    public AdminDashboardGUI() {
        initComponents();
    }

    public AdminDashboardGUI(Admin a) {
        initComponents();
        setLocationRelativeTo(null);
        admin = a;
        adminNameText.setText(admin.getName());
        adminNameText.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        adminNameText = new javax.swing.JTextField();
        viewInquiryButton = new javax.swing.JButton();
        viewBillButton = new javax.swing.JButton();
        calculateBillButton = new javax.swing.JButton();
        updateAccountButton = new javax.swing.JButton();
        SetChainButton = new javax.swing.JButton();
        categorizeCustomerButton1 = new javax.swing.JButton();
        jButtonSignOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setText("Welcome");

        viewInquiryButton.setText("View Inquiries");
        viewInquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInquiryButtonActionPerformed(evt);
            }
        });

        viewBillButton.setText("View Bills");
        viewBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBillButtonActionPerformed(evt);
            }
        });

        calculateBillButton.setText("Calculate Bill");
        calculateBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateBillButtonActionPerformed(evt);
            }
        });

        updateAccountButton.setText("Update Account");
        updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAccountButtonActionPerformed(evt);
            }
        });

        SetChainButton.setText("Set Respond Chain");
        SetChainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetChainButtonActionPerformed(evt);
            }
        });

        categorizeCustomerButton1.setText("Categorize Customer");
        categorizeCustomerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorizeCustomerButton1ActionPerformed(evt);
            }
        });

        jButtonSignOut.setText("Sign Out");
        jButtonSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(adminNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSignOut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(calculateBillButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SetChainButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(categorizeCustomerButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viewInquiryButton)
                                .addGap(23, 23, 23)
                                .addComponent(viewBillButton)
                                .addGap(18, 18, 18)
                                .addComponent(updateAccountButton)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminNameText))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewInquiryButton)
                    .addComponent(updateAccountButton)
                    .addComponent(viewBillButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SetChainButton)
                    .addComponent(categorizeCustomerButton1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calculateBillButton)
                    .addComponent(jButtonSignOut))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calculateBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateBillButtonActionPerformed
        Bill calculatingBills= new Bill();
        calculatingBills.calculateBill();
       JOptionPane.showMessageDialog(this, "All Bills have been Calculated.");
       
    }//GEN-LAST:event_calculateBillButtonActionPerformed

    private void viewInquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInquiryButtonActionPerformed
        ViewInquiriesByAdminGUI allInquiries = new ViewInquiriesByAdminGUI(admin);
        allInquiries.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewInquiryButtonActionPerformed

    private void viewBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBillButtonActionPerformed
        try {
            Connection connection = DatabaseSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM bill");
            if (result.next()) {
                ViewAllCustomerBills bills = new ViewAllCustomerBills(admin);
                bills.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No bills exist in database");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error getting bills from database in admin dashboard");
        }
    }//GEN-LAST:event_viewBillButtonActionPerformed

    private void SetChainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetChainButtonActionPerformed
        SetChainGUI setChain = new SetChainGUI(admin);
        setChain.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SetChainButtonActionPerformed

    private void categorizeCustomerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorizeCustomerButton1ActionPerformed
        CategorizeCustomerGUI categorize = new CategorizeCustomerGUI(admin);
        categorize.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_categorizeCustomerButton1ActionPerformed

    private void updateAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAccountButtonActionPerformed
        UpdateAccount gui = new UpdateAccount(admin);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_updateAccountButtonActionPerformed

    private void jButtonSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignOutActionPerformed
        LoginGUI gui = new LoginGUI();
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonSignOutActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboardGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SetChainButton;
    private javax.swing.JTextField adminNameText;
    private javax.swing.JButton calculateBillButton;
    private javax.swing.JButton categorizeCustomerButton1;
    private javax.swing.JButton jButtonSignOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton updateAccountButton;
    private javax.swing.JButton viewBillButton;
    private javax.swing.JButton viewInquiryButton;
    // End of variables declaration//GEN-END:variables
}
