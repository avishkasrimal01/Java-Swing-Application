import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dash.Views.Control;
import Database.Database;
import Employees.example.ManageEmployee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Allocate {

    int orderno;
    int empid;
    private JPanel panel1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JTextField ordertextField;
    private JTextField emptextField;
    private JTable table1;

    private Database singleConn;
    static Connection connection;
    PreparedStatement statement,statement2,statement3;
    public String orderid;
    public String empidval;

    public Allocate(int orderno, int empid) {
        this.orderno = orderno;
        this.empid = empid;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public Allocate()
    {
        dbConnect();


        singleConn=Database.getSingleInstance();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAllocate();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAllocate();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllocate();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
    }




    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Allocate allocate = new Allocate();
        JFrame frame = new JFrame("Allocation");
        frame.setTitle("Car Care");
        frame.setSize(600,600);
        frame.setContentPane(allocate.panel1);
        frame.pack();
        frame.setLocationRelativeTo(null); //loads from the center
        frame.setVisible(true);
    }

    public void clearFields(){
        ordertextField.setText("");
        emptextField.setText("");
    }



    public void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("connected to DB");
        } catch (SQLException ex) {
            ex.printStackTrace();  //used the " " because to covert the ex from SQLEXception to String
        }
    }

    public void addAllocate(){
         orderid= ordertextField.getText();
         empidval= emptextField.getText();

        try
        {
            String query="insert into allocate values('"+orderid+"','"+empid+"')";
            singleConn.ExecuteQuery(query);
            System.out.println("Order Allocated.");
            JOptionPane.showMessageDialog(panel1, "Successfully Allocated an orderno:"+orderid+" with employee "+empidval+".", "Sucess", 1);

            clearFields();

            dbConnect();
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an Order."+ex);
        }
    }

    public void updateAllocate(){
        String orderid= ordertextField.getText();
        String empid= emptextField.getText();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL update statement
            String updateQuery = "UPDATE allocate SET empid=? WHERE orderno=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, empid);
                preparedStatement.setString(2, orderid);
                clearFields();
                // Execute update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    System.out.println("Order information updated successfully.");
                } else {
                    System.out.println("No Order found with the specified ID.");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllocate(){
        String orderid= ordertextField.getText();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL delete statement

            String deleteQuery = "DELETE FROM allocate WHERE orderno=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // Set parameter
                preparedStatement.setString(1, orderid);
                // Execute delete
                int rowsAffected = preparedStatement.executeUpdate();
                clearFields();

                // Check if the delete was successful
                if (rowsAffected > 0) {
                    System.out.println("Order deleted successfully.");
                } else {
                    System.out.println("No Order found with the specified ID.");
                }

            }
        } catch (SQLException e) {
            System.out.println("2"+e);
            e.printStackTrace();
        }
    }

    public void back(){
        Control ui=new Control();
        ui.setContentPane(ui.backpanel);
        ui.setTitle("Dashboard 1.0");
        ui.setSize(600,600);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
    }



}
