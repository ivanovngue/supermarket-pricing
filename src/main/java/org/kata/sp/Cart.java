package org.kata.sp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is used to cart
 *
 * @author Ivan
 */
public class Cart {

    @Setter
    @Getter
    private static Map<Product, Integer> shoppingCart = new HashMap<>();

    private Cart() {
    }

    public static boolean addProductToCart(String productName, int quantity) {
        boolean hasBeenAdded = false;
        Product product = getProductFromStockIfExist(productName, quantity);

        if (product != null) {
            shoppingCart.put(product, quantity);
            hasBeenAdded = true;
        }
        return hasBeenAdded;
    }

    public static boolean removeProductFromCart(String productName, int quantity) {
        boolean hasBeenRemoved = false;
        Product product = getProductFromCartIfExist(productName);

        if (product != null) {
            if (quantity >= shoppingCart.get(product)) {
                shoppingCart.remove(product);
            } else {
                shoppingCart.put(product, Cart.getShoppingCart().get(product) - quantity);
            }
            hasBeenRemoved = true;
        }
        return hasBeenRemoved;
    }

    public static void emptyCart() {
        shoppingCart.clear();
    }

    private static Product getProductFromStockIfExist(String productName, int quantity) {
        return getProductFromMapIfExist(Stock.getAllProductsInStock(), productName, quantity);
    }

    private static Product getProductFromCartIfExist(String productName) {
        return getProductFromMapIfExist(shoppingCart, productName, 0);
    }

    private static Product getProductFromMapIfExist(Map<Product, Integer> productList, String productName, int quantity) {
        Product product = null;

        Iterator products = productList.entrySet().iterator();
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