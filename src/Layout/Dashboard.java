/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import javax.swing.JOptionPane;

/**
 *
 * @author rafie
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
                
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        Sidebar = new javax.swing.JPanel();
        btn_menu_employee = new javax.swing.JButton();
        btn_menu_inventory = new javax.swing.JButton();
        btn_menu_asset = new javax.swing.JButton();
        Navbar = new javax.swing.JPanel();
        btn_icon_user = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        frame_icon_user = new javax.swing.JPanel();
        btn_logout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_profile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(500, 500));

        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sidebar.setBackground(new java.awt.Color(255, 255, 255));

        btn_menu_employee.setBackground(new java.awt.Color(101, 131, 254));
        btn_menu_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_employee.setText("EMPLOYEE");
        btn_menu_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_employeeActionPerformed(evt);
            }
        });

        btn_menu_inventory.setBackground(new java.awt.Color(101, 131, 254));
        btn_menu_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_inventory.setText("INVENTORY");
        btn_menu_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_inventoryActionPerformed(evt);
            }
        });

        btn_menu_asset.setBackground(new java.awt.Color(101, 131, 254));
        btn_menu_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_asset.setText("ASSET");
        btn_menu_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_assetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_menu_asset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_inventory, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(btn_menu_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_menu_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        content.add(Sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 70, 180, 730));

        Navbar.setBackground(new java.awt.Color(255, 255, 255));

        btn_icon_user.setBackground(new java.awt.Color(255, 255, 255));
        btn_icon_user.setForeground(new java.awt.Color(255, 255, 255));
        btn_icon_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/profile.png"))); // NOI18N
        btn_icon_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_icon_userMouseClicked(evt);
            }
        });
        btn_icon_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_icon_userActionPerformed(evt);
            }
        });

        logo.setFont(new java.awt.Font("Sitka Text", 1, 48)); // NOI18N
        logo.setForeground(new java.awt.Color(101, 131, 254));
        logo.setText("AmAe");

        javax.swing.GroupLayout NavbarLayout = new javax.swing.GroupLayout(Navbar);
        Navbar.setLayout(NavbarLayout);
        NavbarLayout.setHorizontalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(927, 927, 927)
                .addComponent(btn_icon_user, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        NavbarLayout.setVerticalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_icon_user, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        content.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 70));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        content.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 26, 1160, 770));

        frame_icon_user.setBackground(new java.awt.Color(255, 255, 255));
        frame_icon_user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_logout.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_logout.setText("Logout");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        frame_icon_user.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 250, 40));

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel3.setText("PROFILE");
        frame_icon_user.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        btn_profile.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_profile.setText("Profile");
        frame_icon_user.add(btn_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(frame_icon_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(frame_icon_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int clicked = 0;
    private void btn_icon_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_icon_userActionPerformed
//        if(clicked%2 == 0){
//            frame_icon_user.setVisible(true);
//        }else{
//            frame_icon_user.setVisible(false);
//        }
//        clicked++;
    }//GEN-LAST:event_btn_icon_userActionPerformed

    private void btn_icon_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_icon_userMouseClicked
        
    }//GEN-LAST:event_btn_icon_userMouseClicked

    private void btn_menu_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_employeeActionPerformed
//        jTabbebPanel.setSelectedIndex(1);
    }//GEN-LAST:event_btn_menu_employeeActionPerformed

    private void btn_menu_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_inventoryActionPerformed
//        content.setSelectedIndex(2);
    }//GEN-LAST:event_btn_menu_inventoryActionPerformed

    private void btn_menu_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_assetActionPerformed
//        content.setSelectedIndex(3);
    }//GEN-LAST:event_btn_menu_assetActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        JOptionPane.showMessageDialog(null,"Logout Success");
        login loginPage = new login();
        loginPage.setVisible(true);
        loginPage.pack();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logoutActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JButton btn_icon_user;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_menu_asset;
    private javax.swing.JButton btn_menu_employee;
    private javax.swing.JButton btn_menu_inventory;
    private javax.swing.JButton btn_profile;
    private javax.swing.JPanel content;
    private javax.swing.JPanel frame_icon_user;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
