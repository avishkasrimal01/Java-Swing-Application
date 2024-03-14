package Orders.Views;

import Orders.Controllers.OrderC;
import Orders.Models.OrderM;
import Service.OrderService;
import Dash.Views.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderV extends JFrame{
    private JTextField ordernoTextField;
    private JTextField cnameTextField;
    private JTextField catTextField;
    private JTextField priceTextField;
    private JTextField vnoTextField;
    private JButton searchButton;
    private JButton updateorderButton;
    private JButton backButton;
    private JButton deleteorderButton;
    private JButton addorderButton;
    public JPanel backPanel;
    private JTable supplierTable;
    private JButton confirmButton;
    private JTextField noTextField;
    private DefaultTableModel tableModel;
    private OrderC orders;


    public OrderV(OrderC orders) {
        this.orders = orders;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchorder();
            }
        });
        addorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addorder();
            }
        });
        updateorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateorder();
            }
        });
        deleteorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                deleteorder();
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
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    private void addorder() {


        String no = ordernoTextField.getText();
        String cname = cnameTextField.getText();
        String category = catTextField.getText();
        String price = priceTextField.getText();
        String vno = vnoTextField.getText();


        if (!no.isEmpty() && !cname.isEmpty()) {
            orders.addorder(no, cname, category, price,vno);
            if (orders.addordertodb())
            {
                JOptionPane.showMessageDialog(backPanel, "Successfully Added an order", "Sucess", 1);
            }
            else
            {
                JOptionPane.showMessageDialog(backPanel, "Cannot insert a order to DB", "Error", 0);
            }

            updateTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Order No.");
        }
    }

    private void searchorder() {
        String id = ordernoTextField.getText();
        OrderM order = orders.getorderById(id);
        if (order != null) {
            showorderDetails(order);
        } else {
            JOptionPane.showMessageDialog(this, "Order not found.");
            clearFields();
        }

    }

    private void updateorder() {
        orders.updateorder(ordernoTextField.getText(), cnameTextField.getText(),
                catTextField.getText(), priceTextField.getText(),vnoTextField.getText());

        OrderService.updateorder(ordernoTextField.getText(), cnameTextField.getText(),
                catTextField.getText(), priceTextField.getText(),vnoTextField.getText());

        updateTable();
        clearFields();
    }

    private void deleteorder() {
        orders.deleteorder(ordernoTextField.getText());
        OrderService.deleteOrder(ordernoTextField.getText());
        updateTable();
        clearFields();
    }

    private void showorderDetails(OrderM orders) {
        ordernoTextField.setText(orders.getOrderno());
        cnameTextField.setText(orders.getCustomername());
        catTextField.setText(orders.getCategory());
        priceTextField.setText(orders.getPrice());
        vnoTextField.setText(orders.getVno());
    }

    private void updateTable() {
        clearTable();
        ArrayList<OrderM> allorders = orders.getAllorders();
        for (OrderM orders : allorders) {
            tableModel.addRow(new Object[]{orders.getOrderno(), orders.getCustomername(), orders.getCategory(), orders.getPrice(),orders.getVno()});
        }
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void clearFields() {
        ordernoTextField.setText("");
        cnameTextField.setText("");
        catTextField.setText("");
        priceTextField.setText("");
        vnoTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OrderC orderController = new OrderC();
                OrderV ui= new OrderV(orderController);
                ui.setContentPane(ui.backPanel);
                ui.setTitle("Bicycle Manager 1.0");
                ui.setSize(600,600);
                ui.pack();
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
    }


    private void createUIComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Order No");
        tableModel.addColumn("Customer Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Vehicle No");
        supplierTable = new JTable(tableModel);
    }
}
