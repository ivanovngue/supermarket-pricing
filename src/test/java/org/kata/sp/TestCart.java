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
        stock.when(Stock::getAllProductsInStock).thenReturn(getProductsInStock());
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

    private static Map<Product, Integer> getProductsInStock() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("A", "pound", 2.5), 10);
        products.put(new Product("B", "unit", 1.0), 8);
        return products;
    }

    private static List<Promotion> getPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion(5, 3.0, "A"));
        promotions.add(new Promotion(3, 2.0, "B"));
        return promotions;
    }
}