package Dash.Views;

import Employees.example.ManageEmployee;
import Inventory.Controllers.Ic;
import Inventory.Views.Iv;
import Orders.Controllers.OrderC;
import Orders.Views.OrderV;
import Suppliers.Controllers.SC;
import Suppliers.Views.SV;
import Report.report;
import ConfirmMail.Confirm;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control extends JFrame {
    private JButton orders;
    private JButton inventory;
    private JButton suppliers;
    private JButton employee;
    private JButton report;
    private JButton notification;
    private JButton email;
    public JPanel backpanel;
    private JButton logoutButton;
    private JButton selectButton;


    public Control () {


        orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderC orderController = new OrderC();
                OrderV ui= new OrderV(orderController);
                ui.setContentPane(ui.backPanel);
                ui.setTitle("Bicycle Manager 1.0");
                ui.setSize(600,600);
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
        inventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ic itemController = new Ic();
                Iv ui= new Iv(itemController);
                ui.setContentPane(ui.backPanel);
                ui.setTitle("Bicycle Manager 1.0");
                ui.setSize(600,600);
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
        suppliers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SC supplierController = new SC();
                SV ui= new SV(supplierController);
                ui.setContentPane(ui.backPanel);
                ui.setTitle("Bicycle Manager 1.0");
                ui.setSize(600,600);
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEmployee manageEmployee = new ManageEmployee();
//        manageEmployee.dbConnect();
                JFrame frame = new JFrame("ManageEmployee");
                // Add some sample data directly to the table



                //default
                frame.setContentPane(manageEmployee.mainPanel);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setLocationRelativeTo(null); //loads from the center
                frame.setVisible(true);
            }
        });
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report.report report = new report();
                JFrame frame = new JFrame("report");
                frame.setContentPane(new report().panel1);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Order Confirmation");
                    frame.setContentPane(new Confirm().panel1);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(600,600);
                    frame.pack();
                    frame.setVisible(true);
                });
            }
        });
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Order Confirmation");
                    frame.setContentPane(new Confirm().panel1);
                    frame.pack();
                    frame.setVisible(true);
                });
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Allocate allocate = new Allocate();
                JFrame frame = new JFrame("Allocation");
                frame.setTitle("Car Care");
                frame.setSize(600,600);
                frame.setContentPane(allocate.backpanel);
                frame.pack();
                frame.setLocationRelativeTo(null); //loads from the center
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Control ui=new Control();
        ui.setContentPane(ui.backpanel);
        ui.setTitle("Dashboard 1.0");
        ui.setSize(600,600);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
    }

}
