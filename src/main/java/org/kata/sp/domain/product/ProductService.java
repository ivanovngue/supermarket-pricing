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
        products.put(new ProductModel("A", "pound", 2.5f), 10);
        products.put(new ProductModel("B", "unit", 1.0f), 8);
        products.put(new ProductModel("C", "unit", 12.75f), 15);
        products.put(new ProductModel("D", "unit", 24.0f), 20);
        products.put(new ProductModel("E", "pound", 16.25f), 30);
        products.put(new ProductModel("F", "unit", 11.0f), 25);
        return products;
    }
}