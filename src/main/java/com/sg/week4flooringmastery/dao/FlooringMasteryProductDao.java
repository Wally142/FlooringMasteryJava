
package com.sg.week4flooringmastery.dao;

import com.sg.week4flooringmastery.dto.Product;

import java.util.List;

public interface FlooringMasteryProductDao {
    
 List<Product> loadProductList() throws Exception; 
    
}
