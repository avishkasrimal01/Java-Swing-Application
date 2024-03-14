package Suppliers.Controllers;

import Suppliers.Models.SM;
import java.util.ArrayList;
import Service.SupplierService;

public class SC {
    private ArrayList<SM> suppliers;

    public SC() {
        suppliers = new ArrayList<>();
        service=new SupplierService();
    }

    SM newSupplier;

    SupplierService service;

    public SM addSupplier(String id, String name, String address, String products) {
        newSupplier = new SM(id, name, address, products);
        suppliers.add(newSupplier);
        return newSupplier;
    }

    public SM getSupplierById(String id) {
        for (SM supplier : suppliers) {
            if (supplier.getId().equals(id)) {
                return supplier;
            }
        }
        return null;
    }

    public void updateSupplier(String id, String name, String address, String products) {
        SM supplier = getSupplierById(id);
        if (supplier != null) {
            supplier.setName(name);
            supplier.setAddress(address);
            supplier.setProducts(products);
        }
    }

    public void deleteSupplier(String id) {
        SM supplier = getSupplierById(id);
        if (supplier != null) {
            suppliers.remove(supplier);
        }
    }

    public ArrayList<SM> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }

    public boolean addsuppliertodb(){
        return service.addsupplier(newSupplier);
    }
}
