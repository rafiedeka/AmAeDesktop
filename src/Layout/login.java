/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import javax.swing.*;
import java.sql.*;
import Config.Db;
import java.awt.event.KeyEvent;

/**
 *
 * @author rafie
 */
public class login extends javax.swing.JFrame {

    static Connection conn = Db.getConnection();
    static Statement db;
    static ResultSet Result;
    public static String employee_id;
    public static String division_name;
    public static String id_level;
    
    public login() {
        initComponents();
        
    }
    
    protected void doLogin(){
        String val_username = f_username.getText();
        String val_password = String.valueOf(f_password.getPassword());
        
        //VALIDATION
        if ( val_username.trim().length() == 0 || val_password.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        }
        
        try{
            String query = "SELECT b.employee_id,\n" +
                                "b.password,\n" +
                                "c.name AS division_name,\n" +
                                "a.id_level\n" +
                            "FROM t_user_level a\n" +
                            "JOIN t_employee b ON b.id = a.id_user\n" +
                            "JOIN t_division c ON a.id_division = c.id " +
                            "WHERE b.employee_id=? AND b.password=MD5(?)";
            PreparedStatement req = conn.prepareStatement(query);
            req.setString(1,val_username);
            req.setString(2,val_password);
            Result = req.executeQuery();
            if(Result.next()){
                employee_id = Result.getString("employee_id");
                division_name = Result.getString("division_name");
                id_level = Result.getString("id_level");
                
                if("admin".equals(division_name)){
                    JOptionPane.showMessageDialog(null,"Login Success");
                    admin admin = new admin();
                    admin.setVisible(true);
                    admin.pack();
                    this.dispose();
                }
                if("HRD".equals(division_name)){
                    JOptionPane.showMessageDialog(null,"Login Success");
                    HRD hrd = new HRD();
                    hrd.setVisible(true);
                    hrd.pack();
                    this.dispose();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Username & Password is Incorrect");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Username & Password is Incorrect");
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
        bg_login1 = new Layout.bg_login();
        jLabel1 = new javax.swing.JLabel();
        f_username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        f_password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(810, 556));

        javax.swing.GroupLayout bg_login1Layout = new javax.swing.GroupLayout(bg_login1);
        bg_login1.setLayout(bg_login1Layout);
        bg_login1Layout.setHorizontalGroup(
            bg_login1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );
        bg_login1Layout.setVerticalGroup(
            bg_login1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(101, 131, 254));
        jLabel1.setText("AmAe");

        f_username.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        f_username.setToolTipText("");
        f_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_usernameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel3.setText("Password");

        btn_login.setBackground(new java.awt.Color(102, 102, 255));
        btn_login.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        btn_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_loginKeyPressed(evt);
            }
        });

        f_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                f_passwordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bg_login1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(f_username, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(f_password, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btn_login)))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bg_login1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f_username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f_password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        doLogin();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void f_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_usernameActionPerformed

    private void f_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_f_passwordKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            doLogin();
        }
    }//GEN-LAST:event_f_passwordKeyPressed

    private void btn_loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_loginKeyPressed
        
    }//GEN-LAST:event_btn_loginKeyPressed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Layout.bg_login bg_login1;
    private javax.swing.JButton btn_login;
    private javax.swing.JPasswordField f_password;
    private javax.swing.JTextField f_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
