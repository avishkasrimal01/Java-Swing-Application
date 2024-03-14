package Inventory.Views;

import Service.IService;
import Inventory.Controllers.Ic;
import Inventory.Models.Im;
import Dash.Views.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Iv extends JFrame{
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField categoryTextField;
    private JTextField qtyTextField;
    private JButton searchButton;
    private JButton updateempButton;
    private JButton backButton;
    private JButton deleteempButton;
    private JButton addempButton;
    public JPanel backPanel;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private Ic items;

    public Iv(Ic items) {
        this.items = items;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchitem();
            }
        });
        addempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                additem();
            }
        });
        updateempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateitem();
            }
        });
        deleteempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deleteitem();
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

    private void additem() {


        String id = idTextField.getText();
        String name = nameTextField.getText();
        String price = priceTextField.getText();
        String category = categoryTextField.getText();
        String qty = qtyTextField.getText();


        if (!id.isEmpty() && !name.isEmpty()) {
            items.additem(id, name, price, category,qty);
            if (items.additemtodb())
            {
                JOptionPane.showMessageDialog(backPanel, "Successfully Added a Rider to Database", "Sucess", 0);
            }
            else
            {
                JOptionPane.showMessageDialog(backPanel, "Cannot insert a Rider to DB", "Error", 0);
            }
            updateTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID and Name.");
        }
    }

    private void searchitem() {
        String id = idTextField.getText();
        Im item = items.getitemById(id);
        if (item != null) {
            showItemDetails(item);
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            clearFields();
        }

    }

    private void updateitem() {
        items.updateitem(idTextField.getText(), nameTextField.getText(),
                priceTextField.getText(), categoryTextField.getText(),qtyTextField.getText());
        IService.updateItem(idTextField.getText(), nameTextField.getText(),
                priceTextField.getText(), categoryTextField.getText(),qtyTextField.getText());
        updateTable();
        clearFields();
    }

    private void deleteitem() {
        items.deleteitem(idTextField.getText());
        IService.deleteItem(idTextField.getText());
        updateTable();
        clearFields();

    }

    private void showItemDetails(Im items) {
        idTextField.setText(items.getProductId());
        nameTextField.setText(items.getName());
        priceTextField.setText(items.getPrice());
        categoryTextField.setText(items.getCategory());
        qtyTextField.setText(items.getQty());
    }

    private void updateTable() {
        clearTable();
        ArrayList<Im> allitems = items.getAllitems();
        for (Im items : allitems) {
            tableModel.addRow(new Object[]{items.getProductId(), items.getName(), items.getPrice(), items.getCategory(),items.getQty()});
        }
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        priceTextField.setText("");
        categoryTextField.setText("");
        qtyTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ic itemController = new Ic();
                Iv ui= new Iv(itemController);
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
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Category");
        tableModel.addColumn("Quantity");
        supplierTable = new JTable(tableModel);
    }
}




