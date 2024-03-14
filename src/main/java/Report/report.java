package Report;

import Dash.Views.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class report {
    Connection connection;
    PreparedStatement statement,statement2;

    private JTable table1;
    public JPanel panel1;
    private JComboBox<String> comboBox1;
    private JButton filter;
    private JTable table2;
    private JButton backButton;

    public report(){
        dbConnect();
        showTable();
        loadComboBox();


        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = comboBox1.getSelectedItem().toString();
                filter(selectedMonth);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Control ui=new Control();
                ui.setContentPane(ui.backpanel);
                ui.setTitle("Dashboard 1.0");
                ui.setSize(600,600);
                ui.pack();
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        report report = new report();

        JFrame frame = new JFrame("report");
        frame.setContentPane(new report().panel1);
        frame.pack();
        frame.setVisible(true);
    }


    public void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("connected to DB");
        } catch (SQLException ex) {
            ex.printStackTrace();  //used the " " because to covert the ex from SQLEXception to String
        }
        }

        public void showTable(){
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
        try {
            statement = connection.prepareStatement("SELECT * FROM supplies");
            ResultSet resultSet = statement.executeQuery();
            model.setRowCount(0); // Clear existing data in the table
            model.setColumnIdentifiers(new Object[]{"Table","productId", "Name", "Credit"});
            model2.setRowCount(0); // Clear existing data in the table
            model2.setColumnIdentifiers(new Object[]{"Table","productId", "Name", "Price","Month"});

            while (resultSet.next()) {
                // Retrieve data from the result set
                int ID = resultSet.getInt("productID");
                String productName = resultSet.getString("Product Name");
                int price = resultSet.getInt("Price");
                String month = resultSet.getString("Date");
                model.addRow(new Object[]{"SUPPLIES",ID,productName,price});
            }


            //DATA FROM TABLE REGISTRY
            statement2 = connection.prepareStatement("SELECT * FROM registry");
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet2.next()) {
                // Retrieve data from the result set
                int ID = resultSet2.getInt("ID");
                String productName = resultSet2.getString("Transation Name");
                int price = resultSet2.getInt("Price");
                String month = resultSet2.getString("Date");

                model2.addRow(new Object[]{"REGISTRY",ID,productName,price,month});
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        }

        public void loadComboBox(){ String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            for (String month : months) {
                comboBox1.addItem(month);
            }
        }

        public void filter(String selectedMonth) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
            try {
                statement = connection.prepareStatement("SELECT * FROM supplies WHERE UPPER(Date) = ?");
                statement.setString(1, selectedMonth.toUpperCase());
                ResultSet resultSet = statement.executeQuery();
                model.setRowCount(0); // Clear existing data in the table
                model.setColumnIdentifiers(new Object[]{"Table","productId", "Name","Credit"});
                model2.setRowCount(0); // Clear existing data in the table
                model2.setColumnIdentifiers(new Object[]{"Table","productId", "Name", "Price","Month"});

                while (resultSet.next()) {
                    // Retrieve data from the result set
                    int ID = resultSet.getInt("productID");
                    String productName = resultSet.getString("Product Name");
                    int price = resultSet.getInt("Price");
                    String month = resultSet.getString("Date");
                    model.addRow(new Object[]{"SUPPLIES",ID,productName,price});
                }


                //DATA FROM TABLE REGISTRY
                statement2 = connection.prepareStatement("SELECT * FROM registry WHERE UPPER(Date) = ?");
                statement2.setString(1, selectedMonth.toUpperCase());
                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()) {
                    // Retrieve data from the result set
                    int ID = resultSet2.getInt("ID");
                    String productName = resultSet2.getString("Transation Name");
                    int price = resultSet2.getInt("Price");
                    String month = resultSet2.getString("Date");

                    model2.addRow(new Object[]{"REGISTRY",ID,productName,price,month});
                }

            } catch (SQLException ex){
                ex.printStackTrace();
            }

        }
}
