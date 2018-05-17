package com.sg.week4flooringmastery.ui;

import com.sg.week4flooringmastery.dto.Order;
import com.sg.week4flooringmastery.service.FlooringMasteryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {

    private UserIO io;
    private FlooringMasteryService service;
    private final String stateNames = " OH, PA, MI, IN";
    private final String types = "Carpet, Laminate, Tile, Wood";

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int getSelection() {
        io.print("Welcome To The Masters Of Flooring!");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public LocalDate getDate() {
        LocalDate dateIn = io.readLocalDate("Please Enter Order Date (MM/DD/YYYY) Format only");
        return dateIn;
    }

    public int getOrderNum() {
        System.out.println("\n");
        int orderNumber = io.readInt("Please Enter your Order Number");
        System.out.println("\n");
        return orderNumber;
    }

    public String acceptOrder() {
        String acceptOrder = io.readString("Confirm Order, Y/N ??");
        return acceptOrder;
    }

    public void orderSummary(Order order) {
        System.out.println("\n");
        System.out.println("Material Cost Per Square Foot: " + order.getCostPerSqFt());
        System.out.println("Labor Cost Per Sqaure Foot " + order.getLaborCostPerSqFt());
        System.out.println("Total Material Cost " + order.getMaterialCost());
        System.out.println("Total Labor Cost " + order.getLaborCost());
        System.out.println("Total Taxes " + order.getTotalTax());
        System.out.println("Total Cost " + order.getTotalCost());
        System.out.println("Order To Be Received at " + order.getTimeStamp());
        System.out.println("\n");
    }

    public Order editOrder(Order order) {
        
        BigDecimal zero = BigDecimal.ZERO;

        System.out.println("Leave Blank if no Changes are Needed");
        System.out.println("\n");
        String customerName = io.readString("Change Customer Name To? ");
        String state = io.readOptionalState("Change Location To? " + stateNames);
        String productType = io.readOptionalProduct("Change Product Type To? " + types);
        BigDecimal area = io.readOptionalBigDecimal("Change Area of Project?");
        
        if (customerName.equals("")) {
            customerName = order.getCustomerName();
        } else {
            order.setCustomerName(customerName);
        }
        if (state.equals("")) {
            state = order.getState();
        } else {
            order.setState(state);
        }
        if (productType.equals("")) {
            productType = order.getCustomerName();
        } else {
            order.setProductType(productType);
        }
        if (area.compareTo(zero) == 0) {
            area = order.getArea();
        } else {
            order.setArea(area);
        }
       
        return order;
    }

    public Order getOrderData() {

        String customerName = io.readString("Please Enter Customer Name");
        String state = io.readState("Please enter the State of Where the Sale is Occuring " + stateNames);
        String productType = io.readProduct("Please enter the Product Type " + types);
        BigDecimal area = io.readBigDecimal("Please enter the Area in SqFeet that you want to Cover");
        LocalDate time = LocalDate.now();

        Order currentOrder = new Order();

        currentOrder.setOrderNumber(currentOrder.getOrderNumber());
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        currentOrder.setTimeStamp(time);
        return currentOrder;
    }

    public void displayByDate(LocalDate date, List<Order> orders) {
        System.out.println("\n");
        for (Order order : orders) {
            if (date.equals(order.getTimeStamp())) {
                io.print(order.getOrderNumber() + ": "
                        + order.getCustomerName() + " "
                        + order.getState() + " "
                        + order.getProductType() + " "
                        + order.getTotalCost() + " "
                        + order.getTimeStamp());
            } else {
                System.out.println("No Order Found");
            }
        }
        System.out.println("\n");
    }

    public void orderSuccesfullBanner() {
        System.out.println("Your Order Has Been Successfully Stored In The System.");
        System.out.println("\n");
    }

    public void SearchBanner() {
        System.out.println("===SEARCH BY DATE===");
    }

    public void editBanner() {
        System.out.println("===ORDER EDIT===");
    }

    public void createOrderBanner() {
        System.out.println("===CREATE AN ORDER===");
    }

    public void removeBanner() {
        System.out.println("===REMOVE ORDER===");
    }

    public void removeSucessBanner() {
        System.out.println("Your Order Has Been Successfully Removed From the System.");
    }

    public void editSuccessBanner() {
        System.out.println("Your Order Has Been Successfully Updated.");
    }

    public void orderNotSavedBanner() {
        System.out.println("Order not Saved, Returning To Main Menu!");
        System.out.println("\n");
    }
}
