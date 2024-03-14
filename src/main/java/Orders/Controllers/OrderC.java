package Orders.Controllers;

import Orders.Models.OrderM;
import java.util.ArrayList;

import Service.OrderService;

public class OrderC {
    private ArrayList<OrderM> orders;

    public OrderC() {

        orders = new ArrayList<>();
        service=new OrderService();
    }

    OrderM order;

    OrderService service;

    public OrderM addorder(String orderno, String customername, String category, String price, String vno) {
        order = new OrderM(orderno,customername,category,price,vno);
        orders.add(order);
        return order;
    }

    public OrderM getorderById(String id) {
        for (OrderM orders : orders) {
            if (order.getOrderno().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void updateorder(String orderno, String customername, String category, String price, String vno) {
        OrderM orders = getorderById(orderno);
        if (orders != null) {
            orders.setOrderno(orderno);
            orders.setCustomername(customername);
            orders.setCategory(category);
            orders.setPrice(price);
            orders.setVno(vno);
        }

}
    public void deleteorder(String id) {
        OrderM order = getorderById(id);
        if (order != null) {
            orders.remove(order);
        }
    }

    public ArrayList<OrderM> getAllorders() {

        return new ArrayList<>(orders);
    }

    public boolean addordertodb(){
        return service.additem(order);
    }
}
