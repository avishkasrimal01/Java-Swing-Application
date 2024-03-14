package Suppliers.Models;

public class SM {
    public String id;
    public String name;
    public String address;
    public String products;
    public String emp;


    public SM(String id, String name, String address, String products) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.products = products;
        this.emp = emp;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getProducts() {
        return products;
    }

    public String getEmp() {
        return emp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }
}
