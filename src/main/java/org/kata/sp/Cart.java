package org.kata.sp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {

    private static Map<Product, Integer> cart = new HashMap<>();

    private Cart() {
    }

    public static boolean addProductToCart(String productName, int quantity) {
        boolean hasBeenAdded = false;
        Product product = getProductFromStockIfExist(productName, quantity);

        if (product != null) {
            cart.put(product, quantity);
            hasBeenAdded = true;
        }
        return hasBeenAdded;
    }

    private static Product getProductFromStockIfExist(String productName, int quantity) {
        Product product = null;

        Iterator products = Stock.getAllProductsInStock().entrySet().iterator();
        while (products.hasNext()) {
            Map.Entry<Product, Integer> entry = (Map.Entry) products.next();
            if (productName.equalsIgnoreCase(entry.getKey().getProductName()) && quantity <= entry.getValue()) {
                product = entry.getKey();
                break;
            }
        }
        return product;
    }
}