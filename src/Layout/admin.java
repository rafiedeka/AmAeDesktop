/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import javax.swing.*;
import java.sql.*;
import Config.Db;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafie
 */
public class admin extends javax.swing.JFrame {
    static Connection conn = Db.getConnection();
    static Statement db;
    static ResultSet Result;
    private DefaultTableModel tabmode;
    
    
    public admin() {
        initComponents();
        panel_btn_profile.setVisible(false);
        
        dt_employee();
        F_employee_id.setVisible(false);
        btn_edit_employee.setEnabled(false);
        btn_delete_employee.setEnabled(false);
        
        dt_inventory();
        F_inventory_id.setVisible(false);
        btn_edit_inventory.setEnabled(false);
        btn_delete_inventory.setEnabled(false);
        
        CB_inCharge_data();
        dt_division();
        F_division_id.setVisible(false);
        btn_edit_division.setEnabled(false);
        btn_delete_division.setEnabled(false);
        
    }
    
    private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    
    // EMPLOYEE
        protected void clear_form_employee(){
            tid.setText("");
            tnama.setText("");
            talamat.setText("");
            rjk1.setSelected(true);
            tphone.setText("");
            tcari_inventory.setText("");
            temail.setText("");
            F_employee_id.setText("");
            btn_save_employee.setEnabled(true);
            btn_edit_employee.setEnabled(false);
            btn_delete_employee.setEnabled(false);

        }
    
        protected void dt_employee() {
            Object [] Baris = {"No","Employee ID","Name","Gender","Email","Phone","Address","Created Date"};
            tabmode = new DefaultTableModel(null, Baris);
            tableEmployee.setModel(tabmode);
            String sql = "SELECT * FROM t_employee";
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    String a = hasil.getString ("id");
                    String b = hasil.getString ("employee_id");
                    String c = hasil.getString ("name");
                    String d = hasil.getString ("gender");
                    String e = hasil.getString ("email");
                    String f = hasil.getString ("phone");
                    String g = hasil.getString ("address");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String h = df.format(datetime);

                    String [] data= {a,b,c,d,e,f,g,h};
                    tabmode.addRow(data);
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
        }
    
    
    // INVENTORY
        protected void clear_form_inventory(){
            ttag.setText("");
            tmodel.setText("");
            tkat.setText("");
            tqty.setText("");
            jtk.setText("");
            tstatus.setText("");
            tcari_inventory.setText("");
            F_inventory_id.setText("");
            btn_save_inventory.setEnabled(true);
            btn_edit_inventory.setEnabled(false);
            btn_delete_inventory.setEnabled(false);
            
        }
        
        protected void dt_inventory(){
            Object[]Baris = {"No","Tag","Model","Cattegory","Qty","Description","Status","Created Date"};
            tabmode = new DefaultTableModel(null, Baris);
            tabelinventory.setModel(tabmode);
            
            String sql = "SELECT * FROM t_inventory";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String a = hasil.getString("id");
                    String b = hasil.getString("tag");
                    String c = hasil.getString("model");
                    String d = hasil.getString("cattegory");
                    String e = hasil.getString("qty");
                    String f = hasil.getString("description");
                    String g = hasil.getString("status");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String h = df.format(datetime);

                    String[] data={a,b,c,d,e,f,g,h};
                    tabmode.addRow(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
        }
        
    // DIVISION
        public void CB_inCharge_data(){
            try{
                db = conn.createStatement();
                String query = "SELECT id, employee_id,name FROM t_employee ";

                Result = db.executeQuery(query);

                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    CB_inCharge.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "In Charge Data Error");
                System.out.println(e);
            }
        }
        
        protected void dt_division(){
            Object[]Baris = {"No","Division","In Charge","Created Date"};
            tabmode = new DefaultTableModel(null, Baris);
            table_division.setModel(tabmode);
            
            String sql = "SELECT a.id,a.name AS division_name,CONCAT(b.employee_id,' - ',b.name) AS boss_name,a.created_at\n" +
                        "FROM t_division a\n" +
                        "LEFT JOIN t_employee b ON a.boss_id = b.id";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String a = hasil.getString("id");
                    String b = hasil.getString("division_name");
                    String c = hasil.getString("boss_name");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String d = df.format(datetime);

                    String[] data={a,b,c,d};
                    tabmode.addRow(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
        }
        
        protected void clear_form_division(){
            F_division.setText("");
            CB_inCharge.setSelectedIndex(0);
            F_division_id.setText("");
            
            btn_save_division.setEnabled(true);
            btn_edit_division.setEnabled(false);
            btn_delete_division.setEnabled(false);
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_btn_profile = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        gradient_footer2 = new Layout.gradient_footer();
        btn_logout = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btn_profile = new javax.swing.JButton();
        navbar = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btn_icon_user = new javax.swing.JButton();
        sidebar = new javax.swing.JPanel();
        btn_menu_employee = new javax.swing.JButton();
        btn_menu_inventory = new javax.swing.JButton();
        btn_menu_asset = new javax.swing.JButton();
        btn_menu_division = new javax.swing.JButton();
        content = new javax.swing.JTabbedPane();
        panel_employee = new javax.swing.JPanel();
        BreadCrumb = new javax.swing.JPanel();
        employee = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jkel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tphone = new javax.swing.JTextField();
        tid = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        rjk1 = new javax.swing.JRadioButton();
        rjk2 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        talamat = new javax.swing.JTextArea();
        btn_save_employee = new javax.swing.JButton();
        btn_edit_employee = new javax.swing.JButton();
        btn_delete_employee = new javax.swing.JButton();
        btn_clear_employee = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        temail = new javax.swing.JTextField();
        F_employee_id = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tcari1 = new javax.swing.JTextField();
        btn_search_employee = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();
        panel_inventory = new javax.swing.JPanel();
        BreadCrumb1 = new javax.swing.JPanel();
        INVENTORY = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ttag = new javax.swing.JTextField();
        tmodel = new javax.swing.JTextField();
        tkat = new javax.swing.JTextField();
        tqty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtk = new javax.swing.JTextArea();
        btn_save_inventory = new javax.swing.JButton();
        btn_edit_inventory = new javax.swing.JButton();
        btn_delete_inventory = new javax.swing.JButton();
        btn_clear_inventory = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        tstatus = new javax.swing.JTextField();
        F_inventory_id = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelinventory = new javax.swing.JTable();
        tcari_inventory = new javax.swing.JTextField();
        btn_search_inventory = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        panel_asset = new javax.swing.JPanel();
        BreadCrumb2 = new javax.swing.JPanel();
        INVENTORY1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ttag1 = new javax.swing.JTextField();
        tmodel1 = new javax.swing.JTextField();
        tkat1 = new javax.swing.JTextField();
        tqty1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtk1 = new javax.swing.JTextArea();
        btn_save_inventory1 = new javax.swing.JButton();
        btn_edit_inventory1 = new javax.swing.JButton();
        btn_delete_inventory1 = new javax.swing.JButton();
        btn_clear_inventory1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        tstatus1 = new javax.swing.JTextField();
        F_inventory_id1 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelinventory1 = new javax.swing.JTable();
        tcari_inventory1 = new javax.swing.JTextField();
        btn_search_inventory1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        panel_division = new javax.swing.JPanel();
        BreadCrumb3 = new javax.swing.JPanel();
        INVENTORY2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        F_division = new javax.swing.JTextField();
        btn_save_division = new javax.swing.JButton();
        btn_edit_division = new javax.swing.JButton();
        btn_delete_division = new javax.swing.JButton();
        btn_clear_division = new javax.swing.JButton();
        CB_inCharge = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_division = new javax.swing.JTable();
        F_division_id = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelinventory2 = new javax.swing.JTable();
        tcari_inventory2 = new javax.swing.JTextField();
        btn_search_inventory2 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        gradient_footer1 = new Layout.gradient_footer();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_btn_profile.setBackground(new java.awt.Color(255, 255, 255));
        panel_btn_profile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_logout.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_logout.setText("Logout");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PROFILE");

        btn_profile.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_profile.setText("Profile");

        javax.swing.GroupLayout gradient_footer2Layout = new javax.swing.GroupLayout(gradient_footer2);
        gradient_footer2.setLayout(gradient_footer2Layout);
        gradient_footer2Layout.setHorizontalGroup(
            gradient_footer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradient_footer2Layout.createSequentialGroup()
                .addGroup(gradient_footer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gradient_footer2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel13))
                    .addGroup(gradient_footer2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btn_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gradient_footer2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        gradient_footer2Layout.setVerticalGroup(
            gradient_footer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradient_footer2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(13, 13, 13)
                .addComponent(btn_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gradient_footer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(gradient_footer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        panel_btn_profile.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 150));

        jPanel1.add(panel_btn_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 70, 280, 150));

        navbar.setBackground(new java.awt.Color(255, 255, 255));

        logo.setFont(new java.awt.Font("Sitka Text", 1, 48)); // NOI18N
        logo.setForeground(new java.awt.Color(46, 139, 244));
        logo.setText("AmAe");

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

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1247, Short.MAX_VALUE)
                .addComponent(btn_icon_user, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_icon_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(172, 172, 172))
        );

        jPanel1.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 70));

        sidebar.setBackground(new java.awt.Color(255, 255, 255));

        btn_menu_employee.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_employee.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_employee.setText("EMPLOYEE");
        btn_menu_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_employeeActionPerformed(evt);
            }
        });

        btn_menu_inventory.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_inventory.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_inventory.setText("INVENTORY");
        btn_menu_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_inventoryActionPerformed(evt);
            }
        });

        btn_menu_asset.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_asset.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_asset.setText("ASSET");
        btn_menu_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_assetActionPerformed(evt);
            }
        });

        btn_menu_division.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_division.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_division.setText("DIVISION");
        btn_menu_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_divisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_menu_inventory, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_menu_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_asset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_division, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btn_menu_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_division, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(505, Short.MAX_VALUE))
        );

        jPanel1.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 740));

        BreadCrumb.setBackground(new java.awt.Color(255, 255, 255));

        employee.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        employee.setText("EMPLOYEE");

        javax.swing.GroupLayout BreadCrumbLayout = new javax.swing.GroupLayout(BreadCrumb);
        BreadCrumb.setLayout(BreadCrumbLayout);
        BreadCrumbLayout.setHorizontalGroup(
            BreadCrumbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumbLayout.createSequentialGroup()
                .addGap(554, 554, 554)
                .addComponent(employee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumbLayout.setVerticalGroup(
            BreadCrumbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumbLayout.createSequentialGroup()
                .addComponent(employee)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 227));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel1.setText("Employee ID");

        jLabel9.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel9.setText("Name");

        jkel.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jkel.setText("Gender");

        jLabel14.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel14.setText("Phone");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel10.setText("Address");

        tphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tphoneActionPerformed(evt);
            }
        });

        tid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidActionPerformed(evt);
            }
        });

        rjk1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        rjk1.setText("Laki-Laki");

        rjk2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        rjk2.setText("Perempuan");

        talamat.setColumns(20);
        talamat.setRows(5);
        jScrollPane3.setViewportView(talamat);

        btn_save_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_employee.setText("SAVE");
        btn_save_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_employeeActionPerformed(evt);
            }
        });

        btn_edit_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_employee.setText("EDIT");
        btn_edit_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_employeeActionPerformed(evt);
            }
        });

        btn_delete_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_employee.setText("DELETE");
        btn_delete_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_employeeActionPerformed(evt);
            }
        });

        btn_clear_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_employee.setText("CLEAR");
        btn_clear_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_employeeActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel15.setText("Email");

        temail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jkel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14)
                                .addComponent(jLabel10)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tphone)
                                    .addComponent(temail)
                                    .addComponent(tnama)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(rjk2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tid)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rjk1)
                        .addComponent(jkel))
                    .addComponent(rjk2))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(tphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 227));

        jLabel11.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel11.setText("Kata Kunci");

        btn_search_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_employee.setText("CARI");
        btn_search_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_employeeActionPerformed(evt);
            }
        });

        tableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmployeeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableEmployee);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tcari1)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_employeeLayout = new javax.swing.GroupLayout(panel_employee);
        panel_employee.setLayout(panel_employeeLayout);
        panel_employeeLayout.setHorizontalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_employeeLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        panel_employeeLayout.setVerticalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        content.addTab("tab1", panel_employee);

        BreadCrumb1.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY.setText("INVENTORY");

        javax.swing.GroupLayout BreadCrumb1Layout = new javax.swing.GroupLayout(BreadCrumb1);
        BreadCrumb1.setLayout(BreadCrumb1Layout);
        BreadCrumb1Layout.setHorizontalGroup(
            BreadCrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb1Layout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addComponent(INVENTORY)
                .addContainerGap(612, Short.MAX_VALUE))
        );
        BreadCrumb1Layout.setVerticalGroup(
            BreadCrumb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb1Layout.createSequentialGroup()
                .addComponent(INVENTORY)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel3.setText("Asset Tag");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel4.setText("Model");

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel5.setText("Cattegory");

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel6.setText("Qty");

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel7.setText("Description");

        ttag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttagActionPerformed(evt);
            }
        });

        jtk.setColumns(20);
        jtk.setRows(5);
        jScrollPane1.setViewportView(jtk);

        btn_save_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_inventory.setText("SAVE");
        btn_save_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_inventoryActionPerformed(evt);
            }
        });

        btn_edit_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_inventory.setText("EDIT");
        btn_edit_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_inventoryActionPerformed(evt);
            }
        });

        btn_delete_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_inventory.setText("DELETE");
        btn_delete_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_inventoryActionPerformed(evt);
            }
        });

        btn_clear_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_inventory.setText("CLEAR");
        btn_clear_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_inventoryActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel16.setText("Status");

        F_inventory_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_inventory_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_save_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btn_edit_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(btn_delete_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btn_clear_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tmodel, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                    .addComponent(ttag)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tstatus)
                                    .addComponent(tkat)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ttag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F_inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tabelinventory.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelinventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelinventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelinventoryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelinventory);

        btn_search_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_inventory.setText("CARI");
        btn_search_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_inventoryActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel8.setText("Kata Kunci");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcari_inventory)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout panel_inventoryLayout = new javax.swing.GroupLayout(panel_inventory);
        panel_inventory.setLayout(panel_inventoryLayout);
        panel_inventoryLayout.setHorizontalGroup(
            panel_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inventoryLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_inventoryLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(BreadCrumb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_inventoryLayout.setVerticalGroup(
            panel_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inventoryLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        content.addTab("tab2", panel_inventory);

        BreadCrumb2.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY1.setText("ASSET");

        javax.swing.GroupLayout BreadCrumb2Layout = new javax.swing.GroupLayout(BreadCrumb2);
        BreadCrumb2.setLayout(BreadCrumb2Layout);
        BreadCrumb2Layout.setHorizontalGroup(
            BreadCrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb2Layout.createSequentialGroup()
                .addGap(564, 564, 564)
                .addComponent(INVENTORY1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumb2Layout.setVerticalGroup(
            BreadCrumb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb2Layout.createSequentialGroup()
                .addComponent(INVENTORY1)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel17.setText("Asset Tag");

        jLabel18.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel18.setText("Model");

        jLabel19.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel19.setText("Cattegory");

        jLabel20.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel20.setText("Qty");

        jLabel21.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel21.setText("Description");

        ttag1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttag1ActionPerformed(evt);
            }
        });

        jtk1.setColumns(20);
        jtk1.setRows(5);
        jScrollPane5.setViewportView(jtk1);

        btn_save_inventory1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_inventory1.setText("SAVE");
        btn_save_inventory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_inventory1ActionPerformed(evt);
            }
        });

        btn_edit_inventory1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_inventory1.setText("EDIT");
        btn_edit_inventory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_inventory1ActionPerformed(evt);
            }
        });

        btn_delete_inventory1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_inventory1.setText("DELETE");
        btn_delete_inventory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_inventory1ActionPerformed(evt);
            }
        });

        btn_clear_inventory1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_inventory1.setText("CLEAR");
        btn_clear_inventory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_inventory1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel22.setText("Status");

        F_inventory_id1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_inventory_id1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btn_save_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btn_edit_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(btn_delete_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btn_clear_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tmodel1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                    .addComponent(ttag1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tqty1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tstatus1)
                                    .addComponent(tkat1)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_inventory_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(ttag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tmodel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tkat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(tqty1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tstatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(F_inventory_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tabelinventory1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelinventory1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelinventory1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelinventory1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabelinventory1);

        btn_search_inventory1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_inventory1.setText("CARI");
        btn_search_inventory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_inventory1ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel23.setText("Kata Kunci");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcari_inventory1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_assetLayout = new javax.swing.GroupLayout(panel_asset);
        panel_asset.setLayout(panel_assetLayout);
        panel_assetLayout.setHorizontalGroup(
            panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_assetLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BreadCrumb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(panel_assetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_assetLayout.setVerticalGroup(
            panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_assetLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_assetLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(panel_assetLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))))
        );

        content.addTab("tab3", panel_asset);

        BreadCrumb3.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY2.setText("DIVISION");

        javax.swing.GroupLayout BreadCrumb3Layout = new javax.swing.GroupLayout(BreadCrumb3);
        BreadCrumb3.setLayout(BreadCrumb3Layout);
        BreadCrumb3Layout.setHorizontalGroup(
            BreadCrumb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb3Layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(INVENTORY2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumb3Layout.setVerticalGroup(
            BreadCrumb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb3Layout.createSequentialGroup()
                .addComponent(INVENTORY2)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel24.setText("Division");

        jLabel25.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel25.setText("In Charge");

        F_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_divisionActionPerformed(evt);
            }
        });

        btn_save_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_division.setText("SAVE");
        btn_save_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_divisionActionPerformed(evt);
            }
        });

        btn_edit_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_division.setText("EDIT");
        btn_edit_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_divisionActionPerformed(evt);
            }
        });

        btn_delete_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_division.setText("DELETE");
        btn_delete_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_divisionActionPerformed(evt);
            }
        });

        btn_clear_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_division.setText("CLEAR");
        btn_clear_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_divisionActionPerformed(evt);
            }
        });

        CB_inCharge.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- choose ---" }));
        CB_inCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_inChargeActionPerformed(evt);
            }
        });

        jScrollPane7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane7MouseClicked(evt);
            }
        });

        table_division.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_divisionMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table_division);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btn_save_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btn_edit_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(btn_delete_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btn_clear_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CB_inCharge, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(F_division, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(F_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(CB_inCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(F_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_edit_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane7)
                .addGap(5, 5, 5))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tabelinventory2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabelinventory2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelinventory2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelinventory2MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabelinventory2);

        btn_search_inventory2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_inventory2.setText("CARI");
        btn_search_inventory2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_inventory2ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel30.setText("Kata Kunci");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tcari_inventory2)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_inventory2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari_inventory2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_inventory2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panel_divisionLayout = new javax.swing.GroupLayout(panel_division);
        panel_division.setLayout(panel_divisionLayout);
        panel_divisionLayout.setHorizontalGroup(
            panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_divisionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_divisionLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        panel_divisionLayout.setVerticalGroup(
            panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_divisionLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        content.addTab("tab4", panel_division);

        jPanel1.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 1270, 710));

        footer.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("© 2022 KEL. 4 | ALL RIGHT RESERVED");

        javax.swing.GroupLayout gradient_footer1Layout = new javax.swing.GroupLayout(gradient_footer1);
        gradient_footer1.setLayout(gradient_footer1Layout);
        gradient_footer1Layout.setHorizontalGroup(
            gradient_footer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradient_footer1Layout.createSequentialGroup()
                .addContainerGap(1204, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(29, 29, 29))
        );
        gradient_footer1Layout.setVerticalGroup(
            gradient_footer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradient_footer1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addComponent(gradient_footer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(gradient_footer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.add(footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 750, 1500, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_menu_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_employeeActionPerformed
        content.setSelectedIndex(0);
    }//GEN-LAST:event_btn_menu_employeeActionPerformed

    private void btn_menu_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_inventoryActionPerformed
        content.setSelectedIndex(1);
    }//GEN-LAST:event_btn_menu_inventoryActionPerformed

    private void btn_menu_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_assetActionPerformed
        content.setSelectedIndex(2);
    }//GEN-LAST:event_btn_menu_assetActionPerformed

    private void btn_menu_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_divisionActionPerformed
        content.setSelectedIndex(3);
    }//GEN-LAST:event_btn_menu_divisionActionPerformed

    private void btn_save_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_inventoryActionPerformed
        String tag = ttag.getText();
        String model = tmodel.getText();
        String cattegory = tkat.getText();
        String qty = tqty.getText();
        String description = jtk.getText();
        String status = tstatus.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        if ( tag.trim().length() == 0 || model.trim().length() == 0 || cattegory.trim().length() == 0 || qty.trim().length() == 0 || description.trim().length() == 0 || status.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else {
            String sql = "INSERT INTO t_inventory(tag,model,cattegory,qty,description,status,created_at) values (?,?,?,?,?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tag);
                stat.setString(2, model);
                stat.setString(3, cattegory);
                stat.setString(4, qty);
                stat.setString(5, description);
                stat.setString(6, status);
                stat.setString(7, datetime);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Create Inventory Success ");
                clear_form_inventory();
                ttag.requestFocus();
                dt_inventory();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Create Inventory Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_save_inventoryActionPerformed

    private void btn_edit_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_inventoryActionPerformed
        String id = F_inventory_id.getText();
        String tag = ttag.getText();
        String model = tmodel.getText();
        String cattegory = tkat.getText();
        String qty = tqty.getText();
        String description = jtk.getText();
        String status = tstatus.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        if ( tag.trim().length() == 0 || model.trim().length() == 0 || cattegory.trim().length() == 0 || qty.trim().length() == 0 || description.trim().length() == 0 || status.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else {
            String sql = "UPDATE t_inventory SET tag=?, model=?, cattegory=?, qty=?,description=?, status=?,updated_at=? where id=?";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, tag);
                stat.setString(2, model);
                stat.setString(3, cattegory);
                stat.setString(4, qty);
                stat.setString(5, description);
                stat.setString(6, status);
                stat.setString(7, datetime);
                stat.setString(8, id);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Edit Inventory Success");
                clear_form_inventory();
                ttag.requestFocus();
                dt_inventory();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Edit Inventory Failed");
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_btn_edit_inventoryActionPerformed

    private void btn_delete_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_inventoryActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "DELETE FROM t_inventory WHERE id ='"+F_inventory_id.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete Inventory Success");
                clear_form_inventory();
                ttag.requestFocus();
                dt_inventory();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Delete Employee Failed");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_delete_inventoryActionPerformed

    private void btn_clear_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_inventoryActionPerformed
        clear_form_inventory();
        dt_inventory();
    }//GEN-LAST:event_btn_clear_inventoryActionPerformed

    private void tabelinventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelinventoryMouseClicked

        int bar = tabelinventory.getSelectedRow();
        String id_inventory = tabmode.getValueAt(bar, 0).toString();
        String tag = tabmode.getValueAt(bar, 1).toString();
        String model = tabmode.getValueAt(bar, 2).toString();
        String cattegory = tabmode.getValueAt(bar, 3).toString();
        String qty = tabmode.getValueAt(bar, 4).toString();
        String description = tabmode.getValueAt(bar, 5).toString();
        String status = tabmode.getValueAt(bar, 6).toString();

        F_inventory_id.setText(id_inventory);
        ttag.setText(tag);
        tmodel.setText(model);
        tkat.setText(cattegory);
        tqty.setText(qty);
        jtk.setText(description);
        tstatus.setText(status);
        btn_edit_inventory.setEnabled(true);
        btn_save_inventory.setEnabled(false);
        btn_delete_inventory.setEnabled(true);
    }//GEN-LAST:event_tabelinventoryMouseClicked

    private void btn_search_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_inventoryActionPerformed
        Object[]Baris = {"No","Tag","Model","Cattegory","Qty","Description","Status","Created Date"};
        tabmode= new DefaultTableModel(null, Baris);
        tabelinventory.setModel(tabmode);
        String sql = "SELECT * FROM t_inventory "
                + "WHERE tag LIKE '%"+ tcari_inventory.getText() +"%' "
                + "OR model LIKE '%"+ tcari_inventory.getText() +"%' "
                + "OR cattegory LIKE '%"+ tcari_inventory.getText() +"%' "
                + "OR qty LIKE '%"+tcari_inventory.getText()+"%' "
                + "OR description LIKE '%"+tcari_inventory.getText()+"%' "
                + "OR status LIKE '%"+tcari_inventory.getText()+"%' ";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("tag");
                String c = hasil.getString("model");
                String d = hasil.getString("cattegory");
                String e = hasil.getString("qty");
                String f = hasil.getString("description");
                String g = hasil.getString("status");
                Date datetime = hasil.getDate("created_at");
                String formaterDate = "dd/MM/yyyy";
                DateFormat df = new SimpleDateFormat(formaterDate);
                String h = df.format(datetime);

                String[] data={a,b,c,d,e,f,g,h};
                tabmode.addRow(data);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Table Error");
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_search_inventoryActionPerformed

    private void btn_save_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_employeeActionPerformed
        
        String employee_id = tid.getText();
        String name = tnama.getText();
        String email = temail.getText();
        String password = "123";
        String gender = "";
        if(rjk1.isSelected()){
            gender = "Laki-laki";
        }else{
            gender = "Perempuan";
        }
        String phone = tphone.getText();
        String address = talamat.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        //VALIDATION
        if ( employee_id.trim().length() == 0 || name.trim().length() == 0 || gender.trim().length() == 0 || email.trim().length() == 0 || phone.trim().length() == 0 || address.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else if(!email.matches(EMAIL_PATTERN)){
            JOptionPane.showMessageDialog(null,"Email required (@) !");
        } else{
            String query = "INSERT INTO t_employee (\n" +
                            "employee_id,\n" +
                            "name,\n" +
                            "email,\n" +
                            "password,\n" +
                            "gender,\n" +
                            "address,\n" +
                            "phone,\n" +
                            "created_at\n" +
                            ") VALUES(?,?,?,MD5(?),?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, name);
                stat.setString(3, email);
                stat.setString(4, password);
                stat.setString(5, gender);
                stat.setString(6, address);
                stat.setString(7, phone);
                stat.setString(8, datetime);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Create Employee Success");
                clear_form_employee();
                tid.requestFocus();
                dt_employee();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create Employee Failed!");
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_btn_save_employeeActionPerformed

    private void btn_edit_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_employeeActionPerformed
        String id = F_employee_id.getText();
        String employee_id = tid.getText();
        String name = tnama.getText();
        String email = temail.getText();
        String password = "123";
        String gender = "";
        if(rjk1.isSelected()){
            gender = "Laki-laki";
        }else{
            gender = "Perempuan";
        }
        String phone = tphone.getText();
        String address = talamat.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        
        //VALIDATION
        if ( employee_id.trim().length() == 0 || name.trim().length() == 0 || gender.trim().length() == 0 || email.trim().length() == 0 || phone.trim().length() == 0 || address.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else if(!email.matches(EMAIL_PATTERN)){
            JOptionPane.showMessageDialog(null,"Email required (@) !");
        } else{
            try {
                String sql = "UPDATE t_employee SET  \n" +
                                "employee_id =?,\n" +
                                "name =?,\n" +
                                "email =?,\n" +
                                "gender =?,\n" +
                                "address =?,\n" +
                                "phone =?,\n" +
                                "updated_at =?\n" +
                            "WHERE id = ?";
                PreparedStatement stat = conn.prepareStatement (sql);
                stat.setString(1, employee_id);
                stat.setString(2, name);
                stat.setString(3, email);
                stat.setString(4, gender);
                stat.setString(5, address);
                stat.setString(6, phone);
                stat.setString(7, datetime);
                stat.setString(8, id);

                stat.executeUpdate();
                JOptionPane.showMessageDialog (null, "Edit Employee Success");
                clear_form_employee();
                tid.requestFocus();
                dt_employee();

            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Edit Employee Failed");
                System.out.println(e);
            }
        }

    }//GEN-LAST:event_btn_edit_employeeActionPerformed

    private void btn_delete_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_employeeActionPerformed
        
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "DELETE FROM t_employee WHERE id= "+F_employee_id.getText();
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog (null, "Delete Employee Success");
                clear_form_employee();
                tid.requestFocus();
                dt_employee();

            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Delete Employee Failed");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_delete_employeeActionPerformed

    private void btn_clear_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_employeeActionPerformed
        clear_form_employee();
        dt_employee();
    }//GEN-LAST:event_btn_clear_employeeActionPerformed

    private void tidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidActionPerformed

    private void btn_search_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_employeeActionPerformed
        Object [] Baris = {"No","Employee ID","Name","Gender","Email","Phone","Address"};
        tabmode = new DefaultTableModel(null, Baris);
        tableEmployee.setModel(tabmode);
        String sql = "SELECT * FROM t_employee "
                + "WHERE employee_id LIKE '%"+ tcari1.getText() +"%' "
                + "OR name LIKE '%"+ tcari1.getText() +"%' "
                + "OR gender LIKE '%"+ tcari1.getText() +"%' "
                + "OR email LIKE '%"+tcari1.getText()+"%' "
                + "OR address LIKE '%"+tcari1.getText()+"%' "
                + "OR phone LIKE '%"+tcari1.getText()+"%' ";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString ("id");
                String b = hasil.getString ("employee_id");
                String c = hasil.getString ("name");
                String d = hasil.getString ("gender");
                String e = hasil.getString ("email");
                String f = hasil.getString ("phone");
                String g = hasil.getString ("address");

                String [] data= {a,b,c,d,e,f,g};
                tabmode.addRow(data);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Table Error");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btn_search_employeeActionPerformed

    private void tableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmployeeMouseClicked
        
        int bar = tableEmployee.getSelectedRow();
        String id_employee = tabmode.getValueAt (bar, 0).toString();
        String employee_id = tabmode.getValueAt (bar, 1).toString();
        String name = tabmode.getValueAt (bar, 2).toString();
        String gender = tabmode.getValueAt (bar,3).toString();
        String email = tabmode.getValueAt (bar, 4).toString();
        String phone = tabmode.getValueAt (bar, 5).toString();
        String address = tabmode.getValueAt (bar, 6).toString();
        
        F_employee_id.setText(id_employee);
        tid.setText (employee_id);
        tnama.setText (name);
        if(gender.toLowerCase().equals("laki-laki")) {rjk1.setSelected(true);rjk2.setSelected(false);}
        else {rjk1.setSelected(false); rjk2.setSelected(true);}
        temail.setText(email);
        tphone.setText(phone);
        talamat.setText(address);
        btn_edit_employee.setEnabled(true);
        btn_save_employee.setEnabled(false);
        btn_delete_employee.setEnabled(true);
        
    }//GEN-LAST:event_tableEmployeeMouseClicked

    private void btn_icon_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_icon_userMouseClicked

    }//GEN-LAST:event_btn_icon_userMouseClicked

    int clicked = 0;
    private void btn_icon_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_icon_userActionPerformed
        if(clicked%2 == 0){
                panel_btn_profile.setVisible(true);
            }else{
                panel_btn_profile.setVisible(false);
            }
        clicked++;
    }//GEN-LAST:event_btn_icon_userActionPerformed

    private void tphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tphoneActionPerformed

    private void temailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_temailActionPerformed

    private void ttagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttagActionPerformed

    private void F_inventory_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_inventory_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_inventory_idActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        JOptionPane.showMessageDialog(null,"Logout Success");
        login loginPage = new login();
        loginPage.setVisible(true);
        loginPage.pack();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void ttag1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttag1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttag1ActionPerformed

    private void btn_save_inventory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_inventory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_save_inventory1ActionPerformed

    private void btn_edit_inventory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_inventory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_inventory1ActionPerformed

    private void btn_delete_inventory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_inventory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_delete_inventory1ActionPerformed

    private void btn_clear_inventory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_inventory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clear_inventory1ActionPerformed

    private void F_inventory_id1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_inventory_id1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_inventory_id1ActionPerformed

    private void tabelinventory1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelinventory1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelinventory1MouseClicked

    private void btn_search_inventory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_inventory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search_inventory1ActionPerformed

    private void F_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_divisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_divisionActionPerformed

    private void btn_save_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_divisionActionPerformed
        
        String division = F_division.getText();
        String inCharge = CB_inCharge.getSelectedItem().toString();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String id = "";
        
        String regex = "-";
        // System.out.println(inCharge.indexOf(regex));
        String employee_id = inCharge.substring(0, inCharge.indexOf(regex));
        
        //VALIDATION
        if ( division.trim().length() == 0 || "--- choose ---".equals(inCharge)){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else{
            // Get Id for Boss Id
            String query_get_employee_id = "SELECT * FROM t_employee WHERE employee_id='"+employee_id+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            String query = "INSERT INTO t_division (\n" +
                            "name,\n" +
                            "boss_id,\n" +
                            "created_at\n" +
                            ") VALUES(?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, division);
                stat.setString(2, id);
                stat.setString(3, datetime);
                stat.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Create Division Success");
                clear_form_division();
                dt_division();
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create Division Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_save_divisionActionPerformed

    private void btn_edit_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_divisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_divisionActionPerformed

    private void btn_delete_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_divisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_delete_divisionActionPerformed

    private void btn_clear_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_divisionActionPerformed
        clear_form_division();
        dt_division();
        
    }//GEN-LAST:event_btn_clear_divisionActionPerformed

    private void tabelinventory2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelinventory2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelinventory2MouseClicked

    private void btn_search_inventory2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_inventory2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search_inventory2ActionPerformed

    private void CB_inChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_inChargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_inChargeActionPerformed

    private void table_divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_divisionMouseClicked
        
        btn_edit_division.setEnabled(true);
        btn_save_division.setEnabled(false);
        btn_delete_division.setEnabled(true);
        //{"No","Division","In Charge","Created Date"};
        int bar = table_division.getSelectedRow();
        String division_id = tabmode.getValueAt (bar, 0).toString();
        String division_name = tabmode.getValueAt (bar, 1).toString();
        String boss_id = tabmode.getValueAt (bar, 2).toString();
        
        F_division_id.setText(division_id);
        F_division.setText(division_name);
        CB_inCharge.setSelectedItem(boss_id);
        
    }//GEN-LAST:event_table_divisionMouseClicked

    private void jScrollPane7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane7MouseClicked

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
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BreadCrumb;
    private javax.swing.JPanel BreadCrumb1;
    private javax.swing.JPanel BreadCrumb2;
    private javax.swing.JPanel BreadCrumb3;
    private javax.swing.JComboBox<String> CB_inCharge;
    private javax.swing.JTextField F_division;
    private javax.swing.JTextField F_division_id;
    private javax.swing.JTextField F_employee_id;
    private javax.swing.JTextField F_inventory_id;
    private javax.swing.JTextField F_inventory_id1;
    private javax.swing.JLabel INVENTORY;
    private javax.swing.JLabel INVENTORY1;
    private javax.swing.JLabel INVENTORY2;
    private javax.swing.JButton btn_clear_division;
    private javax.swing.JButton btn_clear_employee;
    private javax.swing.JButton btn_clear_inventory;
    private javax.swing.JButton btn_clear_inventory1;
    private javax.swing.JButton btn_delete_division;
    private javax.swing.JButton btn_delete_employee;
    private javax.swing.JButton btn_delete_inventory;
    private javax.swing.JButton btn_delete_inventory1;
    private javax.swing.JButton btn_edit_division;
    private javax.swing.JButton btn_edit_employee;
    private javax.swing.JButton btn_edit_inventory;
    private javax.swing.JButton btn_edit_inventory1;
    private javax.swing.JButton btn_icon_user;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_menu_asset;
    private javax.swing.JButton btn_menu_division;
    private javax.swing.JButton btn_menu_employee;
    private javax.swing.JButton btn_menu_inventory;
    private javax.swing.JButton btn_profile;
    private javax.swing.JButton btn_save_division;
    private javax.swing.JButton btn_save_employee;
    private javax.swing.JButton btn_save_inventory;
    private javax.swing.JButton btn_save_inventory1;
    private javax.swing.JButton btn_search_employee;
    private javax.swing.JButton btn_search_inventory;
    private javax.swing.JButton btn_search_inventory1;
    private javax.swing.JButton btn_search_inventory2;
    private javax.swing.JTabbedPane content;
    private javax.swing.JLabel employee;
    private javax.swing.JPanel footer;
    private Layout.gradient_footer gradient_footer1;
    private Layout.gradient_footer gradient_footer2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel jkel;
    private javax.swing.JTextArea jtk;
    private javax.swing.JTextArea jtk1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel panel_asset;
    private javax.swing.JPanel panel_btn_profile;
    private javax.swing.JPanel panel_division;
    private javax.swing.JPanel panel_employee;
    private javax.swing.JPanel panel_inventory;
    private javax.swing.JRadioButton rjk1;
    private javax.swing.JRadioButton rjk2;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable tabelinventory;
    private javax.swing.JTable tabelinventory1;
    private javax.swing.JTable tabelinventory2;
    private javax.swing.JTable tableEmployee;
    private javax.swing.JTable table_division;
    private javax.swing.JTextArea talamat;
    private javax.swing.JTextField tcari1;
    private javax.swing.JTextField tcari_inventory;
    private javax.swing.JTextField tcari_inventory1;
    private javax.swing.JTextField tcari_inventory2;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tkat;
    private javax.swing.JTextField tkat1;
    private javax.swing.JTextField tmodel;
    private javax.swing.JTextField tmodel1;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tphone;
    private javax.swing.JTextField tqty;
    private javax.swing.JTextField tqty1;
    private javax.swing.JTextField tstatus;
    private javax.swing.JTextField tstatus1;
    private javax.swing.JTextField ttag;
    private javax.swing.JTextField ttag1;
    // End of variables declaration//GEN-END:variables
}
