package edu.cis.Model;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> eatriumItems;
    private String adminID;


    public Menu(ArrayList<MenuItem> eatriumItems, String adminID) {
        this.eatriumItems = eatriumItems;
        this.adminID = adminID;
    }

    public ArrayList<MenuItem> getEatriumItems() {
        return eatriumItems;
    }

    public void setEatriumItems(ArrayList<MenuItem> eatriumItems) {
        this.eatriumItems = eatriumItems;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public MenuItem getItem( String itemID){
       for ( int i =0; i<eatriumItems.size();i++){
           if ( eatriumItems.get(i).getId().equals(itemID)){
               return eatriumItems.get(i);
           }
       }
       return null;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "eatriumItems=" + eatriumItems +
                ", adminID='" + adminID + '\'' +
                '}';
    }
}
