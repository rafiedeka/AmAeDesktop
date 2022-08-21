/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

import javax.swing.*;
import java.sql.*;
import Config.Db;
import static Layout.login.Result;
import static Layout.login.division_name;
import static Layout.login.employee_id;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DateFormatter;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.CalendarUtils;

/**
 *
 * @author rafie
 */
public final class admin extends javax.swing.JFrame {
    static Connection conn = Db.getConnection();
    static Statement db;
    static ResultSet Result;
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    
    
    public admin() {
        
        if(null == Session.employee_id){
            JOptionPane.showMessageDialog(null,"Session Expired!");
            login loginPage = new login();
            loginPage.setVisible(true);
            loginPage.pack();
            this.dispose();
            
        }else{
            initComponents();
            // SET CENTER
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            
            panel_btn_profile.setVisible(false);

            dt_employee();
            clear_form_employee();
            F_employee_id.setVisible(false);

            dt_inventory();
            clear_form_inventory();
            F_inventory_id.setVisible(false);

            CB_employee();
            CB_division();
            CB_level();
            dt_division();
            clear_form_division();
            clear_form_list_user_division();
            F_division_id.setVisible(false);
            F_user_division_id.setVisible(false);


            CB_asset_employee();
            CB_asset_inventory();
            dt_asset();
            F_asset_id.setVisible(false);
            clear_form_asset();
            
            setUpFormPayment();
            
        }
        
        
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
        public void CB_employee(){
            try{
                db = conn.createStatement();
                String query = "SELECT id, employee_id,name FROM t_employee ";

                Result = db.executeQuery(query);
                CB_inCharge.addItem("--- choose ---");
                CB_employee_list_user_division.addItem("--- choose ---");
                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    CB_inCharge.addItem(data);
                    CB_employee_list_user_division.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "CB In Charge Data Error");
                JOptionPane.showMessageDialog(null, "CB Employee Data Error");
                System.out.println(e);
            }
        }
        
        public void CB_division(){
            CB_division_list_user_division.removeAllItems();
            try{
                db = conn.createStatement();
                String query = "SELECT name FROM t_division ";

                Result = db.executeQuery(query);
                CB_division_list_user_division.addItem("--- choose ---");
                while(Result.next()){
                    String name = Result.getString("name");
                    CB_division_list_user_division.addItem(name);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "CB division Error");
                System.out.println(e);
            }
        }
        
        public void CB_level(){
            CB_level_list_user_division.addItem("--- choose ---");
            CB_level_list_user_division.addItem("administrator");
            CB_level_list_user_division.addItem("user");
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
        
        protected void dt_list_user_division(List<UserDivisi> param){
            try{
                Object[]Baris = {"No","Employee","Division","Level","Created Date"};
                tabmode2 = new DefaultTableModel(null, Baris);
                table_list_user_division.setModel(tabmode2);
            
                for(UserDivisi val : param){
                    String[] item = {
                        val.getId(),
                        val.getEmployeeId()+" - "+val.getName(),
                        val.getDivision(),
                        val.getIdLevel(),
                        val.getCreatedAt()
                        };
                    
                    tabmode2.addRow(item);
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
            List<UserDivisi> value = new ArrayList<UserDivisi>();
            dt_list_user_division(value);
            clear_form_list_user_division();
        }
        
        protected void clear_form_list_user_division(){
            CB_employee_list_user_division.setSelectedIndex(0);
            CB_division_list_user_division.setSelectedIndex(0);
            CB_level_list_user_division.setSelectedIndex(0);
            
            btn_save_list_user_division.setEnabled(true);
            btn_edit_list_user_division.setEnabled(false);
            btn_delete_list_user_division.setEnabled(false);
        }
        
        
        
    //ASSET
        public void CB_asset_employee(){
            try{
                db = conn.createStatement();
                String query = "SELECT id, employee_id,name FROM t_employee ";

                Result = db.executeQuery(query);
                CB_inCharge.addItem("--- choose ---");
                F_asset_employee.addItem("--- choose ---");
                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    F_asset_employee.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "F_asset_employee Data Error");
                System.out.println(e);
            }
        }    
        
        public void CB_asset_inventory(){
            try{
                db = conn.createStatement();
                String query = "SELECT * FROM t_inventory ";

                Result = db.executeQuery(query);
                F_asset_inventory.addItem("--- choose ---");
                while(Result.next()){
                    String tag = Result.getString("tag");
                    String model = Result.getString("model");
                    String data = tag + " - " + model;
                    F_asset_inventory.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "F_asset_inventory Data Error");
                System.out.println(e);
            }
        }  
        
        protected void dt_asset(){

            Object[]Baris = {"No","Inventory","Employee","Description","Place","Received Date","Received time","Notes","Created Date"};
            tabmode = new DefaultTableModel(null, Baris);
            table_asset.setModel(tabmode);
            
            String sql = "SELECT a.id, \n" +
                            "CONCAT_WS(' - ',c.tag,c.model) AS inventory, \n" +
                            "CONCAT_WS(' - ',b.employee_id,b.name) AS employee,\n" +
                            "a.description,\n" +
                            "a.place,\n" +
                            "a.received_date,\n" +
                            "a.received_time,\n" +
                            "a.notes,\n" +
                            "a.created_at\n" +
                        "FROM t_asset a\n" +
                        "LEFT JOIN t_employee b ON a.employee_id = b.id\n" +
                        "LEFT JOIN t_inventory c ON a.inventory_id = c.id\n" +
                        "ORDER BY c.created_at DESC";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String a = hasil.getString("id");
                    String b = hasil.getString("inventory");
                    String c = hasil.getString("employee");
                    String d = hasil.getString("description");
                    String e = hasil.getString("place");
                    Date received_date = hasil.getDate("received_date");
                    DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
                    String f = dtf.format(received_date);
                    Time received_time = hasil.getTime("received_time");
                    DateFormat tf = new SimpleDateFormat("HH:mm:ss");
                    String g = tf.format(received_time);
                    String h = hasil.getString("notes");
                    Date datetime = hasil.getDate("created_at");
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String i = df.format(datetime);

                    String[] data={a,b,c,d,e,f,g,h,i};
                    tabmode.addRow(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
        }
        
        protected void clear_form_asset(){
            F_asset_employee.setSelectedIndex(0);
            F_asset_inventory.setSelectedIndex(0);
            F_asset_description.setText("");
            F_asset_place.setText("");
            String datetime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
                F_asset_received_date.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            F_asset_received_hours.setValue(0);
            F_asset_received_minute.setValue(0);
            F_asset_received_second.setValue(0);
            F_asset_notes.setText("");
            F_asset_search.setText("");
            
            btn_save_asset.setEnabled(true);
            btn_edit_asset.setEnabled(false);
            btn_delete_asset.setEnabled(false);

        }
        
    //PAYMENT
        protected void setUpFormPayment(){
            PlainDocument F_basicSalary = (PlainDocument) F_payment_basicSalary.getDocument();
            PlainDocument F_allowance = (PlainDocument) F_payment_allowance.getDocument();
            PlainDocument F_overtime = (PlainDocument) F_payment_overtime.getDocument();
            PlainDocument F_other = (PlainDocument) F_payment_other.getDocument();
            PlainDocument F_bpjstkc = (PlainDocument) F_payment_bpjstkc.getDocument();
            PlainDocument F_bpjsksc = (PlainDocument) F_payment_bpjsksc.getDocument();
            PlainDocument F_eid_allowance = (PlainDocument) F_payment_eid_allowance.getDocument();
            PlainDocument F_total_income = (PlainDocument) F_payment_total_income.getDocument();
            PlainDocument F_bpjstke = (PlainDocument) F_payment_bpjstke.getDocument();
            PlainDocument F_bpjskse = (PlainDocument) F_payment_bpjskse.getDocument();
            PlainDocument F_attendance = (PlainDocument) F_payment_attendance.getDocument();
            PlainDocument F_net_total = (PlainDocument) F_payment_net_total.getDocument();
            
            F_basicSalary.setDocumentFilter(new MyIntFilter());
            F_allowance.setDocumentFilter(new MyIntFilter());
            F_overtime.setDocumentFilter(new MyIntFilter());
            F_other.setDocumentFilter(new MyIntFilter());
            F_bpjstkc.setDocumentFilter(new MyIntFilter());
            F_bpjsksc.setDocumentFilter(new MyIntFilter());
            F_eid_allowance.setDocumentFilter(new MyIntFilter());
            F_total_income.setDocumentFilter(new MyIntFilter());
            F_bpjstke.setDocumentFilter(new MyIntFilter());
            F_bpjskse.setDocumentFilter(new MyIntFilter());
            F_attendance.setDocumentFilter(new MyIntFilter());
            F_net_total.setDocumentFilter(new MyIntFilter());
            
            F_payment_basicSalary.setEnabled(false);
            F_payment_allowance.setEnabled(false);
            F_payment_overtime.setEnabled(false);
            F_payment_other.setEnabled(false);
            F_payment_bpjstkc.setEnabled(false);
            F_payment_bpjsksc.setEnabled(false);
            F_payment_eid_allowance.setEnabled(false);
            F_payment_total_income.setEnabled(false);
            F_payment_bpjstke.setEnabled(false);
            F_payment_bpjskse.setEnabled(false);
            F_payment_attendance.setEnabled(false);
            F_payment_net_total.setEnabled(false);
            
            try{
                db = conn.createStatement();
                String query = "SELECT id, employee_id,name FROM t_employee ";

                Result = db.executeQuery(query);
                F_payment_employee.addItem("--- choose ---");
                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    F_payment_employee.addItem(data);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "F_payment_employee Data Error");
                System.out.println(e);
            }
            
            F_payment_division.setEnabled(false);
            F_payment_absent.setEnabled(false);
            clear_form_payment();
        }
        
        protected void clear_form_payment(){
            F_payment_basicSalary.setText(String.valueOf(0));
            F_payment_allowance.setText(String.valueOf(0));
            F_payment_overtime.setText(String.valueOf(0));
            F_payment_other.setText(String.valueOf(0));
            F_payment_bpjstkc.setText(String.valueOf(0));
            F_payment_bpjsksc.setText(String.valueOf(0));
            F_payment_eid_allowance.setText(String.valueOf(0));
            F_payment_total_income.setText(String.valueOf(0));
            F_payment_bpjstke.setText(String.valueOf(0));
            F_payment_bpjskse.setText(String.valueOf(0));
            F_payment_attendance.setText(String.valueOf(0));
            F_payment_net_total.setText(String.valueOf(0));
            F_payment_employee.setSelectedIndex(0);
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            F_payment_period.setFormats(dateFormat);
            
            
            
            
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
        btn_menu_division = new javax.swing.JButton();
        btn_menu_asset = new javax.swing.JButton();
        btn_menu_payment = new javax.swing.JButton();
        btn_menu_apprasial = new javax.swing.JButton();
        content = new javax.swing.JTabbedPane();
        panel_employee = new javax.swing.JPanel();
        BreadCrumb = new javax.swing.JPanel();
        employee = new javax.swing.JLabel();
        F_employee_id = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jkel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tphone = new javax.swing.JTextField();
        tid = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        rjk1 = new javax.swing.JRadioButton();
        rjk2 = new javax.swing.JRadioButton();
        btn_save_employee = new javax.swing.JButton();
        btn_edit_employee = new javax.swing.JButton();
        btn_delete_employee = new javax.swing.JButton();
        btn_clear_employee = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        temail = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        F_employee_status = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        F_employee_status1 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        talamat = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        btn_print_employee = new javax.swing.JButton();
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
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        F_asset_notes = new javax.swing.JTextArea();
        btn_save_asset = new javax.swing.JButton();
        btn_edit_asset = new javax.swing.JButton();
        btn_delete_asset = new javax.swing.JButton();
        btn_clear_asset = new javax.swing.JButton();
        F_asset_id = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        F_asset_employee = new javax.swing.JComboBox<>();
        F_asset_inventory = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        F_asset_description = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        F_asset_place = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        F_asset_received_date = new org.jdesktop.swingx.JXDatePicker();
        F_asset_received_minute = new javax.swing.JSpinner();
        F_asset_received_second = new javax.swing.JSpinner();
        F_asset_received_hours = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_asset = new javax.swing.JTable();
        F_asset_search = new javax.swing.JTextField();
        btn_search_asset = new javax.swing.JButton();
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
        table_list_user_division = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        CB_employee_list_user_division = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        CB_division_list_user_division = new javax.swing.JComboBox<>();
        btn_save_list_user_division = new javax.swing.JButton();
        btn_edit_list_user_division = new javax.swing.JButton();
        btn_delete_list_user_division = new javax.swing.JButton();
        btn_clear_list_user_division = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        CB_level_list_user_division = new javax.swing.JComboBox<>();
        F_user_division_id = new javax.swing.JTextField();
        panel_salary = new javax.swing.JPanel();
        BreadCrumb4 = new javax.swing.JPanel();
        INVENTORY3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btn_save_division1 = new javax.swing.JButton();
        btn_edit_division1 = new javax.swing.JButton();
        btn_delete_division1 = new javax.swing.JButton();
        btn_clear_payment = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        table_division1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        F_payment_basicSalary = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        F_payment_allowance = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        F_payment_period = new org.jdesktop.swingx.JXDatePicker();
        jLabel46 = new javax.swing.JLabel();
        F_payment_overtime = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        F_payment_other = new javax.swing.JTextField();
        F_payment_employee = new javax.swing.JComboBox<>();
        F_payment_bpjstkc = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        F_payment_bpjsksc = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        F_payment_division = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        F_payment_eid_allowance = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        F_payment_bpjstke = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        F_payment_bpjskse = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        F_payment_total_income = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        F_payment_absent = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        F_payment_attendance = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        F_payment_net_total = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        BreadCrumb5 = new javax.swing.JPanel();
        INVENTORY4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        BreadCrumb6 = new javax.swing.JPanel();
        INVENTORY5 = new javax.swing.JLabel();
        panel_dashboard = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        D_btn_employee = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        D_btn_inventory = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        D_btn_division = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        D_btn_asset = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        D_btn_payment = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        D_btn_apprasial = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
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
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoMouseClicked(evt);
            }
        });

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

        btn_menu_division.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_division.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_division.setText("DIVISION");
        btn_menu_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_divisionActionPerformed(evt);
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

        btn_menu_payment.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_payment.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_payment.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_payment.setText("PAYMENT");
        btn_menu_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_paymentActionPerformed(evt);
            }
        });

        btn_menu_apprasial.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_apprasial.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_apprasial.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_apprasial.setText("APPRASIAL");
        btn_menu_apprasial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_apprasialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_menu_asset, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_menu_inventory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_division, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_menu_payment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_menu_apprasial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
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
                .addComponent(btn_menu_division, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(389, Short.MAX_VALUE))
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
                .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(449, 449, 449)
                .addComponent(employee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumbLayout.setVerticalGroup(
            BreadCrumbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumbLayout.createSequentialGroup()
                .addGroup(BreadCrumbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee)
                    .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel38.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel38.setText("Marital Status");

        F_employee_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", " " }));

        jLabel39.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel39.setText("Religion");

        F_employee_status1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Moslem", "Christian", "Buddha", "Hindu" }));

        jLabel40.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel40.setText("NIK");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel10.setText("Address");

        talamat.setColumns(20);
        talamat.setRows(5);
        jScrollPane3.setViewportView(talamat);

        jLabel41.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel41.setText("NPWP");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel42.setText("BPJS TK");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel43.setText("BPJS KS");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel44.setText("NO. Rek");

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        btn_print_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_employee.setText("PRINT");
        btn_print_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_employeeActionPerformed(evt);
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tid)
                                            .addComponent(tnama)
                                            .addComponent(temail)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(rjk2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(tphone)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(F_employee_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField4)
                                            .addComponent(F_employee_status1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 396, Short.MAX_VALUE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField6)
                                            .addComponent(jTextField7)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jkel)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(101, 101, 101)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jTextField8)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 15, Short.MAX_VALUE)
                                .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_print_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jkel)
                    .addComponent(rjk1)
                    .addComponent(rjk2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(F_employee_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(F_employee_status1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_print_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
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
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        panel_employeeLayout.setVerticalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE))
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
                .addContainerGap(584, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
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

        jLabel18.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel18.setText("Inventory");

        jLabel19.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel19.setText("Description");

        jLabel21.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel21.setText("Notes");

        F_asset_notes.setColumns(20);
        F_asset_notes.setRows(5);
        jScrollPane5.setViewportView(F_asset_notes);

        btn_save_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_asset.setText("SAVE");
        btn_save_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_assetActionPerformed(evt);
            }
        });

        btn_edit_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_asset.setText("EDIT");
        btn_edit_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_assetActionPerformed(evt);
            }
        });

        btn_delete_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_asset.setText("DELETE");
        btn_delete_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_assetActionPerformed(evt);
            }
        });

        btn_clear_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_asset.setText("CLEAR");
        btn_clear_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_assetActionPerformed(evt);
            }
        });

        F_asset_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_asset_idActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel28.setText("Employee");

        F_asset_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_asset_employeeActionPerformed(evt);
            }
        });

        F_asset_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_asset_inventoryActionPerformed(evt);
            }
        });

        F_asset_description.setColumns(20);
        F_asset_description.setRows(5);
        jScrollPane9.setViewportView(F_asset_description);

        jLabel29.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel29.setText("Place");

        jLabel31.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel31.setText("Received DateTime");

        F_asset_received_minute.setModel(new javax.swing.SpinnerNumberModel(0, null, 60, 1));

        F_asset_received_second.setModel(new javax.swing.SpinnerNumberModel(0, null, 60, 1));

        F_asset_received_hours.setModel(new javax.swing.SpinnerNumberModel(0, null, 60, 1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_save_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btn_edit_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_delete_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btn_clear_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(F_asset_id, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(F_asset_received_date, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F_asset_received_hours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(F_asset_received_minute, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(F_asset_received_second, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9)
                            .addComponent(F_asset_place, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_asset_employee, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(F_asset_inventory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(F_asset_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(F_asset_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel19))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(F_asset_place, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(F_asset_received_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88)
                .addComponent(F_asset_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        table_asset.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        table_asset.setModel(new javax.swing.table.DefaultTableModel(
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
        table_asset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_assetMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(table_asset);

        btn_search_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_asset.setText("CARI");
        btn_search_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_assetActionPerformed(evt);
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
                        .addComponent(F_asset_search, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(F_asset_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_assetLayout = new javax.swing.GroupLayout(panel_asset);
        panel_asset.setLayout(panel_assetLayout);
        panel_assetLayout.setHorizontalGroup(
            panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_assetLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_assetLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panel_assetLayout.setVerticalGroup(
            panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_assetLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panel_assetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_divisionMouseEntered(evt);
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
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_edit_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        table_list_user_division.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        table_list_user_division.setModel(new javax.swing.table.DefaultTableModel(
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
        table_list_user_division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_list_user_divisionMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(table_list_user_division);

        jLabel30.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel30.setText("List User Of Division");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel2.setText("Employee");

        jLabel26.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel26.setText("Division");

        btn_save_list_user_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_list_user_division.setText("SAVE");
        btn_save_list_user_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_list_user_divisionActionPerformed(evt);
            }
        });

        btn_edit_list_user_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_list_user_division.setText("EDIT");
        btn_edit_list_user_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_list_user_divisionActionPerformed(evt);
            }
        });

        btn_delete_list_user_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_list_user_division.setText("DELETE");
        btn_delete_list_user_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_list_user_divisionActionPerformed(evt);
            }
        });

        btn_clear_list_user_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_list_user_division.setText("CLEAR");
        btn_clear_list_user_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_list_user_divisionActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel27.setText("Level");

        F_user_division_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_user_division_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CB_level_list_user_division, 0, 495, Short.MAX_VALUE)
                            .addComponent(CB_division_list_user_division, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CB_employee_list_user_division, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(btn_save_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(btn_edit_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_delete_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btn_clear_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(F_user_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel30)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_employee_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_division_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_level_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(58, 58, 58)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear_list_user_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(F_user_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
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

        BreadCrumb4.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY3.setText("PAYMENT");

        javax.swing.GroupLayout BreadCrumb4Layout = new javax.swing.GroupLayout(BreadCrumb4);
        BreadCrumb4.setLayout(BreadCrumb4Layout);
        BreadCrumb4Layout.setHorizontalGroup(
            BreadCrumb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb4Layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(INVENTORY3)
                .addContainerGap(590, Short.MAX_VALUE))
        );
        BreadCrumb4Layout.setVerticalGroup(
            BreadCrumb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb4Layout.createSequentialGroup()
                .addComponent(INVENTORY3)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btn_save_division1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_division1.setText("SAVE");
        btn_save_division1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_division1ActionPerformed(evt);
            }
        });

        btn_edit_division1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_division1.setText("EDIT");
        btn_edit_division1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_division1ActionPerformed(evt);
            }
        });

        btn_delete_division1.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_division1.setText("DELETE");
        btn_delete_division1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_division1ActionPerformed(evt);
            }
        });

        btn_clear_payment.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_payment.setText("CLEAR");
        btn_clear_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_paymentActionPerformed(evt);
            }
        });

        jScrollPane10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane10MouseClicked(evt);
            }
        });

        table_division1.setModel(new javax.swing.table.DefaultTableModel(
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
        table_division1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_division1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_division1MouseEntered(evt);
            }
        });
        jScrollPane10.setViewportView(table_division1);

        jLabel20.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel20.setText("Basic Salary");

        jLabel22.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel22.setText("Allowance");

        jLabel45.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel45.setText("Period");

        jLabel46.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel46.setText("Overtime");

        jLabel47.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel47.setText("Employee");

        jLabel49.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel49.setText("Others");

        F_payment_employee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                F_payment_employeeItemStateChanged(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel50.setText("BPJS TK (Company)");

        jLabel51.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel51.setText("BPJS KS (Company)");

        jLabel52.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel52.setText("Division");

        jLabel53.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel53.setText("Eid Allowance");

        jLabel54.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel54.setText("BPJS TK (Employee)");

        jLabel55.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel55.setText("BPJS KS (Employee)");

        jLabel56.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel56.setText("TOTAL INCOME");

        jLabel57.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel57.setText("Absent");

        jLabel58.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel58.setText("Attendance");

        jLabel59.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel59.setText("NET TOTAL");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47)
                            .addComponent(jLabel52)
                            .addComponent(jLabel57))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(F_payment_absent, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_period, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_division, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(F_payment_other, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_overtime, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_bpjstkc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_basicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(18, 18, 18)
                                .addComponent(F_payment_bpjsksc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(57, 57, 57)
                                .addComponent(F_payment_eid_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(47, 47, 47)
                                .addComponent(F_payment_total_income, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel58))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(F_payment_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_bpjskse, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F_payment_bpjstke, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(F_payment_net_total, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_save_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_clear_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10))
                        .addGap(26, 26, 26))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(F_payment_period, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(F_payment_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(F_payment_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(F_payment_basicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(F_payment_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel54)
                                    .addComponent(F_payment_bpjstke, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55)
                                    .addComponent(F_payment_bpjskse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel46)
                                .addComponent(F_payment_overtime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel58)
                                .addComponent(F_payment_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(F_payment_other, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(F_payment_bpjstkc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel57)
                        .addComponent(F_payment_absent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(F_payment_net_total, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(F_payment_bpjsksc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(F_payment_eid_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(F_payment_total_income, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_division1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_salaryLayout = new javax.swing.GroupLayout(panel_salary);
        panel_salary.setLayout(panel_salaryLayout);
        panel_salaryLayout.setHorizontalGroup(
            panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_salaryLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BreadCrumb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_salaryLayout.setVerticalGroup(
            panel_salaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salaryLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        content.addTab("tab5", panel_salary);

        BreadCrumb5.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY4.setText("APPRASIAL");

        javax.swing.GroupLayout BreadCrumb5Layout = new javax.swing.GroupLayout(BreadCrumb5);
        BreadCrumb5.setLayout(BreadCrumb5Layout);
        BreadCrumb5Layout.setHorizontalGroup(
            BreadCrumb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb5Layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(INVENTORY4)
                .addContainerGap(576, Short.MAX_VALUE))
        );
        BreadCrumb5Layout.setVerticalGroup(
            BreadCrumb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb5Layout.createSequentialGroup()
                .addComponent(INVENTORY4)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BreadCrumb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        content.addTab("tab6", jPanel12);

        BreadCrumb6.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY5.setText("TEST");

        javax.swing.GroupLayout BreadCrumb6Layout = new javax.swing.GroupLayout(BreadCrumb6);
        BreadCrumb6.setLayout(BreadCrumb6Layout);
        BreadCrumb6Layout.setHorizontalGroup(
            BreadCrumb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb6Layout.createSequentialGroup()
                .addGap(572, 572, 572)
                .addComponent(INVENTORY5)
                .addContainerGap(630, Short.MAX_VALUE))
        );
        BreadCrumb6Layout.setVerticalGroup(
            BreadCrumb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb6Layout.createSequentialGroup()
                .addComponent(INVENTORY5)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BreadCrumb6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(643, Short.MAX_VALUE))
        );

        content.addTab("tab7", jPanel13);

        D_btn_employee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/employee.png"))); // NOI18N
        D_btn_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_employeeActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel32.setText("Employee");

        D_btn_inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/inventory.png"))); // NOI18N
        D_btn_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_inventoryActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel33.setText("Inventory");

        D_btn_division.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/division.png"))); // NOI18N
        D_btn_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_divisionActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel34.setText("Division");

        D_btn_asset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/asset.png"))); // NOI18N
        D_btn_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_assetActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel35.setText("Asset");

        D_btn_payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/payment.png"))); // NOI18N
        D_btn_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_paymentActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel36.setText("Payment");

        D_btn_apprasial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/apprasial.png"))); // NOI18N
        D_btn_apprasial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_apprasialActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel37.setText("Apprasial");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D_btn_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D_btn_division, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D_btn_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(180, 180, 180)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D_btn_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D_btn_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D_btn_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(345, 345, 345))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_division, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)))
                .addGap(25, 25, 25)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(D_btn_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_dashboardLayout = new javax.swing.GroupLayout(panel_dashboard);
        panel_dashboard.setLayout(panel_dashboardLayout);
        panel_dashboardLayout.setHorizontalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashboardLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        panel_dashboardLayout.setVerticalGroup(
            panel_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dashboardLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        content.addTab("tab8", panel_dashboard);

        content.setSelectedIndex(7);

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
        //SESSION DESTROY
        Session.employee_id = "";
        Session.name = "";
        Session.division = "";
        Session.email = "";
        login loginPage = new login();
        loginPage.setVisible(true);
        loginPage.pack();
        this.dispose();
        
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_save_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_assetActionPerformed
        String val_employee = F_asset_employee.getSelectedItem().toString();
        String val_inventory = F_asset_inventory.getSelectedItem().toString();
        String val_description = F_asset_description.getText();
        String val_place = F_asset_place.getText();
        DateFormat sysDate = new SimpleDateFormat("yyyy/MM/dd");
        String val_received_date = sysDate.format(F_asset_received_date.getDate()).toString();
        
        String val_received_hours = F_asset_received_hours.getValue().toString();
        String val_received_minute = F_asset_received_minute.getValue().toString();
        String val_received_second = F_asset_received_second.getValue().toString();
        String val_received_time = val_received_hours + ":" + val_received_minute + ":" +val_received_second;
        String val_notes = F_asset_notes.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        String regex = "-";
        String val_employee_id = val_employee.substring(0, val_employee.indexOf(regex));
        String val_inventory_tag = val_inventory.substring(0, val_inventory.indexOf(regex));
        String employee_id = "";
        String inventory_id = "";
        
        //VALIDATION
        if ( "--- choose ---".equals(val_employee) || "--- choose ---".equals(val_inventory) || val_description.trim().length() == 0 || val_place.trim().length() == 0 || val_notes.trim().length() == 0 || val_received_date.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else{
            
            // Get Id for Employee
            String query_get_employee_id = "SELECT * FROM t_employee WHERE employee_id='"+val_employee_id+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    employee_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            // Get Id for Inventory
            String query_get_inventory_id = "SELECT * FROM t_inventory WHERE tag='"+val_inventory_tag+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_inventory_id);
                while (hasil.next()){
                    inventory_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            
            String query = "INSERT INTO t_asset (\n" +
                            "employee_id,\n" +
                            "inventory_id,\n" +
                            "description,\n" +
                            "place,\n" +
                            "received_date,\n" +
                            "received_time,\n" +
                            "notes,\n" +
                            "created_at\n" +
                            ") VALUES(?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, inventory_id);
                stat.setString(3, val_description);
                stat.setString(4, val_place);
                stat.setString(5, val_received_date);
                stat.setString(6, val_received_time);
                stat.setString(7, val_notes);
                stat.setString(8, datetime);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Create Asset Success");
                clear_form_asset();
                F_asset_employee.requestFocus();
                dt_asset();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create Asset Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_save_assetActionPerformed

    private void btn_edit_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_assetActionPerformed
        String val_asset_id = F_asset_id.getText();
        String val_employee = F_asset_employee.getSelectedItem().toString();
        String val_inventory = F_asset_inventory.getSelectedItem().toString();
        String val_description = F_asset_description.getText();
        String val_place = F_asset_place.getText();
        DateFormat sysDate = new SimpleDateFormat("yyyy/MM/dd");
        String val_received_date = sysDate.format(F_asset_received_date.getDate());

        String val_received_hours = F_asset_received_hours.getValue().toString();
        String val_received_minute = F_asset_received_minute.getValue().toString();
        String val_received_second = F_asset_received_second.getValue().toString();
        String val_received_time = val_received_hours + ":" + val_received_minute + ":" +val_received_second;
        String val_notes = F_asset_notes.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        String regex = "-";
        String val_employee_id = val_employee.substring(0, val_employee.indexOf(regex));
        String val_inventory_tag = val_inventory.substring(0, val_inventory.indexOf(regex));
        String employee_id = "";
        String inventory_id = "";

        //VALIDATION
        if ( "--- choose ---".equals(val_employee) || "--- choose ---".equals(val_inventory) || val_description.trim().length() == 0 || val_place.trim().length() == 0 || val_notes.trim().length() == 0 || val_received_date.trim().length() == 0){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else{

            // Get Id for Employee
            String query_get_employee_id = "SELECT * FROM t_employee WHERE employee_id='"+val_employee_id+"'";

            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    employee_id = hasil.getString("id");
                }

            }catch (SQLException ex) {
                System.out.println(ex);
            }

            // Get Id for Inventory
            String query_get_inventory_id = "SELECT * FROM t_inventory WHERE tag='"+val_inventory_tag+"'";

            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_inventory_id);
                while (hasil.next()){
                    inventory_id = hasil.getString("id");
                }

            }catch (SQLException ex) {
                System.out.println(ex);
            }


            String query = "UPDATE t_asset SET "+
                            "employee_id=?,\n" +
                            "inventory_id=?,\n" +
                            "description=?,\n" +
                            "place=?,\n" +
                            "received_date=?,\n" +
                            "received_time=?,\n" +
                            "notes=?,\n" +
                            "updated_at=?\n" +
                            "WHERE id=?";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, inventory_id);
                stat.setString(3, val_description);
                stat.setString(4, val_place);
                stat.setString(5, val_received_date);
                stat.setString(6, val_received_time);
                stat.setString(7, val_notes);
                stat.setString(8, datetime);
                stat.setString(9, val_asset_id);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Edit Asset Success");
                clear_form_asset();
                F_asset_employee.requestFocus();
                dt_asset();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Edit Asset Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_edit_assetActionPerformed

    private void btn_delete_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_assetActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "DELETE FROM t_asset WHERE id= "+F_asset_id.getText();
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog (null, "Delete Asset Success");
                clear_form_asset();
                F_asset_employee.requestFocus();
                dt_asset();

            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Delete Asset Failed");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_delete_assetActionPerformed

    private void btn_clear_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_assetActionPerformed
        clear_form_asset();
        dt_asset();
    }//GEN-LAST:event_btn_clear_assetActionPerformed

    private void F_asset_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_asset_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_asset_idActionPerformed

    private void table_assetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_assetMouseClicked
        btn_save_asset.setEnabled(false);
        btn_edit_asset.setEnabled(true);
        btn_delete_asset.setEnabled(true);
        
        //{"No","Inventory","Employee","Description","Place","Received Datetime","Notes","Created Date"};
        int bar = table_asset.getSelectedRow();
        String asset_id = tabmode.getValueAt (bar, 0).toString();
        String inventory_id = tabmode.getValueAt (bar, 1).toString();
        String employee_id = tabmode.getValueAt (bar, 2).toString();
        String description = tabmode.getValueAt (bar, 3).toString();
        String place = tabmode.getValueAt (bar, 4).toString();
        String received_date = tabmode.getValueAt (bar, 5).toString();
        String received_time = tabmode.getValueAt (bar, 6).toString();
        String notes = tabmode.getValueAt (bar, 7).toString();
        F_asset_id.setText(asset_id);
        F_asset_employee.setSelectedItem(employee_id);
        F_asset_inventory.setSelectedItem(inventory_id);
        F_asset_description.setText(description);
        F_asset_place.setText(place);
        F_asset_notes.setText(notes);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(received_date);
            F_asset_received_date.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //F_asset_received_date.setDate(date);
        String regex = ":";
        String Vhour = received_time.substring(0, received_time.indexOf(regex));
        String Vminute = received_time.substring(received_time.indexOf(regex)+1, received_time.indexOf(regex)+3);
        String Vsecond = received_time.substring(received_time.length()-2, received_time.length());
        F_asset_received_hours.setValue(Integer.parseInt(Vhour));
        F_asset_received_minute.setValue(Integer.parseInt(Vminute));
        F_asset_received_second.setValue(Integer.parseInt(Vsecond));
    }//GEN-LAST:event_table_assetMouseClicked

    private void btn_search_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_assetActionPerformed
        
        Object[]Baris = {"No","Inventory","Employee","Description","Place","Received Datetime","Notes","Created Date"};
        tabmode= new DefaultTableModel(null, Baris);
        table_asset.setModel(tabmode);
        String sql = "SELECT a.id, \n" +
                        "CONCAT_WS(' - ',c.tag,c.model) AS inventory, \n" +
                        "CONCAT_WS(' - ',b.employee_id,b.name) AS employee,\n" +
                        "a.description,\n" +
                        "a.place,\n" +
                        "a.received_date,\n" +
                        "a.received_time,\n" +
                        "a.notes,\n" +
                        "a.created_at\n" +
                    "FROM t_asset a\n" +
                    "LEFT JOIN t_employee b ON a.employee_id = b.id\n" +
                    "LEFT JOIN t_inventory c ON a.inventory_id = c.id\n" +
                    "WHERE CONCAT_WS(' - ',c.tag,c.model) LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR CONCAT_WS(' - ',b.employee_id,b.name) LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR a.description LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR a.place LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR a.received_date LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR a.received_time LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "OR a.notes LIKE '%"+ F_asset_search.getText() +"%'\n" +
                    "ORDER BY c.created_at DESC";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("inventory");
                String c = hasil.getString("employee");
                String d = hasil.getString("description");
                String e = hasil.getString("place");
                Date received_date = hasil.getDate("received_date");
                DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
                String f = dtf.format(received_date);
                Time received_time = hasil.getTime("received_time");
                DateFormat tf = new SimpleDateFormat("HH:mm:ss");
                String g = tf.format(received_time);
                String h = hasil.getString("notes");
                Date datetime = hasil.getDate("created_at");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String i = df.format(datetime);

                String[] data={a,b,c,d,e,f,g,h,i};
                tabmode.addRow(data);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Table Error");
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_btn_search_assetActionPerformed

    private void F_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_divisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_divisionActionPerformed

    private void btn_save_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_divisionActionPerformed
        
        String division = F_division.getText();
        String inCharge = CB_inCharge.getSelectedItem().toString();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String id = "";
        
        String regex = "-";
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
                CB_division();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create Division Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_save_divisionActionPerformed

    private void btn_edit_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_divisionActionPerformed
        
        String divison_id = F_division_id.getText();
        String division = F_division.getText();
        String inCharge = CB_inCharge.getSelectedItem().toString();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String id = "";

        String regex = "-";
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

            String query = "UPDATE t_division SET \n" +
                            "name=?,\n" +
                            "boss_id=?,\n" +
                            "updated_at=?\n" +
                            "WHERE id=?";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, division);
                stat.setString(2, id);
                stat.setString(3, datetime);
                stat.setString(4, divison_id);
                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Edit Division Success");
                clear_form_division();
                dt_division();
                CB_division();
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Edit Division Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_edit_divisionActionPerformed

    private void btn_delete_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_divisionActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            String sql = "DELETE FROM t_division WHERE id= "+F_division_id.getText();
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog (null, "Delete Division Success");
                F_division.requestFocus();
                clear_form_division();
                dt_division();

            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Delete Division Failed");
                System.out.println(e);
            }
        }
        
    }//GEN-LAST:event_btn_delete_divisionActionPerformed

    private void btn_clear_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_divisionActionPerformed
        clear_form_division();
        dt_division();
    }//GEN-LAST:event_btn_clear_divisionActionPerformed

    private void table_list_user_divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_list_user_divisionMouseClicked
    
        btn_save_list_user_division.setEnabled(false);
        btn_edit_list_user_division.setEnabled(true);
        btn_delete_list_user_division.setEnabled(true);
        
        //{"No","Employee","Division","Created Date"};
        int bar = table_list_user_division.getSelectedRow();
        String user_level_id = tabmode2.getValueAt (bar, 0).toString();
        String employee = tabmode2.getValueAt (bar, 1).toString();
        String division = tabmode2.getValueAt (bar, 2).toString();
        String level = tabmode2.getValueAt (bar, 3).toString();
        
        CB_employee_list_user_division.setSelectedItem(employee);
        CB_division_list_user_division.setSelectedItem(division);
        CB_level_list_user_division.setSelectedItem(level);
        F_user_division_id.setText(user_level_id);
        
    }//GEN-LAST:event_table_list_user_divisionMouseClicked

    private void CB_inChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_inChargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_inChargeActionPerformed

    private void table_divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_divisionMouseClicked
        
        btn_save_division.setEnabled(false);
        btn_edit_division.setEnabled(true);
        btn_delete_division.setEnabled(true);
        btn_save_list_user_division.setEnabled(true);
        btn_edit_list_user_division.setEnabled(false);
        btn_delete_list_user_division.setEnabled(false);
        
        //{"No","Division","In Charge","Created Date"};
        int idx = table_division.getSelectedRow();
        String division_id = table_division.getValueAt(idx, 0).toString();
        String division_name = table_division.getValueAt(idx, 1).toString();
        String boss_id = table_division.getValueAt(idx, 2).toString();
        
        F_division_id.setText(division_id);
        F_division.setText(division_name);
        CB_inCharge.setSelectedItem(boss_id);
        
        // list user division
        String sql = "SELECT a.id, b.employee_id, b.name,c.name AS division, a.id_level, d.name AS boss_name, a.created_at \n" +
                    "FROM t_user_level a \n" +
                    "LEFT JOIN t_employee b ON a.id_user = b.id \n" +
                    "LEFT JOIN t_division c ON a.id_division = c.id \n" +
                    "LEFT JOIN t_employee d ON c.boss_id = d.id \n" +
                    "WHERE c.id = "+division_id;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            String e = "";
            List<UserDivisi> value = new ArrayList<UserDivisi>();
            
            if(hasil.first()){
                
                do {
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String g = df.format(datetime);
                    
                    UserDivisi item = new UserDivisi();
                    item.setId(hasil.getString("id"));
                    item.setEmployeeId(hasil.getString("employee_id"));
                    item.setName(hasil.getString("name"));
                    item.setDivision(hasil.getString("division"));
                    item.setIdLevel(hasil.getInt("id_level") == 0 ? "administrator" : "user");
                    item.setBossManId(hasil.getString("boss_name"));
                    item.setCreatedAt(g);
                    value.add(item);
                }
                while (hasil.next()); 
                
                dt_list_user_division(value);
                    
            }else{
                dt_list_user_division(value);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Table Error");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_table_divisionMouseClicked

    private void jScrollPane7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane7MouseClicked

    private void table_divisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_divisionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_divisionMouseEntered

    private void btn_save_list_user_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_list_user_divisionActionPerformed
        
        String val_employee = CB_employee_list_user_division.getSelectedItem().toString();
        String val_division = CB_division_list_user_division.getSelectedItem().toString();
        String level = CB_level_list_user_division.getSelectedItem().toString();
        String id_division = F_division_id.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String employee_id = "";
        String division_id = "";
        Integer val_level;
        
        String regex = "-";
        // System.out.println(inCharge.indexOf(regex));
        String str_employee = val_employee.substring(0, val_employee.indexOf(regex));
        
        //VALIDATION
        if ( "--- choose ---".equals(val_employee) || "--- choose ---".equals(val_division) || "--- choose ---".equals(level) ){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else{
            // Get Id for Employee Id
            String query_get_employee_id = "SELECT * FROM t_employee WHERE employee_id='"+str_employee+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    employee_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            // Get Id for DIvision Id
            String query_get_division_id = "SELECT * FROM t_division WHERE name='"+val_division+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_division_id);
                while (hasil.next()){
                    division_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            if("administrator".equals(level)){
                val_level = 0;
            }else{
                val_level = 1;
            }
            
            // INSERT t_user_level
            String query = "INSERT INTO t_user_level (\n" +
                            "id_user,\n" +
                            "id_division,\n" +
                            "id_level,\n" +
                            "created_at\n" +
                            ") VALUES(?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, division_id);
                stat.setInt(3, val_level);
                stat.setString(4, datetime);
                stat.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Create User Division Success");
                clear_form_list_user_division();
                dt_division();
                CB_division();
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create User Division Failed!");
                System.out.println(e);
            }
            
            if(id_division.trim().length() == 0){
                List<UserDivisi> value = new ArrayList<UserDivisi>();
                dt_list_user_division(value);
                
            }else{
                // list user division
                String sql = "SELECT a.id, b.employee_id, b.name,c.name AS division, a.id_level, d.name AS boss_name, a.created_at \n" +
                            "FROM t_user_level a \n" +
                            "LEFT JOIN t_employee b ON a.id_user = b.id \n" +
                            "LEFT JOIN t_division c ON a.id_division = c.id \n" +
                            "LEFT JOIN t_employee d ON c.boss_id = d.id \n" +
                            "WHERE c.id = "+id_division;
                try{
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(sql);
                    String e = "";
                    List<UserDivisi> value = new ArrayList<UserDivisi>();

                    if(hasil.first()){

                        do {
                            Date v_dt = hasil.getDate("created_at");
                            String formaterDate = "dd/MM/yyyy";
                            DateFormat df = new SimpleDateFormat(formaterDate);
                            String g = df.format(v_dt);

                            UserDivisi item = new UserDivisi();
                            item.setId(hasil.getString("id"));
                            item.setEmployeeId(hasil.getString("employee_id"));
                            item.setName(hasil.getString("name"));
                            item.setDivision(hasil.getString("division"));
                            item.setIdLevel(hasil.getInt("id_level") == 0 ? "administrator" : "user");
                            item.setBossManId(hasil.getString("boss_name"));
                            item.setCreatedAt(g);
                            value.add(item);
                        }
                        while (hasil.next()); 

                        dt_list_user_division(value);

                    }else{
                        dt_list_user_division(value);
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Table Error");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btn_save_list_user_divisionActionPerformed

    private void btn_edit_list_user_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_list_user_divisionActionPerformed
        
        String id_user_level = F_user_division_id.getText();
        String val_employee = CB_employee_list_user_division.getSelectedItem().toString();
        String val_division = CB_division_list_user_division.getSelectedItem().toString();
        String level = CB_level_list_user_division.getSelectedItem().toString();
        String id_division = F_division_id.getText();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String employee_id = "";
        String division_id = "";
        Integer val_level;
        
        String regex = "-";
        // System.out.println(inCharge.indexOf(regex));
        String str_employee = val_employee.substring(0, val_employee.indexOf(regex));
        
        //VALIDATION
        if ( "--- choose ---".equals(val_employee) || "--- choose ---".equals(val_division) || "--- choose ---".equals(level) ){
            JOptionPane.showMessageDialog(null,"Form required!");
        } else{
            // Get Id for Employee Id
            String query_get_employee_id = "SELECT * FROM t_employee WHERE employee_id='"+str_employee+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    employee_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            // Get Id for DIvision Id
            String query_get_division_id = "SELECT * FROM t_division WHERE name='"+val_division+"'";
            
            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_division_id);
                while (hasil.next()){
                    division_id = hasil.getString("id");
                }
                
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            if("administrator".equals(level)){
                val_level = 0;
            }else{
                val_level = 1;
            }
            
            // INSERT t_user_level
            String query = "UPDATE t_user_level \n" +
                            "SET id_user=?,\n" +
                            "id_division=?,\n" +
                            "id_level=?,\n" +
                            "updated_at=?\n" +
                            "WHERE id=?";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, division_id);
                stat.setInt(3, val_level);
                stat.setString(4, datetime);
                stat.setString(5, id_user_level);
                stat.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Edit User Division Success");
                clear_form_list_user_division();
                dt_division();
                CB_division();
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Edit User Division Failed!");
                System.out.println(e);
            }
            
            if(id_division.trim().length() == 0){
                List<UserDivisi> value = new ArrayList<UserDivisi>();
                dt_list_user_division(value);
                
            }else{
                // list user division
                String sql = "SELECT a.id, b.employee_id, b.name,c.name AS division, a.id_level, d.name AS boss_name, a.created_at \n" +
                            "FROM t_user_level a \n" +
                            "LEFT JOIN t_employee b ON a.id_user = b.id \n" +
                            "LEFT JOIN t_division c ON a.id_division = c.id \n" +
                            "LEFT JOIN t_employee d ON c.boss_id = d.id \n" +
                            "WHERE c.id = "+id_division;
                try{
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(sql);
                    String e = "";
                    List<UserDivisi> value = new ArrayList<UserDivisi>();

                    if(hasil.first()){

                        do {
                            Date v_dt = hasil.getDate("created_at");
                            String formaterDate = "dd/MM/yyyy";
                            DateFormat df = new SimpleDateFormat(formaterDate);
                            String g = df.format(v_dt);

                            UserDivisi item = new UserDivisi();
                            item.setId(hasil.getString("id"));
                            item.setEmployeeId(hasil.getString("employee_id"));
                            item.setName(hasil.getString("name"));
                            item.setDivision(hasil.getString("division"));
                            item.setIdLevel(hasil.getInt("id_level") == 0 ? "administrator" : "user");
                            item.setBossManId(hasil.getString("boss_name"));
                            item.setCreatedAt(g);
                            value.add(item);
                        }
                        while (hasil.next()); 

                        dt_list_user_division(value);

                    }else{
                        dt_list_user_division(value);
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Table Error");
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_btn_edit_list_user_divisionActionPerformed

    private void btn_delete_list_user_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_list_user_divisionActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            
            String sql = "DELETE FROM t_user_level WHERE id ='"+F_user_division_id.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete List User Division Success");
                clear_form_list_user_division();
                ttag.requestFocus();
                dt_inventory();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Delete List User Division Failed");
                System.out.println(e);
            }
            
            // list user division
            String query = "SELECT a.id, b.employee_id, b.name,c.name AS division, a.id_level, d.name AS boss_name, a.created_at \n" +
                        "FROM t_user_level a \n" +
                        "LEFT JOIN t_employee b ON a.id_user = b.id \n" +
                        "LEFT JOIN t_division c ON a.id_division = c.id \n" +
                        "LEFT JOIN t_employee d ON c.boss_id = d.id \n" +
                        "WHERE c.id = "+F_division_id.getText();
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query);
                String e = "";
                List<UserDivisi> value = new ArrayList<UserDivisi>();

                if(hasil.first()){

                    do {
                        Date datetime = hasil.getDate("created_at");
                        String formaterDate = "dd/MM/yyyy";
                        DateFormat df = new SimpleDateFormat(formaterDate);
                        String g = df.format(datetime);

                        UserDivisi item = new UserDivisi();
                        item.setId(hasil.getString("id"));
                        item.setEmployeeId(hasil.getString("employee_id"));
                        item.setName(hasil.getString("name"));
                        item.setDivision(hasil.getString("division"));
                        item.setIdLevel(hasil.getInt("id_level") == 0 ? "administrator" : "user");
                        item.setBossManId(hasil.getString("boss_name"));
                        item.setCreatedAt(g);
                        value.add(item);
                    }
                    while (hasil.next()); 

                    dt_list_user_division(value);

                }else{
                    dt_list_user_division(value);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
            
        }
        
    }//GEN-LAST:event_btn_delete_list_user_divisionActionPerformed

    private void btn_clear_list_user_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_list_user_divisionActionPerformed
        clear_form_list_user_division();
        
    }//GEN-LAST:event_btn_clear_list_user_divisionActionPerformed

    private void F_asset_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_asset_employeeActionPerformed
        
    }//GEN-LAST:event_F_asset_employeeActionPerformed

    private void F_asset_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_asset_inventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_asset_inventoryActionPerformed

    private void F_user_division_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_user_division_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_user_division_idActionPerformed

    private void btn_save_division1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_division1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_save_division1ActionPerformed

    private void btn_edit_division1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_division1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_division1ActionPerformed

    private void btn_delete_division1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_division1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_delete_division1ActionPerformed

    private void btn_clear_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_paymentActionPerformed
        clear_form_payment();
    }//GEN-LAST:event_btn_clear_paymentActionPerformed

    private void table_division1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_division1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_division1MouseClicked

    private void table_division1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_division1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_division1MouseEntered

    private void jScrollPane10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane10MouseClicked

    private void btn_menu_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_paymentActionPerformed
        content.setSelectedIndex(4);
    }//GEN-LAST:event_btn_menu_paymentActionPerformed

    private void D_btn_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_employeeActionPerformed
        content.setSelectedIndex(0);
    }//GEN-LAST:event_D_btn_employeeActionPerformed

    private void D_btn_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_divisionActionPerformed
        content.setSelectedIndex(3);      
    }//GEN-LAST:event_D_btn_divisionActionPerformed

    private void D_btn_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_inventoryActionPerformed
        content.setSelectedIndex(1);
    }//GEN-LAST:event_D_btn_inventoryActionPerformed

    private void D_btn_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_assetActionPerformed
        content.setSelectedIndex(2);
    }//GEN-LAST:event_D_btn_assetActionPerformed

    private void D_btn_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_paymentActionPerformed
        content.setSelectedIndex(4);
    }//GEN-LAST:event_D_btn_paymentActionPerformed

    private void D_btn_apprasialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_apprasialActionPerformed
        content.setSelectedIndex(5);
    }//GEN-LAST:event_D_btn_apprasialActionPerformed

    private void logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoMouseClicked
        content.setSelectedIndex(7);
    }//GEN-LAST:event_logoMouseClicked

    private void btn_menu_apprasialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_apprasialActionPerformed
        content.setSelectedIndex(5);
    }//GEN-LAST:event_btn_menu_apprasialActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void F_payment_employeeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_F_payment_employeeItemStateChanged
       if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
          Object item = evt.getItem();
          System.out.println(item);
       }
        
    }//GEN-LAST:event_F_payment_employeeItemStateChanged

    private void btn_print_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_employeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_print_employeeActionPerformed

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
    private javax.swing.JPanel BreadCrumb4;
    private javax.swing.JPanel BreadCrumb5;
    private javax.swing.JPanel BreadCrumb6;
    private javax.swing.JComboBox<String> CB_division_list_user_division;
    private javax.swing.JComboBox<String> CB_employee_list_user_division;
    private javax.swing.JComboBox<String> CB_inCharge;
    private javax.swing.JComboBox<String> CB_level_list_user_division;
    private javax.swing.JButton D_btn_apprasial;
    private javax.swing.JButton D_btn_asset;
    private javax.swing.JButton D_btn_division;
    private javax.swing.JButton D_btn_employee;
    private javax.swing.JButton D_btn_inventory;
    private javax.swing.JButton D_btn_payment;
    private javax.swing.JTextArea F_asset_description;
    private javax.swing.JComboBox<String> F_asset_employee;
    private javax.swing.JTextField F_asset_id;
    private javax.swing.JComboBox<String> F_asset_inventory;
    private javax.swing.JTextArea F_asset_notes;
    private javax.swing.JTextField F_asset_place;
    private org.jdesktop.swingx.JXDatePicker F_asset_received_date;
    private javax.swing.JSpinner F_asset_received_hours;
    private javax.swing.JSpinner F_asset_received_minute;
    private javax.swing.JSpinner F_asset_received_second;
    private javax.swing.JTextField F_asset_search;
    private javax.swing.JTextField F_division;
    private javax.swing.JTextField F_division_id;
    private javax.swing.JTextField F_employee_id;
    private javax.swing.JComboBox<String> F_employee_status;
    private javax.swing.JComboBox<String> F_employee_status1;
    private javax.swing.JTextField F_inventory_id;
    private javax.swing.JTextField F_payment_absent;
    private javax.swing.JTextField F_payment_allowance;
    private javax.swing.JTextField F_payment_attendance;
    private javax.swing.JTextField F_payment_basicSalary;
    private javax.swing.JTextField F_payment_bpjsksc;
    private javax.swing.JTextField F_payment_bpjskse;
    private javax.swing.JTextField F_payment_bpjstkc;
    private javax.swing.JTextField F_payment_bpjstke;
    private javax.swing.JTextField F_payment_division;
    private javax.swing.JTextField F_payment_eid_allowance;
    private javax.swing.JComboBox<String> F_payment_employee;
    private javax.swing.JTextField F_payment_net_total;
    private javax.swing.JTextField F_payment_other;
    private javax.swing.JTextField F_payment_overtime;
    private org.jdesktop.swingx.JXDatePicker F_payment_period;
    private javax.swing.JTextField F_payment_total_income;
    private javax.swing.JTextField F_user_division_id;
    private javax.swing.JLabel INVENTORY;
    private javax.swing.JLabel INVENTORY1;
    private javax.swing.JLabel INVENTORY2;
    private javax.swing.JLabel INVENTORY3;
    private javax.swing.JLabel INVENTORY4;
    private javax.swing.JLabel INVENTORY5;
    private javax.swing.JButton btn_clear_asset;
    private javax.swing.JButton btn_clear_division;
    private javax.swing.JButton btn_clear_employee;
    private javax.swing.JButton btn_clear_inventory;
    private javax.swing.JButton btn_clear_list_user_division;
    private javax.swing.JButton btn_clear_payment;
    private javax.swing.JButton btn_delete_asset;
    private javax.swing.JButton btn_delete_division;
    private javax.swing.JButton btn_delete_division1;
    private javax.swing.JButton btn_delete_employee;
    private javax.swing.JButton btn_delete_inventory;
    private javax.swing.JButton btn_delete_list_user_division;
    private javax.swing.JButton btn_edit_asset;
    private javax.swing.JButton btn_edit_division;
    private javax.swing.JButton btn_edit_division1;
    private javax.swing.JButton btn_edit_employee;
    private javax.swing.JButton btn_edit_inventory;
    private javax.swing.JButton btn_edit_list_user_division;
    private javax.swing.JButton btn_icon_user;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_menu_apprasial;
    private javax.swing.JButton btn_menu_asset;
    private javax.swing.JButton btn_menu_division;
    private javax.swing.JButton btn_menu_employee;
    private javax.swing.JButton btn_menu_inventory;
    private javax.swing.JButton btn_menu_payment;
    private javax.swing.JButton btn_print_employee;
    private javax.swing.JButton btn_profile;
    private javax.swing.JButton btn_save_asset;
    private javax.swing.JButton btn_save_division;
    private javax.swing.JButton btn_save_division1;
    private javax.swing.JButton btn_save_employee;
    private javax.swing.JButton btn_save_inventory;
    private javax.swing.JButton btn_save_list_user_division;
    private javax.swing.JButton btn_search_asset;
    private javax.swing.JButton btn_search_employee;
    private javax.swing.JButton btn_search_inventory;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel jkel;
    private javax.swing.JTextArea jtk;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel panel_asset;
    private javax.swing.JPanel panel_btn_profile;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_division;
    private javax.swing.JPanel panel_employee;
    private javax.swing.JPanel panel_inventory;
    private javax.swing.JPanel panel_salary;
    private javax.swing.JRadioButton rjk1;
    private javax.swing.JRadioButton rjk2;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable tabelinventory;
    private javax.swing.JTable tableEmployee;
    private javax.swing.JTable table_asset;
    private javax.swing.JTable table_division;
    private javax.swing.JTable table_division1;
    private javax.swing.JTable table_list_user_division;
    private javax.swing.JTextArea talamat;
    private javax.swing.JTextField tcari1;
    private javax.swing.JTextField tcari_inventory;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tkat;
    private javax.swing.JTextField tmodel;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tphone;
    private javax.swing.JTextField tqty;
    private javax.swing.JTextField tstatus;
    private javax.swing.JTextField ttag;
    // End of variables declaration//GEN-END:variables
}
