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

    public static double calculateTotalOfCart() {
        Iterator<Map.Entry<Product, Integer>> products = shoppingCart.entrySet().iterator();
        double totalOfCart = 0;
        boolean hasPromotion = false;
        // Looping into cart
        while (products.hasNext()) {
            Map.Entry<Product, Integer> entry = products.next();
            int quantityOfProductInCart = entry.getValue();
            double unitPriceOfProductInCart = entry.getKey().getUnitPrice();
            String nameOfProductInCart = entry.getKey().getProductName();
            // For each product, we are looking for its promotion
            for (Promotion promotion : Stock.getAllProductPromotions()) {
                int quantityOfProductInPromotion = promotion.getQuantity();
                double priceOfProductInPromotion = promotion.getPriceQuantity();
                String nameOfProductInPromotion = promotion.getProductName();
                // Promotion exists if true
                if (nameOfProductInPromotion.equalsIgnoreCase(nameOfProductInCart)) {
                    hasPromotion = true;
                    if (quantityOfProductInCart >= quantityOfProductInPromotion && quantityOfProductInCart % quantityOfProductInPromotion == 0) {
                        int occurrenceOfPromotion = quantityOfProductInCart / quantityOfProductInPromotion;
                        totalOfCart = totalOfCart + priceOfProductInPromotion * occurrenceOfPromotion;
                    } else if (quantityOfProductInCart >= quantityOfProductInPromotion && quantityOfProductInCart % quantityOfProductInPromotion != 0) {
                        int occurrenceOfPromotion = quantityOfProductInCart % quantityOfProductInPromotion;
                        totalOfCart = totalOfCart + priceOfProductInPromotion * occurrenceOfPromotion
                                + (quantityOfProductInCart - occurrenceOfPromotion * quantityOfProductInPromotion) * unitPriceOfProductInCart;
                    } else {
                        totalOfCart = totalOfCart + unitPriceOfProductInCart * quantityOfProductInCart;
                    }
                }
            }
            if (!hasPromotion) {
                totalOfCart = totalOfCart + unitPriceOfProductInCart * quantityOfProductInCart;
            }
            hasPromotion = false;
        }
        return totalOfCart;
    }

    public static void reviewCart() {
        shoppingCart.entrySet().forEach(System.out::println);
    }

    public static void reviewPromotion() {
        Stock.getAllProductPromotions().forEach(System.out::println);
    }

    private static Product getProductFromStockIfExist(String productName, int quantity) {
        return getProductFromMapIfExist(Stock.getAllProductsInStock(), productName, quantity);
    }

    private static Product getProductFromCartIfExist(String productName) {
        return getProductFromMapIfExist(shoppingCart, productName, 0);
    }

    private static Product getProductFromMapIfExist(Map<Product, Integer> productList, String productName, int quantity) {
        Product product = null;

        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            if (productName.equalsIgnoreCase(entry.getKey().getProductName()) && quantity <= entry.getValue()) {
                product = entry.getKey();
                break;
            }
        }
        return product;
    }
}