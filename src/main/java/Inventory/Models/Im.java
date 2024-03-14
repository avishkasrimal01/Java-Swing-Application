package Inventory.Models;

public class Im {
    public String ProductId;
    public String name;
    public String price;
    public String category;

    public String qty;

    public Im(String productId, String name, String price, String category, String qty) {
        this.ProductId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.qty = qty;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getQty() {
        return qty;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
