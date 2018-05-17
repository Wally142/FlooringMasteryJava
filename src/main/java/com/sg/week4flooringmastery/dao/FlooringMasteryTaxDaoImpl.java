package com.sg.week4flooringmastery.dao;

import com.sg.week4flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    private final String ITEM_FILE = "Taxes.txt";
    private final String DELIMITER = "::";

    List<Tax> taxes = new ArrayList<>();

    @Override
    public List<Tax> loadTaxRates() throws Exception {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception(
                    "-_- Could not Find Products.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            if (currentTokens.length
                    == 2) {
                Tax currentTax = new Tax();

                BigDecimal cost = new BigDecimal(currentTokens[1]);

                currentTax.setState(currentTokens[0]);
                currentTax.setTaxRate(cost);

                taxes.add(currentTax);
            }

        }

        scanner.close();
        return taxes;
    }

}
