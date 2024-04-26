/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;

/**
 * @author Assar
 */
public class CustomerDashboardGUI extends javax.swing.JFrame {

    /**
     * Creates new form CustomerDashboardGUI
     */
    Customer customer;

    public CustomerDashboardGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public CustomerDashboardGUI(Customer c) {
        initComponents();
        setLocationRelativeTo(null);
        customer = c;
        CustomerNameField.setText(customer.getName());
        CustomerNameField.setEditable(false);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WelcomeLabel = new javax.swing.JLabel();
        CustomerNameField = new javax.swing.JTextField();
        usageButton = new javax.swing.JButton();
        inquiryHistoryButton = new javax.swing.JButton();
        BillingHistoryButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        renewButton = new javax.swing.JButton();
        subButtton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        homeServiceButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inquiryButton = new javax.swing.JButton();
        jButtonSignOut = new javax.swing.JButton();
        jButtonUpdateAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        WelcomeLabel.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        WelcomeLabel.setText("Welcome");

        CustomerNameField.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N

        usageButton.setText("Electricity Usage");
        usageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usageButtonActionPerformed(evt);
            }
        });

        inquiryHistoryButton.setText("Inquiry History");
        inquiryHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inquiryHistoryButtonActionPerformed(evt);
            }
        });

        BillingHistoryButton.setText("Billing History");
        BillingHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillingHistoryButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setText("View");

        renewButton.setText("Renew Subscription");
        renewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renewButtonActionPerformed(evt);
            }
        });

        subButtton.setText("Subscribe To Electricity");
        subButtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subButttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setText("Subscriptions");

        homeServiceButton.setText("Request Home Service");
        homeServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeServiceButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Services");

        inquiryButton.setText("Add Inquiry");
        inquiryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inquiryButtonActionPerformed(evt);
            }
        });

        jButtonSignOut.setText("Sign Out");
        jButtonSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignOutActionPerformed(evt);
            }
        });

        jButtonUpdateAccount.setText("Update Account");
        jButtonUpdateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(WelcomeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSignOut)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(renewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(subButtton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inquiryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(usageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(inquiryHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BillingHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jButtonUpdateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WelcomeLabel)
                    .addComponent(CustomerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renewButton)
                    .addComponent(subButtton))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inquiryButton)
                    .addComponent(homeServiceButton)
                    .addComponent(jButtonUpdateAccount))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usageButton)
                    .addComponent(inquiryHistoryButton)
                    .addComponent(BillingHistoryButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtonSignOut)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inquiryHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryHistoryButtonActionPerformed
        ViewInquiriesByCustomerGUI gui = new ViewInquiriesByCustomerGUI(customer);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_inquiryHistoryButtonActionPerformed

    private boolean checkSubscription(String message) {
        if (!customer.getSubscription().isSubscriptionStatus()) {
            JOptionPane.showMessageDialog(this, "You can not " + message + " before you subscribe");
            return false;
        }
        if (customer.getCategory() == null) {
            JOptionPane.showMessageDialog(this, "You can not " + message + " before you are are categorized by our admins");
            return false;
        }
        return true;
    }
    
    private void renewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renewButtonActionPerformed
        if (!checkSubscription("renew your subscription"))
            return;
        RenewSubscriptionGUI renewGUI = new RenewSubscriptionGUI(customer);
        renewGUI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_renewButtonActionPerformed

    private void inquiryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiryButtonActionPerformed
        if (!checkSubscription("make an inquiry"))
            return;
        AddInquiryGUI inquiry = new AddInquiryGUI(customer);
        inquiry.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_inquiryButtonActionPerformed

    private void subButttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subButttonActionPerformed
        SubscribeToElectricity subscribe = new SubscribeToElectricity(customer);
        subscribe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_subButttonActionPerformed

    private void homeServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeServiceButtonActionPerformed
        RequestHomeService request = new RequestHomeService(customer);
        request.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeServiceButtonActionPerformed

    private void usageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usageButtonActionPerformed
        if (!checkSubscription("view your usage"))
            return;
        ViewUsageGUI gui = new ViewUsageGUI(customer);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_usageButtonActionPerformed

    private void BillingHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingHistoryButtonActionPerformed
        if (!checkSubscription("view your billing history"))
            return;
        ViewEachCustomerBillHistory gui = new ViewEachCustomerBillHistory(customer);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_BillingHistoryButtonActionPerformed

    private void jButtonSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignOutActionPerformed
        LoginGUI gui = new LoginGUI();
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonSignOutActionPerformed

    private void jButtonUpdateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateAccountActionPerformed
        UpdateAccount gui = new UpdateAccount(customer);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonUpdateAccountActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerDashboardGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BillingHistoryButton;
    private javax.swing.JTextField CustomerNameField;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JButton homeServiceButton;
    private javax.swing.JButton inquiryButton;
    private javax.swing.JButton inquiryHistoryButton;
    private javax.swing.JButton jButtonSignOut;
    private javax.swing.JButton jButtonUpdateAccount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton renewButton;
    private javax.swing.JButton subButtton;
    private javax.swing.JButton usageButton;
    // End of variables declaration//GEN-END:variables
}
