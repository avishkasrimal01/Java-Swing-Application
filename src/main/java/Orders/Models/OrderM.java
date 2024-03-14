package Orders.Models;

public class OrderM {
    public String orderno;
    public String customername;
    public String category;
    public String price;
    public String vno;
    public String empno;

    public OrderM(String orderno, String customername, String category, String price, String vno) {
        this.orderno = orderno;
        this.customername = customername;
        this.category = category;
        this.price = price;
        this.vno = vno;
        this.empno=empno;

    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCategory() {
        return category;
    }

    public String getEmpno() {
        return empno;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }
}
