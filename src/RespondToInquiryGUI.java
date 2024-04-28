import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author pitos
 */
public class RespondToInquiryGUI extends javax.swing.JFrame {

    /**
     * Creates new form RespondToInquiryGUI
     */

    Employee employee;
    Inquiry inquiry;

    public RespondToInquiryGUI() {
        initComponents();
    }

    public RespondToInquiryGUI(Employee e, Inquiry i) {
        initComponents();
        setLocationRelativeTo(null);
        employee = e;
        inquiry = i;
        jTextAreaQuestion.setText(inquiry.getQuestion());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelQuestion = new javax.swing.JLabel();
        jLabelResponse = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaQuestion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResponse = new javax.swing.JTextArea();
        jLabelTitle = new javax.swing.JLabel();
        jButtonSubmit = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelQuestion.setText("Question");

        jLabelResponse.setText("Response");

        jTextAreaQuestion.setEditable(false);
        jTextAreaQuestion.setColumns(20);
        jTextAreaQuestion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaQuestion);

        jTextAreaResponse.setColumns(20);
        jTextAreaResponse.setRows(5);
        jScrollPane2.setViewportView(jTextAreaResponse);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelTitle.setText("Respond To Inquiry");

        jButtonSubmit.setText("Submit");
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelResponse))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabelTitle)))
                                .addGap(70, 70, 70))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonCancel)
                                .addGap(52, 52, 52)
                                .addComponent(jButtonSubmit)
                                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabelTitle)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabelQuestion)
                                                .addGap(35, 35, 35)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(jLabelResponse)))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSubmit)
                                        .addComponent(jButtonCancel))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        switch (employee.getClass().getName()) {
            case "Admin":
                AdminDashboardGUI aGui = new AdminDashboardGUI(((Admin) employee));
                aGui.setVisible(true);
                break;
            case "Technician":
                TechnicianDashboardGUI tGui = new TechnicianDashboardGUI(((Technician) employee));
                tGui.setVisible(true);
                break;
            case "CustomerService":
                CustomerServiceDashboardGUI cGui = new CustomerServiceDashboardGUI(((CustomerService) employee));
                cGui.setVisible(true);
            default:
        }
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        String response = jTextAreaResponse.getText();

        if (response.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Response can not be empty");
            return;
        }

        inquiry.setResponse(response);

        if (!inquiry.respondToInquiry(employee.getID(), inquiry.getID())) {
            return;
        }

        switch (employee.getClass().getName()) {
            case "Admin":
                AdminDashboardGUI aGui = new AdminDashboardGUI(((Admin) employee));
                aGui.setVisible(true);
                break;
            case "Technician":
                TechnicianDashboardGUI tGui = new TechnicianDashboardGUI(((Technician) employee));
                tGui.setVisible(true);
                break;
            case "CustomerService":
                CustomerServiceDashboardGUI cGui = new CustomerServiceDashboardGUI(((CustomerService) employee));
                cGui.setVisible(true);
            default:
        }
        this.dispose();
    }//GEN-LAST:event_jButtonSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(RespondToInquiryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespondToInquiryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespondToInquiryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespondToInquiryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespondToInquiryGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelResponse;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaQuestion;
    private javax.swing.JTextArea jTextAreaResponse;
    // End of variables declaration//GEN-END:variables
}
