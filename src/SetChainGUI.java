
import java.util.ArrayList;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * @author Shero
 */
public class SetChainGUI extends javax.swing.JFrame {

    /**
     * Creates new form SetChainGUI
     */
    Admin admin;

    public SetChainGUI() {
        initComponents();
    }

    public SetChainGUI(Admin a) {
        initComponents();
        this.setLocationRelativeTo(null);
        admin = a;
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
        fiirstHandler = new javax.swing.JComboBox<>();
        thirdHandler = new javax.swing.JComboBox<>();
        secondHandler = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Set Inquiry Handling Order");

        fiirstHandler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer Service" }));
        fiirstHandler.setSelectedIndex(-1);
        fiirstHandler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiirstHandlerActionPerformed(evt);
            }
        });

        thirdHandler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Technician" }));
        thirdHandler.setSelectedIndex(-1);
        thirdHandler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thirdHandlerActionPerformed(evt);
            }
        });

        secondHandler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Technician" }));
        secondHandler.setSelectedIndex(-1);
        secondHandler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondHandlerActionPerformed(evt);
            }
        });

        jLabel2.setText("Second Handler");

        jLabel3.setText("Third Handler");

        jLabel4.setText("First Handler");

        confirmButton.setText("Confirm order");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(fiirstHandler, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(resetBtn)
                                    .addComponent(thirdHandler, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(secondHandler, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 168, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonCancel)
                .addGap(58, 58, 58)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiirstHandler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondHandler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thirdHandler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirmButton)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancel)
                            .addComponent(resetBtn))
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        //get the index of each handler from drop downs
        int firstHandlerInt = this.fiirstHandler.getSelectedIndex();
        String firstHandlerString = fiirstHandler.getModel().getElementAt(firstHandlerInt);

        int secondHandlerInt = this.secondHandler.getSelectedIndex();
        String secondHandlerString = secondHandler.getModel().getElementAt(secondHandlerInt);

        int thirdHandlerInt = this.thirdHandler.getSelectedIndex();
        String thirdHandlerString = thirdHandler.getModel().getElementAt(thirdHandlerInt);

        boolean isAdminSelected = false;
        boolean isCustomerServiceSelected = false;
        boolean isTechnicianSelected = false;

        ArrayList<Employee> employees = Employee.getEmployeesFromDB("");
        Admin emp1 = null;
        CustomerService emp2 = null;
        Technician emp3 = null;

        Employee selectedEmp1;
        Employee selectedEmp2;
        Employee selectedEmp3;

        for (Employee employee : employees) {

            if (isAdminSelected && isTechnicianSelected && isCustomerServiceSelected) {
                break;
            }

            if (!isAdminSelected && employee.getClass().getName().equals("Admin")) {
                emp1 = ((Admin) employee);
                isAdminSelected = true;
            } else if (!isCustomerServiceSelected && employee.getClass().getName().equals("CustomerService")) {
                emp2 = ((CustomerService) employee);
                isCustomerServiceSelected = true;
            } else if (!isTechnicianSelected && employee.getClass().getName().equals("Technician")) {
                emp3 = ((Technician) employee);
                isTechnicianSelected = true;
            }
        }

        //determine the chain order according to entered indices
        switch (firstHandlerString) {
            case "Admin":
                selectedEmp1 = emp1;
                break;
            case "Customer Service":
                selectedEmp1 = emp2;
                break;
            case "Technician":
                selectedEmp1 = emp3;
                break;
            default:
                selectedEmp1 = emp2;
                break;
        }
        switch (secondHandlerString) {
            case "Admin":
                selectedEmp2 = emp1;
                break;
            case "Customer Service":
                selectedEmp2 = emp2;
                break;
            case "Technician":
                selectedEmp2 = emp3;
                break;
            default:
                selectedEmp2 = emp1;
                break;
        }
        switch (thirdHandlerString) {
            case "Admin":
                selectedEmp3 = emp1;
                break;
            case "Customer Service":
                selectedEmp3 = emp2;
                break;
            case "Technician":
                selectedEmp3 = emp3;
                break;
            default:
                selectedEmp3 = emp3;
                break;
        }

        //set the chain
        selectedEmp1.setHandler(selectedEmp2);
        selectedEmp2.setHandler(selectedEmp3);
        selectedEmp3.setHandler(null);

        JOptionPane.showMessageDialog(this, "Chain has been set");
        AdminDashboardGUI gui = new AdminDashboardGUI(admin);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        AdminDashboardGUI gui = new AdminDashboardGUI(admin);
        gui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void fiirstHandlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiirstHandlerActionPerformed
        // TODO add your handling code here:
        Object obj1 = fiirstHandler.getSelectedItem();
        this.secondHandler.removeItem(obj1);
        this.thirdHandler.removeItem(obj1);
    }//GEN-LAST:event_fiirstHandlerActionPerformed

    private void secondHandlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondHandlerActionPerformed
        // TODO add your handling code here:
        Object obj2 = secondHandler.getSelectedItem();
        this.fiirstHandler.removeItem(obj2);
        this.thirdHandler.removeItem(obj2);
    }//GEN-LAST:event_secondHandlerActionPerformed

    private void thirdHandlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thirdHandlerActionPerformed
        // TODO add your handling code here:
        Object obj3 = thirdHandler.getSelectedItem();
        this.secondHandler.removeItem(obj3);
        this.fiirstHandler.removeItem(obj3);
    }//GEN-LAST:event_thirdHandlerActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Admin");
        choices.add("CustomerService");
        choices.add("Technician");

        this.fiirstHandler.removeAll();
        this.secondHandler.removeAll();
        this.thirdHandler.removeAll();

        this.fiirstHandler.setModel(new DefaultComboBoxModel(choices.toArray()));
        this.secondHandler.setModel(new DefaultComboBoxModel(choices.toArray()));
        this.thirdHandler.setModel(new DefaultComboBoxModel(choices.toArray()));

        this.fiirstHandler.setSelectedIndex(-1);
        this.secondHandler.setSelectedIndex(-1);
        this.thirdHandler.setSelectedIndex(-1);
    }//GEN-LAST:event_resetBtnActionPerformed

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
            java.util.logging.Logger.getLogger(SetChainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetChainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetChainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetChainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetChainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmButton;
    private javax.swing.JComboBox<String> fiirstHandler;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> secondHandler;
    private javax.swing.JComboBox<String> thirdHandler;
    // End of variables declaration//GEN-END:variables
}
