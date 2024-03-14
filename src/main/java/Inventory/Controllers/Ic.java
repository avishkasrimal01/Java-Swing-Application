package Inventory.Controllers;

import Inventory.Models.Im;
import Service.IService;

import java.util.ArrayList;
public class Ic {
    private ArrayList<Im> items;

    public Ic() {

        items = new ArrayList<>();
        service=new IService();
    }

    Im newitem;

    IService service;


    public Im additem(String productId, String name, String price, String category, String qty) {
        newitem = new Im(productId,name,price,category,qty);
        items.add(newitem);
        return newitem;
    }

    public Im getitemById(String id) {
        for (Im items : items) {
            if (items.getProductId().equals(id)) {
                return items;
            }
        }
        return null;
    }

    public Im updateitem(String productId, String name, String price, String category, String qty) {
        Im item = getitemById(productId);
        if (item != null) {
            item.setProductId(productId);
            item.setName(name);
            item.setPrice(price);
            item.setCategory(category);
            item.setQty(qty);

            newitem=new Im(productId,name,price,category,qty);
        }
        return newitem;
    }

    public Im deleteitem(String id) {
        Im item = getitemById(id);
        if (item != null) {
            items.remove(item);
            newitem=getitemById(id);
        }
        return newitem;
    }

    public ArrayList<Im> getAllitems() {

        return new ArrayList<>(items);
    }

    public boolean additemtodb(){
        return service.additem(newitem);
    }
}
