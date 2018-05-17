package com.sg.week4flooringmastery.dao;

import com.sg.week4flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringMasteryOrderDaoTraining extends FlooringMasteryOrderDaoImpl {

    public static final String DELIMITER = "::";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final String header = ("OrderNumber,CustomerName,State,TaxRate,"
            + "ProductType,Area,CostPerSquareFoot,LaborCost,PerSquareFoot,MaterialCost,LaborCost,Tax,Total");

    private List<Order> orders = new ArrayList<>();

    @Override
    public Order addOrder(Order order) {
        try {
            for (Order id : orders) {
                if (id.getOrderNumber() == order.getOrderNumber()) {
                    order.setOrderNumber(id.getOrderNumber() + 1);
                }
            }
            LocalDate date = order.getTimeStamp();
            orders.add(order);
            System.out.println("Program is In Training Mode, Nothing was Saved!");
        } catch (Exception ex) {
            System.out.println("Couldnt Write Order " + ex);
        }
        return order;
    }

    @Override
    public List<Order> searchOrders(LocalDate date) {
        try {
            readOrder(date);
        } catch (Exception ex) {
            System.out.println("Could Not Find Orders");

        }
        return new ArrayList<>(orders);
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) {

        try {
            List<Order> currentOrder = readOrder(date);
            for (Order order : currentOrder) {
                if (orderNumber == order.getOrderNumber()) {
                    currentOrder.remove(order);
                    System.out.println("Program is In Training Mode, Nothing was Saved!");
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Order Not Removed");
        }
    }

    @Override
    public void editOrder(LocalDate date) {

        try {
            System.out.println("Program is In Training Mode, Nothing was Saved!");
        } catch (Exception ex) {
            System.out.println("Order Not Updated!");
        }
    }

    private List<Order> readOrder(LocalDate date) throws Exception {

        String ITEM_FILE = "order_" + date;

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception(
                    "-_- Could not Load Orders.", e);
        }
        String currentLine;

        String[] currentTokens;

        orders.clear();

        if (orders.isEmpty()) {
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);

                if (currentTokens.length == 13) {

                    Order currentOrder = new Order();

                    int id = Integer.parseInt(currentTokens[0]);
                    BigDecimal rate = new BigDecimal(currentTokens[3]);
                    BigDecimal area = new BigDecimal(currentTokens[5]);
                    BigDecimal costSQ = new BigDecimal(currentTokens[6]);
                    BigDecimal costLabSQ = new BigDecimal(currentTokens[7]);
                    BigDecimal material = new BigDecimal(currentTokens[8]);
                    BigDecimal labor = new BigDecimal(currentTokens[9]);
                    BigDecimal tax = new BigDecimal(currentTokens[10]);
                    BigDecimal total = new BigDecimal(currentTokens[11]);
                    date = LocalDate.parse(currentTokens[12], formatter);

                    currentOrder.setOrderNumber(id);
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setState(currentTokens[2]);
                    currentOrder.setTaxRate(rate);
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(area);
                    currentOrder.setCostPerSqFt(costSQ);
                    currentOrder.setLaborCostPerSqFt(costLabSQ);
                    currentOrder.setMaterialCost(material);
                    currentOrder.setLaborCost(labor);
                    currentOrder.setTotalTax(tax);
                    currentOrder.setTotalCost(total);
                    currentOrder.setTimeStamp(date);

                    orders.add(currentOrder);
                }
            }
        }
        scanner.close();
        return orders;
    }
}
