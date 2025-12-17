package edu.cis.Model;

import java.util.ArrayList;

public class CISUser {


    private String userId;
    private String name;
    private String yearLevel;
    private ArrayList<Order> orders;
    private double money;

    public CISUser() {
        orders = new ArrayList<Order>();
        money = 50;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public double getMoney() {
        return money;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addOrder (Order order ){
        this.orders.add(order);
    }

    public String returnOrders (){
        String orders = "";
        for (int i = 0; i < this.orders.size(); i++) {
            orders += this.orders.get(i).toString();
        }
        return orders;

    }

    @Override
    public String toString() {
        return "CISUser{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", yearLevel='" + yearLevel + '\'' +
                ", orders=" + orders +
                ", money=" + money +
                '}';
    }
}
