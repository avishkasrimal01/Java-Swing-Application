package Employees.example;

import Dash.Views.Control;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ManageEmployee {
    public JPanel mainPanel;
    private JLabel banner;
    private JLabel lblFName;
    private JTextField txtFname;
    private JTextField txtFullName;
    private JTextField txtemail;
    private JTextField txtNIC;
    private JTextField txtAge;
    private JTextField txtTP;
    private JTextField txtGender;
    private JTextField txtSal;
    private JButton btnAdd;
    private JTable table1;
    private JButton btnDelete;
    private JScrollPane tbl;
    private JButton btnClear;
    private JTextField txtDel;
    private JButton updateButton;
    private JLabel lbleId;
    private JButton backButton;

    Connection connection;
    PreparedStatement statement, statement2;


    public ManageEmployee() {  //constructor
        dbConnect(); //coneects the databse

        loadDataIntoTable();
        lbleId.setVisible(false);

        final int selectedID = 0;

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFname.getText().isEmpty() || txtFullName.getText().isEmpty() ||
                        txtAge.getText().isEmpty() || txtemail.getText().isEmpty() ||
                        txtNIC.getText().isEmpty() || txtTP.getText().isEmpty() ||
                        txtGender.getText().isEmpty() || txtSal.getText().isEmpty()) {

                    // Display a JOptionPane indicating that fields are empty
                    JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String firstName, fullName, email, nic, tp, gnder;
                int sal, age;



                firstName = txtFname.getText();
                fullName = txtFullName.getText();
                email = txtemail.getText();
                nic = txtNIC.getText();
                age = Integer.parseInt(txtAge.getText());
                tp = txtTP.getText();
                gnder = txtGender.getText();
                sal = Integer.parseInt(txtSal.getText());

                    try{
                        statement = connection.prepareStatement("INSERT INTO employeetbl (firstName, fullName, Email, NIC, Age, Telephone, Gender, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                        statement.setString(1, firstName);
                        statement.setString(2, fullName);
                        statement.setString(3, email);
                        statement.setString(4, nic);
                        statement.setInt(5, age);
                        statement.setString(6, tp);
                        statement.setString(7, gnder);
                        statement.setInt(8, sal);

                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Employ Added");
                        loadDataIntoTable();

                        //clears the values in the textields after addding data
                        txtFname.setText("");
                        txtFullName.setText("");
                        txtAge.setText("");
                        txtemail.setText("");
                        txtNIC.setText("");
                        txtTP.setText("");
                        txtGender.setText("");
                        txtSal.setText("");

                    } catch (SQLException ex) {
                        ex.printStackTrace();  // Print the exception details
                    }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtDel.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter Employee Id to delete", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String employeeToDelete = txtDel.getText().trim();

                try {
                    // Assuming "employeeId" is the column containing the unique identifier for an employee
                    String deleteQuery = "DELETE FROM employeetbl WHERE eId = ?";
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);

                    // Assuming "employeeId" is an integer; adjust the type accordingly
                    int employeeIdToDelete = Integer.parseInt(employeeToDelete);
                    deleteStatement.setInt(1, employeeIdToDelete);

                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Employee deleted successfully");
                        loadDataIntoTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee not found");
                    }

                    // Clear the text field after deletion
                    txtDel.setText("");

                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error deleting employee");
                }

            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clears the values in the textields after addding data
                txtFname.setText("");
                txtFullName.setText("");
                txtAge.setText("");
                txtemail.setText("");
                txtNIC.setText("");
                txtTP.setText("");
                txtGender.setText("");
                txtSal.setText("");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtFname.getText().isEmpty() || txtFullName.getText().isEmpty() ||
                        txtAge.getText().isEmpty() || txtemail.getText().isEmpty() ||
                        txtNIC.getText().isEmpty() || txtTP.getText().isEmpty() ||
                        txtGender.getText().isEmpty() || txtSal.getText().isEmpty()) {

                    // Display a JOptionPane indicating that fields are empty
                    JOptionPane.showMessageDialog(null, "A Record must be selected from the table", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    // Assuming you have an employee ID to uniquely identify the record to be updated
                    int employeeID = Integer.parseInt(lbleId.getText());

                    String firstName, fullName, email, nic, tp, gnder;
                    int sal, age;

                    firstName = txtFname.getText();
                    fullName = txtFullName.getText();
                    email = txtemail.getText();
                    nic = txtNIC.getText();
                    age = Integer.parseInt(txtAge.getText());
                    tp = txtTP.getText();
                    gnder = txtGender.getText();
                    sal = Integer.parseInt(txtSal.getText());

                    statement = connection.prepareStatement("UPDATE employeetbl SET firstName=?, fullName=?, Email=?, NIC=?, Age=?, Telephone=?, Gender=?, Salary=? WHERE eId=?");
                    statement.setString(1, firstName);
                    statement.setString(2, fullName);
                    statement.setString(3, email);
                    statement.setString(4, nic);
                    statement.setInt(5, age);
                    statement.setString(6, tp);
                    statement.setString(7, gnder);
                    statement.setInt(8, sal);
                    statement.setInt(9, employeeID);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Employee Updated");
                    loadDataIntoTable();

                    // Clears the values in the text fields after updating data
                    txtFname.setText("");
                    txtFullName.setText("");
                    txtAge.setText("");
                    txtemail.setText("");
                    txtNIC.setText("");
                    txtTP.setText("");
                    txtGender.setText("");
                    txtSal.setText("");
                    lbleId.setText(""); // Assuming you have a field for the employee ID

                } catch (SQLException ex) {
                    ex.printStackTrace();  // Print the exception details
                }

            }
        });

        // Assuming 'table1' is your JTable
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();

                    // Check if any row is selected
                    if (selectedRow != -1) {
                        // Assuming your table model is a DefaultTableModel
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();

                        // Retrieve data from the selected row
                        int eID = (int) model.getValueAt(selectedRow, 0);
                        String firstName = (String) model.getValueAt(selectedRow, 1);
                        String fullName = (String) model.getValueAt(selectedRow, 2);
                        String email = (String) model.getValueAt(selectedRow, 3);
                        String nic = (String) model.getValueAt(selectedRow, 4);
                        int age = (int) model.getValueAt(selectedRow, 5);
                        String tp = (String) model.getValueAt(selectedRow, 6);
                        String gender = (String) model.getValueAt(selectedRow, 7);
                        int salary = (int) model.getValueAt(selectedRow, 8);

                        // Set the retrieved data to your text fields
                        lbleId.setText(String.valueOf(eID));
                        txtFname.setText(firstName);
                        txtFullName.setText(fullName);
                        txtemail.setText(email);
                        txtNIC.setText(nic);
                        txtAge.setText(String.valueOf(age));
                        txtTP.setText(tp);
                        txtGender.setText(gender);
                        txtSal.setText(String.valueOf(salary));


                        // Add code to set other text fields as needed
                    }

                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Control ui=new Control();
                ui.setContentPane(ui.backpanel);
                ui.setTitle("Dashboard 1.0");
                ui.setSize(600,600);
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
    }






    public static void main(String[] args) {
        ManageEmployee manageEmployee = new ManageEmployee();
//        manageEmployee.dbConnect();
        JFrame frame = new JFrame("ManageEmployee");
        // Add some sample data directly to the table



        //default
        frame.setTitle("Car Care");
        frame.setSize(600,600);
        frame.setContentPane(manageEmployee.mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null); //loads from the center
        frame.setVisible(true);

    }


    public void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("connected to DB");
            statement2 = connection.prepareStatement("SELECT * FROM employeetbl");
        } catch (SQLException ex) {
            ex.printStackTrace();  //used the " " because to covert the ex from SQLEXception to String
        }
    }

    public void loadDataIntoTable() {
        try {
            ResultSet resultSet = statement2.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // Clear existing data in the table
            model.setColumnIdentifiers(new Object[]{"eId","First Name","Full name" ,"Email", "NIC", "Age" ,"Telephone", "Gender", "Salary"}); //column names

            while (resultSet.next()) {
                // Retrieve data from the result set
                int eID = resultSet.getInt("eId");
                String firstName = resultSet.getString("firstName");
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("Email");
                String nic = resultSet.getString("NIC");
                int age = resultSet.getInt("Age");
                String tp = resultSet.getString("Telephone");
                String gender = resultSet.getString("Gender");
                int salary = resultSet.getInt("Salary");

                // Add data to the table
                System.out.println(eID + " " + fullName);
                model.addRow(new Object[]{eID,firstName,fullName,email, nic, age,tp, gender,salary});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();  // Handle exceptions appropriately
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void validations(){

    }
}