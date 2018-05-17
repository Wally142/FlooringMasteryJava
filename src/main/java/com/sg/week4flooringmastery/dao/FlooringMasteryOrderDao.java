
package com.sg.week4flooringmastery.dao;

import com.sg.week4flooringmastery.dto.Order;
import java.util.List;
import java.time.LocalDate;

public interface FlooringMasteryOrderDao {
    
    Order addOrder(Order order);

    List<Order> searchOrders(LocalDate date);
    
    void removeOrder(LocalDate date, int orderNumber);
    
    void editOrder (LocalDate date);
    
    Order getOrder(List<Order> orderList, int orderNumber);
    
}
