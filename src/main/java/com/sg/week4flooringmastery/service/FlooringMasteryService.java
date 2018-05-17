package com.sg.week4flooringmastery.service;

import com.sg.week4flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.week4flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.week4flooringmastery.dao.FlooringMasteryTaxDao;
import com.sg.week4flooringmastery.dto.Order;
import com.sg.week4flooringmastery.dto.Product;
import com.sg.week4flooringmastery.dto.Tax;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryService {

    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryTaxDao taxDao;

    public FlooringMasteryService(FlooringMasteryOrderDao dao1, FlooringMasteryProductDao dao2,
            FlooringMasteryTaxDao dao3) {
        this.orderDao = dao1;
        this.productDao = dao2;
        this.taxDao = dao3;
    }

    public Order addOrder(Order order) throws Exception {
        if (order.getCustomerName().equals("")) {
            throw new Exception("You must Enter a Customer Name!");
        } else {
            orderDao.addOrder(order);
        }
        return order;
    }

    public void removeOrder(LocalDate date, int orderNumber) throws Exception {
        orderDao.removeOrder(date, orderNumber);
    }

    public List<Order> searchOrders(LocalDate date) {
        return orderDao.searchOrders(date);
    }

    public Order getOrder(List<Order> orderList, int orderNumber) {
        return orderDao.getOrder(orderList, orderNumber);
    }

    public void editOrder(LocalDate date, Order order) throws Exception {
        if (order.getCustomerName().equals("")) {
            throw new Exception("You must Enter a Customer Name!");
        } else {
            orderDao.editOrder(date);
        }
    }

    public Order calculateCost(Order order) throws Exception {

        BigDecimal taxRate = new BigDecimal(0);
        BigDecimal area = order.getArea();
        List<Tax> taxes = taxDao.loadTaxRates();
        List<Product> products = productDao.loadProductList();

        if (area.compareTo(BigDecimal.ZERO) >= 0) {

            for (Tax t : taxes) {
                if (t.getState().equals(order.getState())) {
                    taxRate = t.getTaxRate();
                }
            }

            for (Product p : products) {

                if (p.getProductType().equals(order.getProductType())) {

                    BigDecimal costSqFt = (p.getCostPerSqFt());
                    BigDecimal laborCostSqFt = (p.getLaborCostPerSqFt());

                    BigDecimal material = area.multiply(costSqFt);
                    BigDecimal materialCost = material.setScale(2, HALF_UP);

                    BigDecimal labor = area.multiply(laborCostSqFt);
                    BigDecimal laborCost = labor.setScale(2, HALF_UP);

                    BigDecimal subTotal = materialCost.add(laborCost);

                    BigDecimal tax = subTotal.multiply(taxRate);
                    BigDecimal taxCost = tax.setScale(2, HALF_UP);

                    BigDecimal totalCost = subTotal.add(taxCost);

                    order.setTaxRate(taxRate);
                    order.setCostPerSqFt(costSqFt);
                    order.setLaborCostPerSqFt(laborCostSqFt);
                    order.setMaterialCost(materialCost);
                    order.setLaborCost(laborCost);
                    order.setTotalTax(taxCost);
                    order.setTotalCost(totalCost);
                }
            }
        } else {
            throw new Exception("Area Must be Larger than Zero!");
        }

        return order;
    }
}
