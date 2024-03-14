package Suppliers.Views;

import Suppliers.Controllers.SC;
import Suppliers.Models.SM;
import Service.SupplierService;
import Dash.Views.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SV extends JFrame {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField productsTextField;
    private JTextField empTextField;
    private JButton searchButton;
    private JButton updateSupplierButton;
    private JButton backButton;
    private JButton deleteSupplierButton;
    private JButton addSupplierButton;
    public JPanel backPanel;
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private SC supplierController;

    public SV(SC supplierController) {
        this.supplierController = supplierController;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchSupplier();
            }
        });
        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addSupplier();
            }
        });
        updateSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateSupplier();
            }
        });
        deleteSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSupplier();
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



    private void addSupplier() {


        String id = idTextField.getText();
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String products = productsTextField.getText();

        if (!id.isEmpty() && !name.isEmpty()) {
            supplierController.addSupplier(id, name, address, products);
            if (supplierController.addsuppliertodb())
            {
                JOptionPane.showMessageDialog(backPanel, "Successfully Added a supplier to Database", "Sucess", 0);
            }
            else
            {
                JOptionPane.showMessageDialog(backPanel, "Cannot insert a supplier to DB", "Error", 0);
            }

            updateTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Supplier ID and Name.");
        }
    }

    private void searchSupplier() {
        String id = idTextField.getText();
        SM supplier = supplierController.getSupplierById(id);
        if (supplier != null) {
            showSupplierDetails(supplier);
        } else {
            JOptionPane.showMessageDialog(this, "Supplier not found.");
            clearFields();
        }

    }

    private void updateSupplier() {
        supplierController.updateSupplier(idTextField.getText(), nameTextField.getText(),
                addressTextField.getText(), productsTextField.getText());

        SupplierService.updatesupp(idTextField.getText(), nameTextField.getText(),
                addressTextField.getText(), productsTextField.getText());

        updateTable();
        clearFields();
    }

    private void deleteSupplier() {
        supplierController.deleteSupplier(idTextField.getText());
        SupplierService.deletesupp(idTextField.getText());
        updateTable();
        clearFields();
    }

    private void showSupplierDetails(SM supplier) {
        idTextField.setText(supplier.getId());
        nameTextField.setText(supplier.getName());
        addressTextField.setText(supplier.getAddress());
        productsTextField.setText(supplier.getProducts());
    }

    private void updateTable() {
        clearTable();
        ArrayList<SM> allSuppliers = supplierController.getAllSuppliers();
        for (SM supplier : allSuppliers) {
            tableModel.addRow(new Object[]{supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getProducts()});
        }
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        productsTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SC supplierController = new SC();
                SV ui= new SV(supplierController);
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
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Products");
        supplierTable = new JTable(tableModel);
    }
}
