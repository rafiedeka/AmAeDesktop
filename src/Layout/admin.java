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
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
    private DefaultTableModel tabmode3;
    
    
    public admin() {
        
//        if(null == Session.employee_id){
//            JOptionPane.showMessageDialog(null,"Session Expired!");
//            login loginPage = new login();
//            loginPage.setVisible(true);
//            loginPage.pack();
//            this.dispose();
//            
//        }else{
//            
//        }
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
            dt_division();
            clear_form_division();
            F_division_id.setVisible(false);
            
            CB_member_division();
            CB_member_employee();
            CB_member_level();
            F_member_id.setVisible(false);
            clear_form_member();
            dt_member();

            CB_asset_employee();
            CB_asset_inventory();
            dt_asset();
            F_asset_id.setVisible(false);
            clear_form_asset();

            setUpFormPayment();
            F_payment_id.setVisible(false);
            dt_payment();
            
            
            
         
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
            tnik.setText("");
            tmaritalStatus.setSelectedIndex(0);
            treligion.setSelectedIndex(0);
            tnpwp.setText("");
            tbpjstk.setText("");
            tbpjsks.setText("");
            tnorek.setText("");
            F_employee_id.setText("");
            
            
            btn_save_employee.setEnabled(true);
            btn_edit_employee.setEnabled(false);
            btn_delete_employee.setEnabled(false);

        }
    
        protected void dt_employee() {
            Object [] Baris = {"No","Employee ID","Name","Gender","Email","Phone","NIK","Address","Created Date"};
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
                    String g = hasil.getString ("nik");
                    String h = hasil.getString ("address");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String i = df.format(datetime);

                    String [] data= {a,b,c,d,e,f,g,h,i};
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
            tstatus.setSelectedIndex(0);
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
                
                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    CB_inCharge.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "CB In Charge Data Error");
                System.out.println(e);
            }
        }
        
        protected void clear_form_division(){
            F_division.setText("");
            CB_inCharge.setSelectedIndex(0);
            F_division_id.setText("");
            F_division_search.setText("");
            
            btn_save_division.setEnabled(true);
            btn_edit_division.setEnabled(false);
            btn_delete_division.setEnabled(false);
//            List<UserDivisi> value = new ArrayList<UserDivisi>();
//            dt_list_user_division(value);
//            clear_form_list_user_division();
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
        
    //MEMBER DIVISION
        protected void CB_member_division(){
            F_member_division.removeAllItems();
            try{
                db = conn.createStatement();
                String query = "SELECT name FROM t_division ";

                Result = db.executeQuery(query);
                F_member_division.addItem("--- choose ---");
                while(Result.next()){
                    String name = Result.getString("name");
                    F_member_division.addItem(name);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "CB Member Division Error");
                System.out.println(e);
            }
        }
        
        protected void CB_member_employee(){
            try{
                db = conn.createStatement();
                String query = "SELECT id, employee_id,name FROM t_employee ";

                Result = db.executeQuery(query);
                F_member_employee.addItem("--- choose ---");
                
                while(Result.next()){
                    String employee_id = Result.getString("employee_id");
                    String name = Result.getString("name");
                    String data = employee_id + " - " + name;
                    F_member_employee.addItem(data);
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "CB Member Employee Data Error");
                System.out.println(e);
            }
        }
        
        protected void CB_member_level() {
            F_member_level.addItem("--- choose ---");
            F_member_level.addItem("administrator");
            F_member_level.addItem("user");
        }
        
        public void clear_form_member(){
            F_member_division.setSelectedIndex(0);
            F_member_employee.setSelectedIndex(0);
            F_member_level.setSelectedIndex(0);
            F_member_search.setText("");
            
            btn_save_member.setEnabled(true);
            btn_edit_member.setEnabled(false);
            btn_delete_member.setEnabled(false);
        }
        
        protected void dt_member(){
            Object[]Baris = {"No","Employee","Division","Level","In Charge","Created Date"};
            tabmode2 = new DefaultTableModel(null, Baris);
            table_member.setModel(tabmode2);
            String sql = "SELECT a.id, \n" +
                        "CONCAT_WS(\" - \",b.employee_id, b.name) AS employee,\n" +
                        "c.name AS division, \n" +
                        "a.id_level, \n" +
                        "CONCAT_WS(\" - \",d.employee_id, d.name) AS boss_name, \n" +
                        "a.created_at\n" +
                        "FROM t_user_level a\n" +
                        "LEFT JOIN t_employee b ON a.id_user = b.id\n" +
                        "LEFT JOIN t_division c ON a.id_division = c.id\n" +
                        "LEFT JOIN t_employee d ON c.boss_id = d.id\n" +
                        "ORDER BY a.created_at ASC";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String a = hasil.getString("id");
                    String b = hasil.getString("employee");
                    String c = hasil.getString("division");
                    String d = hasil.getInt("id_level") == 0 ? "administrator" : "user";
                    String e = hasil.getString("boss_name");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String f = df.format(datetime);

                    String[] data={a,b,c,d,e,f};
                    tabmode2.addRow(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
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
//            dateTimePicker1.setDateTimeStrict(LocalDateTime.now());
            String datetime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
                F_asset_received_datetime.setDate(date);
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
            PlainDocument F_working_day = (PlainDocument) F_payment_working_day.getDocument();
            PlainDocument F_absent = (PlainDocument) F_payment_absent.getDocument();
            
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
            F_working_day.setDocumentFilter(new MyIntFilter());
            F_absent.setDocumentFilter(new MyIntFilter());
            
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
            clear_form_payment();
        }
        
        protected void open_form_payment(){
            F_payment_basicSalary.setEnabled(true);
            F_payment_allowance.setEnabled(true);
            F_payment_overtime.setEnabled(true);
            F_payment_other.setEnabled(true);
            F_payment_eid_allowance.setEnabled(true);
            F_payment_bpjstkc.setEnabled(false);
            F_payment_bpjstkc.setEditable(false);
            F_payment_bpjsksc.setEnabled(false);
            F_payment_bpjsksc.setEditable(false);
            F_payment_total_income.setEnabled(false);
            F_payment_total_income.setEditable(false);
            F_payment_bpjstke.setEnabled(false);
            F_payment_bpjskse.setEnabled(false);
            F_payment_attendance.setEnabled(false);
            F_payment_net_total.setEnabled(false);
            F_payment_bpjstke.setEditable(false);
            F_payment_bpjskse.setEditable(false);
            F_payment_attendance.setEditable(false);
            F_payment_net_total.setEditable(false);
            F_payment_working_day.setEnabled(true);
            F_payment_absent.setEnabled(true);
            btn_payment_calculate_income.setEnabled(true);
            F_payment_division.setEnabled(false);
            F_payment_division.setEditable(false);
        }
        
        protected void close_form_payment(){
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
            F_payment_working_day.setEnabled(false);
            F_payment_absent.setEnabled(false);
            F_payment_division.setEnabled(false);
            
            F_payment_division.setText("");
            F_payment_working_day.setText(String.valueOf(0));
            F_payment_absent.setText(String.valueOf(0));
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
            
            
            btn_payment_calculate_income.setEnabled(false);
            btn_save_payment.setEnabled(true);
            btn_edit_payment.setEnabled(false);
            btn_delete_payment.setEnabled(false);
        }
        
        protected void clear_form_payment(){
            F_payment_employee.setSelectedIndex(0);
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            F_payment_period.setFormats(dateFormat);
            F_payment_id.setText("");
            dt_payment();
            close_form_payment();
            
        }
        
        public void dt_payment(){
            Object[]Baris = {"No","Period","Employee","Division","Net Total","Created Date"};
            tabmode3 = new DefaultTableModel(null, Baris);
            table_payment.setModel(tabmode3);
            
            String sql = "SELECT a.*,CONCAT_WS(\" - \",b.employee_id,b.name) AS employee, c.name AS division FROM t_payment a\n" +
                        "LEFT JOIN t_employee b ON a.employee_id = b.id\n" +
                        "LEFT JOIN t_employee c ON a.division_id = c.id\n" +
                        "ORDER BY a.created_at DESC";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    
                    String a = hasil.getString("id");
                    Date period = hasil.getDate("period");
                    DateFormat dtf = new SimpleDateFormat("MM/yyyy");
                    String b = dtf.format(period);
                    String c = hasil.getString("employee");
                    String d = hasil.getString("division");
                    String e = hasil.getString("net_total");
                    Date datetime = hasil.getDate("created_at");
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String f = df.format(datetime);

                    String[] data={a,b,c,d,e,f};
                    tabmode3.addRow(data);
                }
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
        }
        
        
        
        
        
        
        
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
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
        btn_menu_division_member = new javax.swing.JButton();
        content = new javax.swing.JTabbedPane();
        panel_employee = new javax.swing.JPanel();
        BreadCrumb = new javax.swing.JPanel();
        employee = new javax.swing.JLabel();
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
        tmaritalStatus = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        treligion = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        tnik = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        talamat = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        tnpwp = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        tbpjstk = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tnorek = new javax.swing.JTextField();
        btn_print_employee = new javax.swing.JButton();
        tbpjsks = new javax.swing.JTextField();
        tcari1 = new javax.swing.JTextField();
        btn_search_employee = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        F_employee_id = new javax.swing.JTextField();
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
        F_inventory_id = new javax.swing.JTextField();
        tstatus = new javax.swing.JComboBox<>();
        btn_print_inventory = new javax.swing.JButton();
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
        F_asset_received_datetime = new org.jdesktop.swingx.JXDatePicker();
        F_asset_received_minute = new javax.swing.JSpinner();
        F_asset_received_second = new javax.swing.JSpinner();
        F_asset_received_hours = new javax.swing.JSpinner();
        btn_print_asset = new javax.swing.JButton();
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
        btn_print_division = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        F_division_search = new javax.swing.JTextField();
        btn_search_division = new javax.swing.JButton();
        panel_salary = new javax.swing.JPanel();
        BreadCrumb4 = new javax.swing.JPanel();
        INVENTORY3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btn_save_payment = new javax.swing.JButton();
        btn_edit_payment = new javax.swing.JButton();
        btn_delete_payment = new javax.swing.JButton();
        btn_clear_payment = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        table_payment = new javax.swing.JTable();
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
        F_payment_working_day = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        F_payment_attendance = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel59 = new javax.swing.JLabel();
        F_payment_net_total = new javax.swing.JTextField();
        btn_payment_calculate_income = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        F_payment_absent = new javax.swing.JTextField();
        F_payment_id = new javax.swing.JTextField();
        panel_apprasial = new javax.swing.JPanel();
        BreadCrumb5 = new javax.swing.JPanel();
        INVENTORY4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        F_payment_period1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        panel_division_member = new javax.swing.JPanel();
        BreadCrumb6 = new javax.swing.JPanel();
        INVENTORY5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btn_print_member = new javax.swing.JButton();
        btn_clear_member = new javax.swing.JButton();
        btn_delete_member = new javax.swing.JButton();
        btn_save_member = new javax.swing.JButton();
        btn_edit_member = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        F_member_id = new javax.swing.JTextField();
        F_member_level = new javax.swing.JComboBox<>();
        F_member_division = new javax.swing.JComboBox<>();
        F_member_employee = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        table_member = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        F_member_search = new javax.swing.JTextField();
        btn_search_asset2 = new javax.swing.JButton();
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
        D_btn_member = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        gradient_footer1 = new Layout.gradient_footer();
        jLabel12 = new javax.swing.JLabel();

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

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

        btn_menu_division_member.setBackground(new java.awt.Color(0, 116, 225));
        btn_menu_division_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_menu_division_member.setForeground(new java.awt.Color(255, 255, 255));
        btn_menu_division_member.setText("DIVISION MEMBER");
        btn_menu_division_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menu_division_memberActionPerformed(evt);
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
                    .addComponent(btn_menu_apprasial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_menu_division_member, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
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
                .addComponent(btn_menu_division_member, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_menu_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
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
                .addGap(506, 506, 506)
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

        tmaritalStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", " " }));

        jLabel39.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel39.setText("Religion");

        treligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Moslem", "Christian", "Buddha", "Hindu" }));

        jLabel40.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel40.setText("NIK");

        tnik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnikActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel10.setText("Address");

        talamat.setColumns(20);
        talamat.setRows(5);
        jScrollPane3.setViewportView(talamat);

        jLabel41.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel41.setText("NPWP");

        tnpwp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnpwpActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel42.setText("BPJS TK");

        tbpjstk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbpjstkActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel43.setText("BPJS KS");

        jLabel44.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel44.setText("NO. Rek");

        tnorek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnorekActionPerformed(evt);
            }
        });

        btn_print_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_employee.setText("PRINT");
        btn_print_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_employeeActionPerformed(evt);
            }
        });

        btn_search_employee.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_employee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/search.png"))); // NOI18N
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

        jLabel17.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel17.setText("Search");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbpjstk)
                            .addComponent(tnpwp)
                            .addComponent(treligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tmaritalStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tnik)
                            .addComponent(tphone)
                            .addComponent(temail)
                            .addComponent(tnama)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rjk1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(rjk2))
                            .addComponent(tid, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tnorek, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(tbpjsks, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addGap(4, 4, 4))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_print_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tcari1)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tcari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(btn_search_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
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
                            .addComponent(tnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(tmaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(treligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(tnpwp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(tbpjstk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(tbpjsks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(tnorek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_clear_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_save_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_print_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(F_employee_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1446, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_employeeLayout.setVerticalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
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
                .addContainerGap(774, Short.MAX_VALUE))
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

        tstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Not Available", " " }));

        btn_print_inventory.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_inventory.setText("PRINT");
        btn_print_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_inventoryActionPerformed(evt);
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
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tmodel, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                    .addComponent(ttag)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_save_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_print_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_delete_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_clear_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tqty, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tkat)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                                .addGap(26, 26, 26))))))
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
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_print_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
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
        btn_search_inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/search.png"))); // NOI18N
        btn_search_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_inventoryActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel8.setText("Search");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tcari_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BreadCrumb2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(INVENTORY1)
                .addGap(629, 629, 629))
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

        btn_print_asset.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_asset.setText("PRINT");
        btn_print_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_assetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(F_asset_received_datetime, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btn_save_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btn_print_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btn_delete_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_clear_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
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
                        .addComponent(F_asset_received_datetime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_second, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(F_asset_received_hours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_print_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        btn_search_asset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/search.png"))); // NOI18N
        btn_search_asset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_assetActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel23.setText("Search");

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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
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
                .addContainerGap(788, Short.MAX_VALUE))
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

        btn_print_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_division.setText("PRINT");
        btn_print_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_divisionActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel30.setText("Search");

        F_division_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_division_searchActionPerformed(evt);
            }
        });

        btn_search_division.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_division.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/search.png"))); // NOI18N
        btn_search_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_divisionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CB_inCharge, 0, 220, Short.MAX_VALUE)
                            .addComponent(F_division))
                        .addGap(18, 18, 18)
                        .addComponent(F_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btn_save_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_edit_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_print_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_delete_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_clear_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F_division_search, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_search_division, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(F_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F_division_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(CB_inCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_clear_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_delete_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_print_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_save_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(F_division_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_search_division, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_divisionLayout = new javax.swing.GroupLayout(panel_division);
        panel_division.setLayout(panel_divisionLayout);
        panel_divisionLayout.setHorizontalGroup(
            panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_divisionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_divisionLayout.setVerticalGroup(
            panel_divisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_divisionLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumb4Layout.setVerticalGroup(
            BreadCrumb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb4Layout.createSequentialGroup()
                .addComponent(INVENTORY3)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        btn_save_payment.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_payment.setText("SAVE");
        btn_save_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_paymentActionPerformed(evt);
            }
        });

        btn_edit_payment.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_payment.setText("EDIT");
        btn_edit_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_paymentActionPerformed(evt);
            }
        });

        btn_delete_payment.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_payment.setText("DELETE");
        btn_delete_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_paymentActionPerformed(evt);
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

        table_payment.setModel(new javax.swing.table.DefaultTableModel(
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
        table_payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_paymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                table_paymentMouseEntered(evt);
            }
        });
        jScrollPane10.setViewportView(table_payment);

        jLabel20.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel20.setText("Basic Salary");

        F_payment_basicSalary.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                F_payment_basicSalaryInputMethodTextChanged(evt);
            }
        });
        F_payment_basicSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                F_payment_basicSalaryKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                F_payment_basicSalaryKeyReleased(evt);
            }
        });

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
        jLabel57.setText("Working Day");

        jLabel58.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel58.setText("Attendance");

        jLabel59.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel59.setText("NET TOTAL");

        btn_payment_calculate_income.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_payment_calculate_income.setText("Calculate");
        btn_payment_calculate_income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_payment_calculate_incomeActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel64.setText("Absent");

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
                            .addComponent(jLabel57)
                            .addComponent(jLabel64))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(F_payment_absent, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_period, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_division, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(F_payment_working_day, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel22)
                                                    .addComponent(jLabel46)
                                                    .addComponent(jLabel49))
                                                .addGap(86, 86, 86)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(F_payment_other, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_overtime, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_basicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_eid_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                    .addComponent(jLabel56)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(F_payment_total_income, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel53)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel51)
                                                    .addComponent(jLabel50))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(F_payment_bpjstkc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_bpjsksc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel55)
                                                            .addComponent(jLabel58))
                                                        .addGap(23, 23, 23))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)))
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(F_payment_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_bpjskse, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(F_payment_bpjstke, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel59)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(F_payment_net_total, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addComponent(btn_payment_calculate_income))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(F_payment_id, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_save_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_clear_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(15, 15, 15))
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
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(F_payment_working_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(F_payment_absent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(F_payment_bpjstke, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(F_payment_bpjskse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(F_payment_attendance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(F_payment_net_total, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_payment_calculate_income)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(F_payment_basicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(F_payment_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(F_payment_overtime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(F_payment_other, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(F_payment_eid_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(F_payment_bpjstkc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(F_payment_bpjsksc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(F_payment_total_income, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))))
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F_payment_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
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
                .addContainerGap(766, Short.MAX_VALUE))
        );
        BreadCrumb5Layout.setVerticalGroup(
            BreadCrumb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb5Layout.createSequentialGroup()
                .addComponent(INVENTORY4)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel61.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel61.setText("Period");

        jLabel11.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel11.setText("Employee");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel62.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel62.setText("Division");

        jLabel63.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel63.setText("Division");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel61)
                                    .addGap(42, 42, 42))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(F_payment_period1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel62)
                            .addGap(29, 29, 29)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63)
                        .addGap(29, 29, 29)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(F_payment_period1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(511, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_apprasialLayout = new javax.swing.GroupLayout(panel_apprasial);
        panel_apprasial.setLayout(panel_apprasialLayout);
        panel_apprasialLayout.setHorizontalGroup(
            panel_apprasialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_apprasialLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_apprasialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_apprasialLayout.setVerticalGroup(
            panel_apprasialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_apprasialLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        content.addTab("tab6", panel_apprasial);

        BreadCrumb6.setBackground(new java.awt.Color(255, 255, 255));

        INVENTORY5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        INVENTORY5.setText("DIVISION MEMBER");

        javax.swing.GroupLayout BreadCrumb6Layout = new javax.swing.GroupLayout(BreadCrumb6);
        BreadCrumb6.setLayout(BreadCrumb6Layout);
        BreadCrumb6Layout.setHorizontalGroup(
            BreadCrumb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb6Layout.createSequentialGroup()
                .addGap(495, 495, 495)
                .addComponent(INVENTORY5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BreadCrumb6Layout.setVerticalGroup(
            BreadCrumb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BreadCrumb6Layout.createSequentialGroup()
                .addComponent(INVENTORY5)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        btn_print_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_print_member.setText("PRINT");
        btn_print_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_memberActionPerformed(evt);
            }
        });

        btn_clear_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_clear_member.setText("CLEAR");
        btn_clear_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_memberActionPerformed(evt);
            }
        });

        btn_delete_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_delete_member.setText("DELETE");
        btn_delete_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_memberActionPerformed(evt);
            }
        });

        btn_save_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_save_member.setText("SAVE");
        btn_save_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_memberActionPerformed(evt);
            }
        });

        btn_edit_member.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_edit_member.setText("EDIT");
        btn_edit_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_memberActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel2.setText("Employee");

        jLabel26.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel26.setText("Division");

        jLabel27.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel27.setText("Level");

        F_member_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_member_idActionPerformed(evt);
            }
        });

        table_member.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        table_member.setModel(new javax.swing.table.DefaultTableModel(
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
        table_member.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_memberMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(table_member);

        jLabel48.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel48.setText("Search");

        F_member_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F_member_searchActionPerformed(evt);
            }
        });

        btn_search_asset2.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        btn_search_asset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/search.png"))); // NOI18N
        btn_search_asset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_asset2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1426, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btn_save_member, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_edit_member, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_print_member, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_delete_member, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_clear_member, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(29, 29, 29)
                        .addComponent(F_member_division, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(F_member_id, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(50, 50, 50)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(F_member_employee, 0, 250, Short.MAX_VALUE)
                            .addComponent(F_member_level, 0, 250, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(F_member_search, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_search_asset2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(F_member_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(F_member_division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(F_member_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(F_member_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save_member, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_member, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_print_member, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete_member, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear_member, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search_asset2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(F_member_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_division_memberLayout = new javax.swing.GroupLayout(panel_division_member);
        panel_division_member.setLayout(panel_division_memberLayout);
        panel_division_memberLayout.setHorizontalGroup(
            panel_division_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_division_memberLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_division_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BreadCrumb6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panel_division_memberLayout.setVerticalGroup(
            panel_division_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_division_memberLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BreadCrumb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        content.addTab("tab7", panel_division_member);

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

        D_btn_division.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/division1.png"))); // NOI18N
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

        D_btn_member.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Asset/division2.png"))); // NOI18N
        D_btn_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_btn_memberActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Sitka Text", 1, 13)); // NOI18N
        jLabel60.setText("Division Member");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(D_btn_member, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(D_btn_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel35))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel32)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(D_btn_division, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(D_btn_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel60)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(D_btn_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(D_btn_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(D_btn_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(301, 836, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(D_btn_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D_btn_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D_btn_apprasial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addComponent(D_btn_division, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(D_btn_asset, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D_btn_member, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D_btn_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(jLabel36))
                .addContainerGap(130, Short.MAX_VALUE))
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
        String status = tstatus.getSelectedItem().toString();
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
        String status = tstatus.getSelectedItem().toString();
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
        tstatus.setSelectedItem(status);
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
        String nik = tnik.getText();
        String maritalStatus = tmaritalStatus.getSelectedItem().toString();
        String religion = treligion.getSelectedItem().toString();
        String npwp = tnpwp.getText();
        String bpjstk = tbpjstk.getText();
        String bpjsks = tbpjsks.getText();
        String norek = tnorek.getText();
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
                                "nik,\n" +
                                "marital_status,\n" +
                                "religion,\n" +
                                "npwp,\n" +
                                "bpjs_tk,\n" +
                                "bpjs_ks,\n" +
                                "no_rek,\n" +
                                "created_at\n" +
                            ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareStatement(query);
                stat.setString(1, employee_id);
                stat.setString(2, name);
                stat.setString(3, email);
                stat.setString(4, password);
                stat.setString(5, gender);
                stat.setString(6, address);
                stat.setString(7, phone);
                stat.setString(8, nik);
                stat.setString(9, maritalStatus);
                stat.setString(10, religion);
                stat.setString(11, npwp);
                stat.setString(12, bpjstk);
                stat.setString(13, bpjsks);
                stat.setString(14, norek);
                stat.setString(15, datetime);

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
        String gender = "";
        if(rjk1.isSelected()){
            gender = "Laki-laki";
        }else{
            gender = "Perempuan";
        }
        String phone = tphone.getText();
        String nik = tnik.getText();
        String maritalStatus = tmaritalStatus.getSelectedItem().toString();
        String religion = treligion.getSelectedItem().toString();
        String npwp = tnpwp.getText();
        String bpjstk = tbpjstk.getText();
        String bpjsks = tbpjsks.getText();
        String norek = tnorek.getText();
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
                                "nik =?,\n" +
                                "marital_status =?,\n" +
                                "religion =?,\n" +
                                "npwp =?,\n" +
                                "bpjs_tk =?,\n" +
                                "bpjs_ks =?,\n" +
                                "no_rek =?,\n" +
                                "updated_at =?\n" +
                            "WHERE id = ?";
                PreparedStatement stat = conn.prepareStatement (sql);
                stat.setString(1, employee_id);
                stat.setString(2, name);
                stat.setString(3, email);
                stat.setString(4, gender);
                stat.setString(5, address);
                stat.setString(6, phone);
                stat.setString(7, nik);
                stat.setString(8, maritalStatus);
                stat.setString(9, religion);
                stat.setString(10, npwp);
                stat.setString(11, bpjstk);
                stat.setString(12, bpjsks);
                stat.setString(13, norek);
                stat.setString(14, datetime);
                stat.setString(15, id);

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
        Object [] Baris = {"No","Employee ID","Name","Gender","Email","Phone","NIK","Address","Created Date"};
        tabmode = new DefaultTableModel(null, Baris);
        tableEmployee.setModel(tabmode);
        String sql = "SELECT * FROM t_employee "
                + "WHERE employee_id LIKE '%"+ tcari1.getText() +"%' "
                + "OR name LIKE '%"+ tcari1.getText() +"%' "
                + "OR gender LIKE '%"+ tcari1.getText() +"%' "
                + "OR email LIKE '%"+tcari1.getText()+"%' "
                + "OR address LIKE '%"+tcari1.getText()+"%' "
                + "OR nik LIKE '%"+tcari1.getText()+"%' "
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
                    String g = hasil.getString ("nik");
                    String h = hasil.getString ("address");
                    Date datetime = hasil.getDate("created_at");
                    String formaterDate = "dd/MM/yyyy";
                    DateFormat df = new SimpleDateFormat(formaterDate);
                    String i = df.format(datetime);

                    String [] data= {a,b,c,d,e,f,g,h,i};
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
        try{
            db = conn.createStatement();
            String query = "SELECT * FROM t_employee WHERE id="+id_employee;

            Result = db.executeQuery(query);
            
            while(Result.next()){
                F_employee_id.setText(Result.getString("id"));
                tid.setText(Result.getString("employee_id"));
                tnama.setText(Result.getString("employee_id"));
                talamat.setText(Result.getString("address"));
                if(Result.getString("gender").toLowerCase().equals("laki-laki")) {rjk1.setSelected(true);rjk2.setSelected(false);}
                else {rjk1.setSelected(false); rjk2.setSelected(true);}
                tphone.setText(Result.getString("phone"));
                temail.setText(Result.getString("email"));
                tnik.setText(Result.getString("nik"));
                tmaritalStatus.setSelectedItem(Result.getString("marital_status"));
                treligion.setSelectedItem(Result.getString("religion"));
                tnpwp.setText(Result.getString("npwp"));
                tbpjstk.setText(Result.getString("bpjs_tk"));
                tbpjsks.setText(Result.getString("bpjs_ks"));
                tnorek.setText(Result.getString("no_rek"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select Table Employee Error");
            System.out.println(e);
        }
        
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
//        LocalDateTime received_datetime = dateTimePicker1.getDateTimeStrict();
//        
//        System.out.println(received_datetime);
        DateFormat sysDate = new SimpleDateFormat("yyyy/MM/dd");
        String val_received_date = sysDate.format(F_asset_received_datetime.getDate()).toString();
        
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
        String val_received_date = sysDate.format(F_asset_received_datetime.getDate());

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
            F_asset_received_datetime.setDate(date);
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

    private void table_memberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_memberMouseClicked
    
        btn_save_member.setEnabled(false);
        btn_edit_member.setEnabled(true);
        btn_delete_member.setEnabled(true);
        
        //{"No","Employee","Division","In Charge","Created Date"};
        int bar = table_member.getSelectedRow();
        String user_level_id = tabmode2.getValueAt (bar, 0).toString();
        String employee = tabmode2.getValueAt (bar, 1).toString();
        String division = tabmode2.getValueAt (bar, 2).toString();
        String level = tabmode2.getValueAt (bar, 3).toString();
        String inCharge = tabmode2.getValueAt (bar, 4).toString();
        String created_date = tabmode2.getValueAt (bar, 5).toString();
        
        System.out.print(user_level_id);
        
        F_member_employee.setSelectedItem(employee);
        F_member_division.setSelectedItem(division);
        F_member_level.setSelectedItem(level);
        F_member_id.setText(user_level_id);
        
    }//GEN-LAST:event_table_memberMouseClicked

    private void CB_inChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_inChargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_inChargeActionPerformed

    private void table_divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_divisionMouseClicked
        
        btn_save_division.setEnabled(false);
        btn_edit_division.setEnabled(true);
        btn_delete_division.setEnabled(true);
        
        //{"No","Division","In Charge","Created Date"};
        int idx = table_division.getSelectedRow();
        String division_id = table_division.getValueAt(idx, 0).toString();
        String division_name = table_division.getValueAt(idx, 1).toString();
        String boss_id = table_division.getValueAt(idx, 2).toString();
        
        F_division_id.setText(division_id);
        F_division.setText(division_name);
        CB_inCharge.setSelectedItem(boss_id);
        
        // list user division
//        String sql = "SELECT a.id, b.employee_id, b.name,c.name AS division, a.id_level, d.name AS boss_name, a.created_at \n" +
//                    "FROM t_user_level a \n" +
//                    "LEFT JOIN t_employee b ON a.id_user = b.id \n" +
//                    "LEFT JOIN t_division c ON a.id_division = c.id \n" +
//                    "LEFT JOIN t_employee d ON c.boss_id = d.id \n" +
//                    "WHERE c.id = "+division_id;
//        try{
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//            String e = "";
//            List<UserDivisi> value = new ArrayList<UserDivisi>();
//            
//            if(hasil.first()){
//                
//                do {
//                    Date datetime = hasil.getDate("created_at");
//                    String formaterDate = "dd/MM/yyyy";
//                    DateFormat df = new SimpleDateFormat(formaterDate);
//                    String g = df.format(datetime);
//                    
//                    UserDivisi item = new UserDivisi();
//                    item.setId(hasil.getString("id"));
//                    item.setEmployeeId(hasil.getString("employee_id"));
//                    item.setName(hasil.getString("name"));
//                    item.setDivision(hasil.getString("division"));
//                    item.setIdLevel(hasil.getInt("id_level") == 0 ? "administrator" : "user");
//                    item.setBossManId(hasil.getString("boss_name"));
//                    item.setCreatedAt(g);
//                    value.add(item);
//                }
//                while (hasil.next()); 
//                
//                dt_list_user_division(value);
//                    
//            }else{
//                dt_list_user_division(value);
//            }
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Table Error");
//            System.out.println(e);
//        }
        
    }//GEN-LAST:event_table_divisionMouseClicked

    private void jScrollPane7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane7MouseClicked

    private void table_divisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_divisionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_divisionMouseEntered

    private void btn_save_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_memberActionPerformed
        
        String val_employee = F_member_employee.getSelectedItem().toString();
        String val_division = F_member_division.getSelectedItem().toString();
        String level = F_member_level.getSelectedItem().toString();
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
                
                JOptionPane.showMessageDialog(null, "Create Division Member Success");
                clear_form_member();
                dt_member();
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Create Division Member Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_save_memberActionPerformed

    private void btn_edit_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_memberActionPerformed
        
        String id_user_level = F_member_id.getText();
        String val_employee = F_member_employee.getSelectedItem().toString();
        String val_division = F_member_division.getSelectedItem().toString();
        String level = F_member_level.getSelectedItem().toString();
        String datetime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String employee_id = "";
        String division_id = "";
        Integer val_level;
        
        String regex = "-";
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
            
            // UPDATE t_user_level
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
                clear_form_member();
                dt_member();
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Edit User Division Failed!");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_edit_memberActionPerformed

    private void btn_delete_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_memberActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            
            String sql = "DELETE FROM t_user_level WHERE id ='"+F_member_id.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete Division Member Success");
                clear_form_member();
                F_member_division.requestFocus();
                dt_member();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Delete Division Member Failed");
                System.out.println(e);
            }
            
        }
        
    }//GEN-LAST:event_btn_delete_memberActionPerformed

    private void btn_clear_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_memberActionPerformed
        clear_form_member();
        dt_member();
        
    }//GEN-LAST:event_btn_clear_memberActionPerformed

    private void F_asset_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_asset_employeeActionPerformed
        
    }//GEN-LAST:event_F_asset_employeeActionPerformed

    private void F_asset_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_asset_inventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_asset_inventoryActionPerformed

    private void F_member_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_member_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_member_idActionPerformed

    private void btn_save_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_paymentActionPerformed
        if(F_payment_period.getDate() == null){
            JOptionPane.showMessageDialog(null,"Period required !");
        }else{
            DateFormat sysDate = new SimpleDateFormat("yyyy/MM/dd");
            String val_payment_period = sysDate.format(F_payment_period.getDate());
            String val_employee = F_payment_employee.getSelectedItem().toString();
            String regex = "-";
            String val_employee_id = val_employee.substring(0, val_employee.indexOf(regex));
            String val_division = F_payment_division.getText();
            Integer working_day = ((F_payment_working_day.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_working_day.getText())));
            Integer absent = ((F_payment_absent.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_absent.getText())));
            Integer salary = ((F_payment_basicSalary.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_basicSalary.getText())));
            Integer allowance = ((F_payment_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_allowance.getText())));
            Integer overtime = ((F_payment_overtime.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_overtime.getText())));
            Integer others = ((F_payment_other.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_other.getText())));
            Integer eid_allowance = ((F_payment_eid_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_eid_allowance.getText())));
            Integer bpjs_tk_c = ((F_payment_bpjstkc.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjstkc.getText())));
            Integer bpjs_ks_c = ((F_payment_bpjsksc.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjsksc.getText())));
            Integer total_income = ((F_payment_total_income.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_total_income.getText())));
            Integer bpjs_tk_e = ((F_payment_bpjstke.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjstke.getText())));
            Integer bpjs_ks_e = ((F_payment_bpjskse.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjskse.getText())));
            Integer attendance = ((F_payment_attendance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_attendance.getText())));
            Integer net_total = ((F_payment_net_total.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_net_total.getText())));
            String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            String employee_id = "";
            String division_id = "";
            String employee_start_date = "";

            //VALIDATION
            if ( "--- choose ---".equals(val_employee) || val_division.trim().length() == 0 || working_day == 0 || net_total == 0 || total_income == 0){
                JOptionPane.showMessageDialog(null,"Please Click Button Calculate again !");
            } else{

                // Get Id for Employee & Division
                String query_get_employee_id = "SELECT a.*,c.id AS division_id \n" +
                                                "FROM t_employee a \n" +
                                                "JOIN t_user_level b ON a.id = b.id_user \n" +
                                                "JOIN t_division c ON b.id_division = c.id \n" +
                                                "WHERE employee_id= '"+val_employee_id+"' LIMIT 1";
                try {
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(query_get_employee_id);
                    while (hasil.next()){
                        employee_id = hasil.getString("id");
                        division_id = hasil.getString("division_id");
                        employee_start_date = hasil.getString("created_at");
                    }

                }catch (SQLException ex) {
                    System.out.println(ex);
                }

                //Insert t_payment
                String query = "INSERT INTO t_payment (\n" +
                                "period,\n" +
                                "employee_id,\n" +
                                "division_id,\n" +
                                "start_date,\n" +
                                "working_day,\n" +
                                "absent,\n" +
                                "basic_salary,\n" +
                                "allowance,\n" +
                                "overtime,\n" +
                                "others,\n" +
                                "eid_allowance,\n" +
                                "bpjs_tk_c,\n" +
                                "bpjs_ks_c,\n" +
                                "total_income,\n" +
                                "bpjs_tk_e,\n" +
                                "bpjs_ks_e,\n" +
                                "atendance,\n" +
                                "net_total,\n" +
                                "created_at\n" +
                                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement stat = conn.prepareStatement(query);
                    stat.setString(1, val_payment_period);
                    stat.setString(2, employee_id);
                    stat.setString(3, division_id);
                    stat.setString(4, employee_start_date);
                    stat.setString(5, String.valueOf(working_day));
                    stat.setString(6, String.valueOf(absent));
                    stat.setString(7, String.valueOf(salary));
                    stat.setString(8, String.valueOf(allowance));
                    stat.setString(9, String.valueOf(overtime));
                    stat.setString(10, String.valueOf(others));
                    stat.setString(11, String.valueOf(eid_allowance));
                    stat.setString(12, String.valueOf(bpjs_tk_c));
                    stat.setString(13, String.valueOf(bpjs_ks_c));
                    stat.setString(14, String.valueOf(total_income));
                    stat.setString(15, String.valueOf(bpjs_tk_e));
                    stat.setString(16, String.valueOf(bpjs_ks_e));
                    stat.setString(17, String.valueOf(attendance));
                    stat.setString(18, String.valueOf(net_total));
                    stat.setString(19, datetime);

                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Create Payment Success");
                    clear_form_payment();
                    F_payment_employee.requestFocus();
                    dt_payment();
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Create Payment Failed!");
                    System.out.println(e);
                }
            }
        }
        
    }//GEN-LAST:event_btn_save_paymentActionPerformed

    private void btn_edit_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_paymentActionPerformed
        
        if(F_payment_period.getDate() == null){
            JOptionPane.showMessageDialog(null,"Period required !");
        }else{
            String id = F_payment_id.getText();
            DateFormat sysDate = new SimpleDateFormat("yyyy/MM/dd");
            String val_payment_period = sysDate.format(F_payment_period.getDate());
            String val_employee = F_payment_employee.getSelectedItem().toString();
            String regex = "-";
            String val_employee_id = val_employee.substring(0, val_employee.indexOf(regex));
            String val_division = F_payment_division.getText();
            Integer working_day = ((F_payment_working_day.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_working_day.getText())));
            Integer absent = ((F_payment_absent.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_absent.getText())));
            Integer salary = ((F_payment_basicSalary.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_basicSalary.getText())));
            Integer allowance = ((F_payment_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_allowance.getText())));
            Integer overtime = ((F_payment_overtime.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_overtime.getText())));
            Integer others = ((F_payment_other.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_other.getText())));
            Integer eid_allowance = ((F_payment_eid_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_eid_allowance.getText())));
            Integer bpjs_tk_c = ((F_payment_bpjstkc.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjstkc.getText())));
            Integer bpjs_ks_c = ((F_payment_bpjsksc.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjsksc.getText())));
            Integer total_income = ((F_payment_total_income.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_total_income.getText())));
            Integer bpjs_tk_e = ((F_payment_bpjstke.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjstke.getText())));
            Integer bpjs_ks_e = ((F_payment_bpjskse.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_bpjskse.getText())));
            Integer attendance = ((F_payment_attendance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_attendance.getText())));
            Integer net_total = ((F_payment_net_total.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_net_total.getText())));
            String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

            String employee_id = "";
            String division_id = "";
            String employee_start_date = "";

            //VALIDATION
            if ( "--- choose ---".equals(val_employee) || val_division.trim().length() == 0 || working_day == 0 || net_total == 0 || total_income == 0){
                JOptionPane.showMessageDialog(null,"Please Click Button Calculate again !");
            } else{

                // Get Id for Employee & Division
                String query_get_employee_id = "SELECT a.*,c.id AS division_id \n" +
                                                "FROM t_employee a \n" +
                                                "JOIN t_user_level b ON a.id = b.id_user \n" +
                                                "JOIN t_division c ON b.id_division = c.id \n" +
                                                "WHERE employee_id= '"+val_employee_id+"' LIMIT 1";
                try {
                    java.sql.Statement stat = conn.createStatement();
                    ResultSet hasil = stat.executeQuery(query_get_employee_id);
                    while (hasil.next()){
                        employee_id = hasil.getString("id");
                        division_id = hasil.getString("division_id");
                        employee_start_date = hasil.getString("created_at");
                    }

                }catch (SQLException ex) {
                    System.out.println(ex);
                }

                //Insert t_payment
                String query = "UPDATE t_payment SET \n" +
                                "period=?,\n" +
                                "employee_id=?,\n" +
                                "division_id=?,\n" +
                                "start_date=?,\n" +
                                "working_day=?,\n" +
                                "absent=?,\n" +
                                "basic_salary=?,\n" +
                                "allowance=?,\n" +
                                "overtime=?,\n" +
                                "others=?,\n" +
                                "bpjs_tk_c=?,\n" +
                                "bpjs_ks_c=?,\n" +
                                "eid_allowance=?,\n" +
                                "total_income=?,\n" +
                                "bpjs_tk_e=?,\n" +
                                "bpjs_ks_e=?,\n" +
                                "atendance=?,\n" +
                                "net_total=?,\n" +
                                "updated_at=?\n" +
                                "WHERE id=?";
                try {
                    PreparedStatement stat = conn.prepareStatement(query);
                    stat.setString(1, val_payment_period);
                    stat.setString(2, employee_id);
                    stat.setString(3, division_id);
                    stat.setString(4, employee_start_date);
                    stat.setString(5, String.valueOf(working_day));
                    stat.setString(6, String.valueOf(absent));
                    stat.setString(7, String.valueOf(salary));
                    stat.setString(8, String.valueOf(allowance));
                    stat.setString(9, String.valueOf(overtime));
                    stat.setString(10, String.valueOf(others));
                    stat.setString(11, String.valueOf(eid_allowance));
                    stat.setString(12, String.valueOf(bpjs_tk_c));
                    stat.setString(13, String.valueOf(bpjs_ks_c));
                    stat.setString(14, String.valueOf(total_income));
                    stat.setString(15, String.valueOf(bpjs_tk_e));
                    stat.setString(16, String.valueOf(bpjs_ks_e));
                    stat.setString(17, String.valueOf(attendance));
                    stat.setString(18, String.valueOf(net_total));
                    stat.setString(19, datetime);
                    stat.setString(20, id);

                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Edit Payment Success");
                    clear_form_payment();
                    F_payment_employee.requestFocus();
                    dt_payment();
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Edit Payment Failed!");
                    System.out.println(e);
                }
            }
        }
          
          
    }//GEN-LAST:event_btn_edit_paymentActionPerformed

    private void btn_delete_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_paymentActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Are you sure want delete?", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok==0){
            
            String sql = "DELETE FROM t_payment WHERE id ='"+F_payment_id.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Delete Payment Success");
                clear_form_payment();
                F_payment_employee.requestFocus();
                dt_payment();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Delete Payment Failed");
                System.out.println(e);
            }
            
        }
    }//GEN-LAST:event_btn_delete_paymentActionPerformed

    private void btn_clear_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_paymentActionPerformed
        clear_form_payment();
    }//GEN-LAST:event_btn_clear_paymentActionPerformed

    private void table_paymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_paymentMouseClicked
        btn_save_payment.setEnabled(false);
        btn_edit_payment.setEnabled(true);
        btn_delete_payment.setEnabled(true);
        
        //{"No","Period","Employee","Division","Net Total","Created Date"};
        int idx = table_payment.getSelectedRow();
        String id = table_payment.getValueAt(idx, 0).toString();
        
        String query = "SELECT a.*, CONCAT_WS(\" - \",b.employee_id,b.name) AS employee, c.name AS division\n" +
                        "FROM t_payment a\n" +
                        "JOIN t_employee b ON a.employee_id = b.id\n" +
                        "JOIN t_division c ON a.division_id = c.id\n" +
                        "WHERE a.id = "+ id;
        
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(query);
            while (hasil.next()){
                F_payment_id.setText(id);
                F_payment_period.setDate(hasil.getDate("period"));
                F_payment_employee.setSelectedItem(hasil.getString("employee"));
                F_payment_division.setText(hasil.getString("division"));
                F_payment_working_day.setText(String.valueOf(hasil.getString("working_day")));
                F_payment_absent.setText(String.valueOf(hasil.getString("absent")));
                F_payment_basicSalary.setText(String.valueOf(hasil.getString("basic_salary")));
                F_payment_allowance.setText(String.valueOf(hasil.getString("allowance")));
                F_payment_overtime.setText(String.valueOf(hasil.getString("overtime")));
                F_payment_other.setText(String.valueOf(hasil.getString("others")));
                F_payment_bpjstkc.setText(String.valueOf(hasil.getString("bpjs_tk_c")));
                F_payment_bpjsksc.setText(String.valueOf(hasil.getString("bpjs_ks_c")));
                F_payment_eid_allowance.setText(String.valueOf(hasil.getString("eid_allowance")));
                F_payment_total_income.setText(String.valueOf(hasil.getString("total_income")));
                F_payment_bpjstke.setText(String.valueOf(hasil.getString("bpjs_tk_e")));
                F_payment_bpjskse.setText(String.valueOf(hasil.getString("bpjs_ks_e")));
                F_payment_attendance.setText(String.valueOf(hasil.getString("atendance")));
                F_payment_net_total.setText(String.valueOf(hasil.getString("net_total")));
            }

        }catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }//GEN-LAST:event_table_paymentMouseClicked

    private void table_paymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_paymentMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_table_paymentMouseEntered

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

    private void tnikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnikActionPerformed

    private void tnpwpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnpwpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnpwpActionPerformed

    private void tbpjstkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbpjstkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpjstkActionPerformed

    private void tnorekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnorekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnorekActionPerformed

    private void F_payment_employeeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_F_payment_employeeItemStateChanged
       if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
          Object item = evt.getItem();
          
          if(item != "--- choose ---"){
              
            // System.out.println(inCharge.indexOf(regex));
            String val_item = item.toString();
            String regex = "-";
            String str_employee = val_item.substring(0, val_item.indexOf(regex));
            String employee_id = "";
            String division = "";
            
            // Get Id for Employee Id
            String query_get_employee_id = "SELECT c.id AS employee_id, b.name AS division FROM t_user_level a\n" +
                                            "LEFT JOIN t_division b ON a.id_division = b.id\n" +
                                            "LEFT JOIN t_employee c ON a.id_user = c.id\n" +
                                            "WHERE c.employee_id='"+ str_employee +"' LIMIT 1";

            try {
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(query_get_employee_id);
                while (hasil.next()){
                    employee_id = hasil.getString("employee_id");
                    division = hasil.getString("division");
                }
            }catch (SQLException ex) {
                System.out.println(ex);
            }
            
            Object[]Baris = {"No","Period","Employee","Division","Net Total","Created Date"};
            tabmode3 = new DefaultTableModel(null, Baris);
            table_payment.setModel(tabmode3);
            
            String sql = "SELECT a.*,CONCAT_WS(\" - \",b.employee_id,b.name) AS employee, c.name AS division FROM t_payment a\n" +
                        "LEFT JOIN t_employee b ON a.employee_id = b.id\n" +
                        "LEFT JOIN t_employee c ON a.division_id = c.id\n" +
                        "WHERE b.employee_id = '" + str_employee + "'\n" +
                        "ORDER BY a.created_at DESC";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while (hasil.next()){
                    String a = hasil.getString("id");
                    Date period = hasil.getDate("period");
                    DateFormat dtf = new SimpleDateFormat("MM/yyyy");
                    String b = dtf.format(period);
                    String c = hasil.getString("employee");
                    String d = hasil.getString("division");
                    String e = hasil.getString("net_total");
                    Date datetime = hasil.getDate("created_at");
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String f = df.format(datetime);

                    String[] data={a,b,c,d,e,f};
                    tabmode3.addRow(data);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Table Error");
                System.out.println(e);
            }
            
            F_payment_division.setText(division);
            open_form_payment();
              
          }else{
              close_form_payment();
              dt_payment();
          }
       }
        
    }//GEN-LAST:event_F_payment_employeeItemStateChanged

    private void btn_print_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_employeeActionPerformed
        try {
            String filename = "src/Report/reportEmployee.jasper";
            HashMap parameter = new HashMap();
            File FileReport = new File(filename);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FileReport.getPath());
            JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print Error");
            System.out.print(e);
        }
    }//GEN-LAST:event_btn_print_employeeActionPerformed

    private void btn_print_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_inventoryActionPerformed
         try {
            String filename = "src/Report/reportInventory.jasper";
            HashMap parameter = new HashMap();
            File FileReport = new File(filename);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FileReport.getPath());
            JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print Error");
            System.out.print(e);
        }
    }//GEN-LAST:event_btn_print_inventoryActionPerformed

    private void btn_print_assetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_assetActionPerformed
         try {
            String filename = "src/Report/reportAsset.jasper";
            HashMap parameter = new HashMap();
            File FileReport = new File(filename);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FileReport.getPath());
            JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print Error");
            System.out.print(e);
        }
    }//GEN-LAST:event_btn_print_assetActionPerformed

    private void btn_print_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_divisionActionPerformed
         try {
             String filename = "src/Report/reportHeadDiv.jasper";
            HashMap parameter = new HashMap();
            File FileReport = new File(filename);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FileReport.getPath());
            JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print Error");
            System.out.print(e);
        }
    }//GEN-LAST:event_btn_print_divisionActionPerformed

    private void btn_print_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_memberActionPerformed
         try {
            String filename = "src/Report/reportListDiv.jasper";
            HashMap parameter = new HashMap();
            File FileReport = new File(filename);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FileReport.getPath());
            JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,conn);
            JasperViewer.viewReport(jasperPrint,false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print Error");
            System.out.print(e);
        }
    }//GEN-LAST:event_btn_print_memberActionPerformed

    private void btn_menu_division_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menu_division_memberActionPerformed
        content.setSelectedIndex(6);
    }//GEN-LAST:event_btn_menu_division_memberActionPerformed

    private void F_division_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_division_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_division_searchActionPerformed

    private void btn_search_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_divisionActionPerformed
        Object[]Baris = {"No","Division","In Charge","Created Date"};
        tabmode = new DefaultTableModel(null, Baris);
        table_division.setModel(tabmode);

        String sql = "SELECT a.id, a.name AS division_name, CONCAT_WS(' - ',b.employee_id,b.`name`) AS boss_name, a.created_at\n" +
                    "FROM t_division a \n" +
                    "LEFT JOIN t_employee b ON a.boss_id = b.id \n" +
                    "WHERE a.name LIKE '%"+ F_division_search.getText() +"%' \n"+
                    "OR CONCAT_WS(' - ',b.employee_id,b.`name`) LIKE '%"+ F_division_search.getText() +"%' ";
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
    }//GEN-LAST:event_btn_search_divisionActionPerformed

    private void F_member_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F_member_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_F_member_searchActionPerformed

    private void btn_search_asset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_asset2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_search_asset2ActionPerformed

    private void D_btn_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_btn_memberActionPerformed
        content.setSelectedIndex(6);
    }//GEN-LAST:event_D_btn_memberActionPerformed

    private void F_payment_basicSalaryInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_F_payment_basicSalaryInputMethodTextChanged
       
    }//GEN-LAST:event_F_payment_basicSalaryInputMethodTextChanged

    private void F_payment_basicSalaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_F_payment_basicSalaryKeyPressed
       
    }//GEN-LAST:event_F_payment_basicSalaryKeyPressed

    private void btn_payment_calculate_incomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_payment_calculate_incomeActionPerformed
        
        try {
            if(F_payment_period.getDate() == null){
                JOptionPane.showMessageDialog(null,"Period required !");
            }
            Integer working_day = ((F_payment_working_day.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_working_day.getText())));
            Integer absent = ((F_payment_absent.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_absent.getText())));
            Integer salary = ((F_payment_basicSalary.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_basicSalary.getText())));
            Integer allowance = ((F_payment_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_allowance.getText())));
            Integer overtime = ((F_payment_overtime.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_overtime.getText())));
            Integer others = ((F_payment_other.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_other.getText())));
            Integer eid_allowance = ((F_payment_eid_allowance.getText().isEmpty() ? 0 : Integer.parseInt(F_payment_eid_allowance.getText())));
            if(working_day == 0){
                JOptionPane.showMessageDialog(null, "Working Day required !");
            }else if(salary == 0){
                JOptionPane.showMessageDialog(null, "Salary required !");
            }else{
                Integer bpjs_tk_c = salary*37/10/100;
                Integer bpjs_ks_c = salary*4/100;
                Integer total_income = salary+allowance+overtime+others+eid_allowance+bpjs_tk_c+bpjs_ks_c;

                Integer bpjs_tk_e = salary*2/100;
                Integer bpjs_ks_e = salary*1/100;
                Integer price_day = salary/working_day;
                Integer attendance = price_day*absent;

                Integer net_total = total_income-bpjs_tk_e-bpjs_ks_e-attendance;

                F_payment_bpjstkc.setText(String.valueOf(bpjs_tk_c));
                F_payment_bpjsksc.setText(String.valueOf(bpjs_ks_c));
                F_payment_total_income.setText(String.valueOf(total_income));

                F_payment_bpjstke.setText(String.valueOf(bpjs_tk_e));
                F_payment_bpjskse.setText(String.valueOf(bpjs_ks_e));
                F_payment_attendance.setText(String.valueOf(attendance));
                F_payment_net_total.setText(String.valueOf(net_total)); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Calculate Error");
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btn_payment_calculate_incomeActionPerformed

    private void F_payment_basicSalaryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_F_payment_basicSalaryKeyReleased

    }//GEN-LAST:event_F_payment_basicSalaryKeyReleased

    
    
    
    
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
    private javax.swing.JComboBox<String> CB_inCharge;
    private javax.swing.JButton D_btn_apprasial;
    private javax.swing.JButton D_btn_asset;
    private javax.swing.JButton D_btn_division;
    private javax.swing.JButton D_btn_employee;
    private javax.swing.JButton D_btn_inventory;
    private javax.swing.JButton D_btn_member;
    private javax.swing.JButton D_btn_payment;
    private javax.swing.JTextArea F_asset_description;
    private javax.swing.JComboBox<String> F_asset_employee;
    private javax.swing.JTextField F_asset_id;
    private javax.swing.JComboBox<String> F_asset_inventory;
    private javax.swing.JTextArea F_asset_notes;
    private javax.swing.JTextField F_asset_place;
    private org.jdesktop.swingx.JXDatePicker F_asset_received_datetime;
    private javax.swing.JSpinner F_asset_received_hours;
    private javax.swing.JSpinner F_asset_received_minute;
    private javax.swing.JSpinner F_asset_received_second;
    private javax.swing.JTextField F_asset_search;
    private javax.swing.JTextField F_division;
    private javax.swing.JTextField F_division_id;
    private javax.swing.JTextField F_division_search;
    private javax.swing.JTextField F_employee_id;
    private javax.swing.JTextField F_inventory_id;
    private javax.swing.JComboBox<String> F_member_division;
    private javax.swing.JComboBox<String> F_member_employee;
    private javax.swing.JTextField F_member_id;
    private javax.swing.JComboBox<String> F_member_level;
    private javax.swing.JTextField F_member_search;
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
    private javax.swing.JTextField F_payment_id;
    private javax.swing.JTextField F_payment_net_total;
    private javax.swing.JTextField F_payment_other;
    private javax.swing.JTextField F_payment_overtime;
    private org.jdesktop.swingx.JXDatePicker F_payment_period;
    private org.jdesktop.swingx.JXDatePicker F_payment_period1;
    private javax.swing.JTextField F_payment_total_income;
    private javax.swing.JTextField F_payment_working_day;
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
    private javax.swing.JButton btn_clear_member;
    private javax.swing.JButton btn_clear_payment;
    private javax.swing.JButton btn_delete_asset;
    private javax.swing.JButton btn_delete_division;
    private javax.swing.JButton btn_delete_employee;
    private javax.swing.JButton btn_delete_inventory;
    private javax.swing.JButton btn_delete_member;
    private javax.swing.JButton btn_delete_payment;
    private javax.swing.JButton btn_edit_asset;
    private javax.swing.JButton btn_edit_division;
    private javax.swing.JButton btn_edit_employee;
    private javax.swing.JButton btn_edit_inventory;
    private javax.swing.JButton btn_edit_member;
    private javax.swing.JButton btn_edit_payment;
    private javax.swing.JButton btn_icon_user;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_menu_apprasial;
    private javax.swing.JButton btn_menu_asset;
    private javax.swing.JButton btn_menu_division;
    private javax.swing.JButton btn_menu_division_member;
    private javax.swing.JButton btn_menu_employee;
    private javax.swing.JButton btn_menu_inventory;
    private javax.swing.JButton btn_menu_payment;
    private javax.swing.JButton btn_payment_calculate_income;
    private javax.swing.JButton btn_print_asset;
    private javax.swing.JButton btn_print_division;
    private javax.swing.JButton btn_print_employee;
    private javax.swing.JButton btn_print_inventory;
    private javax.swing.JButton btn_print_member;
    private javax.swing.JButton btn_profile;
    private javax.swing.JButton btn_save_asset;
    private javax.swing.JButton btn_save_division;
    private javax.swing.JButton btn_save_employee;
    private javax.swing.JButton btn_save_inventory;
    private javax.swing.JButton btn_save_member;
    private javax.swing.JButton btn_save_payment;
    private javax.swing.JButton btn_search_asset;
    private javax.swing.JButton btn_search_asset2;
    private javax.swing.JButton btn_search_division;
    private javax.swing.JButton btn_search_employee;
    private javax.swing.JButton btn_search_inventory;
    private javax.swing.JTabbedPane content;
    private javax.swing.JLabel employee;
    private javax.swing.JPanel footer;
    private Layout.gradient_footer gradient_footer1;
    private Layout.gradient_footer gradient_footer2;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel48;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jkel;
    private javax.swing.JTextArea jtk;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel panel_apprasial;
    private javax.swing.JPanel panel_asset;
    private javax.swing.JPanel panel_btn_profile;
    private javax.swing.JPanel panel_dashboard;
    private javax.swing.JPanel panel_division;
    private javax.swing.JPanel panel_division_member;
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
    private javax.swing.JTable table_member;
    private javax.swing.JTable table_payment;
    private javax.swing.JTextArea talamat;
    private javax.swing.JTextField tbpjsks;
    private javax.swing.JTextField tbpjstk;
    private javax.swing.JTextField tcari1;
    private javax.swing.JTextField tcari_inventory;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tkat;
    private javax.swing.JComboBox<String> tmaritalStatus;
    private javax.swing.JTextField tmodel;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnik;
    private javax.swing.JTextField tnorek;
    private javax.swing.JTextField tnpwp;
    private javax.swing.JTextField tphone;
    private javax.swing.JTextField tqty;
    private javax.swing.JComboBox<String> treligion;
    private javax.swing.JComboBox<String> tstatus;
    private javax.swing.JTextField ttag;
    // End of variables declaration//GEN-END:variables
}
