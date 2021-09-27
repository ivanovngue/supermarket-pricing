package org.kata.sp.domain.product;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is product service
 *
 * @author Ivan
 */
public class ProductService {
    private ProductService() {
    }

    public static Map<ProductModel, Integer> getAllProducts() {
        Map<ProductModel, Integer> products = new HashMap<>();
        products.put(new ProductModel("A", "pound", 2.5), 10);
        products.put(new ProductModel("B", "unit", 1.0), 8);
        products.put(new ProductModel("C", "unit", 12.75), 15);
        products.put(new ProductModel("D", "unit", 24.0), 20);
        products.put(new ProductModel("E", "pound", 16.25), 30);
        products.put(new ProductModel("F", "unit", 11.0), 25);
        return products;
    }
}