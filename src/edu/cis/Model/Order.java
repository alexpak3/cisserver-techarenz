package edu.cis.Model;

public class Order {

    String type, itemID, orderID;

    public Order(String type, String itemID, String orderID) {
        this.type = type;
        this.itemID = itemID;
        this.orderID = orderID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "type='" + type + '\'' +
                ", itemID='" + itemID + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
