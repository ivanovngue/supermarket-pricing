package org.kata.sp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the test class for Cart class
 *
 * @author Ivan
 */
class TestCart {

    private static MockedStatic<Stock> stock = Mockito.mockStatic(Stock.class);

    @BeforeAll
    public static void init() {
        stock.when(Stock::getAllProductsInStock).thenReturn(getProducts());
        stock.when(Stock::getAllProductPromotions).thenReturn(getPromotions());
    }

    @Test
    @DisplayName("Should return true because, product 'A' exists in stock with sufficient quantity.")
    void testWhenAddingProductToCart_thenProductIsCorrectlyAdded() {
        Assertions.assertTrue(Cart.addProductToCart("A", 2));
    }

    @Test
    @DisplayName("Should return false because, product 'A' exists in stock, with insufficient quantity.")
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseInsufficientStock() {
        Assertions.assertFalse(Cart.addProductToCart("A", 11));
    }

    @Test
    @DisplayName("Should return false because, product 'C' doesn't exist in stock.")
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseDoesNotExist() {
        Assertions.assertFalse(Cart.addProductToCart("C", 2));
    }

    @Test
    @DisplayName("Should return true because, product 'A' exists in cart.")
    void testWhenRemoveProductFromCart_thenProductIsRemoved_becauseItIsExist() {
        Cart.setShoppingCart(getProducts());
        Assertions.assertTrue(Cart.removeProductFromCart("A", 1));
    }

    @Test
    @DisplayName("Should return false because, product 'X' doesn't exist in cart.")
    void testWhenRemoveProductFromCart_thenProductIsNotRemoved_becauseDoesNotExist() {
        Cart.setShoppingCart(getProducts());
        Assertions.assertFalse(Cart.removeProductFromCart("X", 1));
    }

    @Test
    @DisplayName("Should return empty shopping cart.")
    void testWhenEmptyCart() {
        Cart.setShoppingCart(getProducts());
        Assertions.assertTrue(Cart.getShoppingCart().size() > 0);
        Cart.emptyCart();
        Assertions.assertEquals(0, Cart.getShoppingCart().size());
    }

    @Test
    @DisplayName("Should return total of shopping cart.")
    void testWhenCalculateTotalCartPrice() {
        Cart.setShoppingCart(getProducts());
        Assertions.assertEquals(23, Cart.calculateTotalOfCart());
    }

    private static Map<Product, Integer> getProducts() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("A", "pound", 2.5), 10);
        products.put(new Product("B", "unit", 1.5), 8);
        return products;
    }

    private static List<Promotion> getPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion(4, 3.0, "A"));
        return promotions;
    }
}