package org.kata.sp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to define stock and promotion
 *
 * @author Ivan
 */
public class Stock {

    private Stock() {
    }

    public static Map<Product, Integer> getAllProductsInStock() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("A", "pound", 2.5), 10);
        products.put(new Product("B", "unit", 1.0), 8);
        products.put(new Product("C", "unit", 12.75), 15);
        products.put(new Product("D", "unit", 24.0), 20);
        products.put(new Product("E", "pound", 16.25), 30);
        products.put(new Product("F", "unit", 11.0), 25);
        return products;
    }

    public static List<Promotion> getAllProductPromotions() {
        return null;
    }
}