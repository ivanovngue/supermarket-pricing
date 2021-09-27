package org.kata.sp.domain.cart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kata.sp.domain.product.ProductModel;
import org.kata.sp.domain.product.ProductService;
import org.kata.sp.domain.promotion.PromotionModel;
import org.kata.sp.domain.promotion.PromotionService;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the test class for cart service
 *
 * @author Ivan
 */
class TestCartService {

    private static MockedStatic<ProductService> productService = Mockito.mockStatic(ProductService.class);
    private static MockedStatic<PromotionService> promotionService = Mockito.mockStatic(PromotionService.class);

    @BeforeAll
    public static void init() {
        productService.when(ProductService::getAllProducts).thenReturn(getProducts());
        promotionService.when(PromotionService::getAllPromotions).thenReturn(getPromotions());
    }

    @Test
    @DisplayName("Should return true because, product 'A' exists in stock with sufficient quantity.")
    void testWhenAddingProductToCart_thenProductIsCorrectlyAdded() {
        Assertions.assertTrue(CartService.addProductToCart("A", 2));
    }

    @Test
    @DisplayName("Should return false because, product 'A' exists in stock, with insufficient quantity.")
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseInsufficientStock() {
        Assertions.assertFalse(CartService.addProductToCart("A", 11));
    }

    @Test
    @DisplayName("Should return false because, product 'C' doesn't exist in stock.")
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseDoesNotExist() {
        Assertions.assertFalse(CartService.addProductToCart("C", 2));
    }

    @Test
    @DisplayName("Should return true because, product 'A' exists in cart.")
    void testWhenRemoveProductFromCart_thenProductIsRemoved_becauseItIsExist() {
        CartService.setShoppingCart(getProducts());
        Assertions.assertTrue(CartService.removeProductFromCart("A", 1));
    }

    @Test
    @DisplayName("Should return false because, product 'X' doesn't exist in cart.")
    void testWhenRemoveProductFromCart_thenProductIsNotRemoved_becauseDoesNotExist() {
        CartService.setShoppingCart(getProducts());
        Assertions.assertFalse(CartService.removeProductFromCart("X", 1));
    }

    @Test
    @DisplayName("Should return empty shopping cart.")
    void testWhenEmptyCart() {
        CartService.setShoppingCart(getProducts());
        Assertions.assertTrue(CartService.getShoppingCart().size() > 0);
        CartService.emptyCart();
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    @DisplayName("Should return total of shopping cart.")
    void testWhenCalculateTotalCartPrice() {
        CartService.setShoppingCart(getProducts());
        Assertions.assertEquals(23, CartService.calculateTotalOfCart());
    }

    private static Map<ProductModel, Integer> getProducts() {
        Map<ProductModel, Integer> products = new HashMap<>();
        products.put(new ProductModel("A", "pound", 2.5), 10);
        products.put(new ProductModel("B", "unit", 1.5), 8);
        return products;
    }

    private static List<PromotionModel> getPromotions() {
        List<PromotionModel> promotionModels = new ArrayList<>();
        promotionModels.add(new PromotionModel(4, 3.0, "A"));
        return promotionModels;
    }
}