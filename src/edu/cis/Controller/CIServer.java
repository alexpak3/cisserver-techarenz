/*
 * File: CIServer.java
 * ------------------------------
 * When it is finished, this program will implement a basic
 * ecommerce network management server.  Remember to update this comment!
 */

package edu.cis.Controller;

import acm.program.*;
import edu.cis.Model.*;
import edu.cis.Utils.SimpleServer;
import java.util.ArrayList;

public class CIServer extends ConsoleProgram
        implements SimpleServerListener
{

    /* The internet port to listen to requests on */
    private static final int PORT = 8000;

    /* The server object. All you need to do is start it */
    private SimpleServer server = new SimpleServer(this, PORT);

    private ArrayList<CISUser> users;
    private Menu menu;

    /**
     * Starts the server running so that when a program sends
     * a request to this server, the method requestMade is
     * called.
     */
    public void run()
    {
        println("Starting server on port " + PORT);
        server.start();
    }

    /**
     * When a request is sent to this server, this method is
     * called. It must return a String.
     *
     * @param request a Request object built by SimpleServer from an
     *                incoming network request by the client
     */
    public String requestMade(Request request)
    {
        String cmd = request.getCommand();
        println(request.toString());

        // your code here.
        if (request.getCommand().equals(CISConstants.PING))
        {
            final String PING_MSG = "Hello, internet";

            //println is used instead of System.out.println to print to the server GUI
            println("   => " + PING_MSG);
            return PING_MSG;
        }

        return "Error: Unknown command " + cmd + ".";
    }

    public String createUser(Request req){
        CISUser nUser = new CISUser();
        String userID =req.getParam(CISConstants.USER_ID_PARAM);
        String userName = req.getParam(CISConstants.USER_NAME_PARAM);
        String userYear = req.getParam(CISConstants.YEAR_LEVEL_PARAM);

        nUser.setName(userName);
        nUser.setUserId(userID);
        nUser.setYearLevel(userYear);
        return CISConstants.SUCCESS;
    }


    public String additem (Request req){
        MenuItem nItem = new MenuItem();
        String ItemName = req.getParam(CISConstants.ITEM_NAME_PARAM);
        String description = req.getParam(CISConstants.DESC_PARAM);
        double price = Double.parseDouble(req.getParam(CISConstants.PRICE_PARAM));
        String itemType = req.getParam(CISConstants.ITEM_TYPE_PARAM);
        String itemId = req.getParam(CISConstants.ITEM_ID_PARAM);

        nItem.setName(ItemName);
        nItem.setDescription(description);
        nItem.setPrice(price);
        nItem.setId(itemId);
        nItem.setType(itemType);

        return CISConstants.SUCCESS;

    }

    public String placeOrder(Request req){
        String orderID = req.getParam(CISConstants.ORDER_ID_PARAM);
        String itemID = req.getParam(CISConstants.ITEM_ID_PARAM);
        String userID = req.getParam(CISConstants.USER_ID_PARAM);
        String orderType = req.getParam(CISConstants.ORDER_TYPE_PARAM);

        if (orderID == null) {
            return CISConstants.ORDER_INVALID_ERR;
        }
        CISUser user = null;
        for(int i = 0; i < users.size(); i++){
            String name = users.get(i).getUserId();
            if(userID.equals(name)){
                user = users.get(i);
                break;
            }
        }
        if(user == null) {
            return CISConstants.USER_INVALID_ERR;
        }

        MenuItem item = null;

        boolean itemExists = false;

        for(int i =0; i<menu.getEatriumItems().size(); i++){
            String currentID = menu.getEatriumItems().get(i).getId();
            if(currentID.equals(itemID)){
                itemExists = true;
                item = menu.getEatriumItems().get(i);
            }
        }
        if (!itemExists) {
            return CISConstants.INVALID_MENU_ITEM_ERR;
        }

        if (item.getAmmountAvailable() == 0) {
            return CISConstants.SOLD_OUT_ERR;
        }

        if (user.getMoney() < item.getPrice()) {
            return CISConstants.USER_BROKE_ERR;
        }

        ArrayList<Order> currentOrders = user.getOrders();
        for (Order current:currentOrders){
            if (current.getOrderID().equals(orderID)){
                return CISConstants.DUP_ORDER_ERR;
            }
        }

        user.setMoney(user.getMoney() - item.getPrice());
        item.setAmmountAvailable(item.getAmmountAvailable()-1);
        String nItemID = item.getId();
        Order newOrder = new Order(orderType, nItemID, orderID);
        user.addOrder(newOrder);

        return CISConstants.SUCCESS;
    }

    public String deleteOrder (Request req){
        String userID = req.getParam(CISConstants.USER_ID_PARAM);
        String orderID = req.getParam(CISConstants.ORDER_ID_PARAM);

        CISUser user = null;
        for(int i = 0; i < users.size(); i++){

            String name = users.get(i).getUserId();
            if(userID.equals(name)){
                user = users.get(i);
                break;
            }
        }
        if(user == null) {
            return CISConstants.USER_INVALID_ERR;
        }
        ArrayList<Order> userOrders = user.getOrders();
        Order chosenOrder = null;
        for(int i =0; i<userOrders.size();i++){
            if(userOrders.get(i).getOrderID().equals(orderID)){
                chosenOrder = userOrders.get(i);
                break;
            }
        }
        if(chosenOrder == null) {
            return CISConstants.ORDER_INVALID_ERR;
        }

        userOrders.remove(chosenOrder);
        user.setOrders(userOrders);
        return CISConstants.SUCCESS;
    }


    public String getOrder(Request req){
        String userID = req.getParam(CISConstants.USER_ID_PARAM);
        String orderID = req.getParam(CISConstants.ORDER_ID_PARAM);

        CISUser user = null;
        for(int i = 0; i < users.size(); i++){
            String name = users.get(i).getUserId();
            if(userID.equals(name)){
                user = users.get(i);
                break;
            }
        }
        if(user == null) {
            return CISConstants.USER_INVALID_ERR;
        }
        ArrayList<Order> currentOrders = user.getOrders();
        for(int i = 0; i <currentOrders.size(); i++){
            if(currentOrders.get(i).getOrderID().equals(orderID)){
                return currentOrders.get(i).toString();
            }
        }
        return CISConstants.ORDER_INVALID_ERR;
    }

    public String getUser(Request req){
        String userID = req.getParam(CISConstants.USER_ID_PARAM);
        CISUser user = null;
        for(int i = 0; i < users.size(); i++){
            String name = users.get(i).getUserId();
            if(userID.equals(name)){
                user = users.get(i);
                break;
            }
        }
        if(user == null) {
            return CISConstants.USER_INVALID_ERR;
        }
        return user.toString();
    }

    public String getItem(Request req){
        String menuItemID = req.getParam(CISConstants.ITEM_ID_PARAM);

        if(menu.getItem(menuItemID) == null) return CISConstants.INVALID_MENU_ITEM_ERR;
        return menu.getItem(menuItemID).toString();

    }

    public String getCart(Request req){
        String userID = req.getParam(CISConstants.USER_ID_PARAM);
        CISUser user = null;
        for(int i= 0; i< users.size(); i++){
            String name = users.get(i).getUserId();
            if(userID.equals(name)){
                user = users.get(i);
                break;
            }
        }
        if(user == null) {
            return CISConstants.USER_INVALID_ERR;
        }

        return user.returnOrders();
    }

    public static void main(String[] args)
    {
        CIServer f = new CIServer();
        f.start(args);
    }
}
