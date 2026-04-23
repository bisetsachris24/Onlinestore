package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {

    public static List<Product> loadProducts(String fileName) {
        List<Product> products = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // skip header row
                }

                String[] parts = line.split("\\|");

                String sku        = parts[0].trim();
                String name       = parts[1].trim();
                double price      = Double.parseDouble(parts[2].trim());
                String department = parts[3].trim();

                products.add(new Product(sku, name, price, department));
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
        }

        return products;
    }
}
