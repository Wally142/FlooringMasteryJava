package com.sg.week4flooringmastery.dao;


import com.sg.week4flooringmastery.dto.Tax;
import java.util.List;

 public interface FlooringMasteryTaxDao {

    List<Tax> loadTaxRates() throws Exception;
}
