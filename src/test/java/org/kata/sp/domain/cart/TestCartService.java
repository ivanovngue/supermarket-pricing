package org.kata.sp.domain.cart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kata.sp.domain.discount.PromotionBuyOneGetOneFree;
import org.kata.sp.domain.discount.PromotionNone;
import org.kata.sp.domain.product.ProductModel;
import org.kata.sp.domain.product.ProductService;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the test class for cart service
 *
 * @author Ivan
 */
class TestCartService {

    private static MockedStatic<ProductService> productService = Mockito.mockStatic(ProductService.class);

    @BeforeAll
    public static void init() {
        productService.when(ProductService::getAllProducts).thenReturn(getProducts());
    }

    @AfterEach
    public void cleanUpEach() {
        CartService.getShoppingCart().clear();
    }

    @Test
    void testWhenAddingProductToCart_thenProductIsCorrectlyAdded() {
        // check size of cart before add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
        // add product
        Assertions.assertTrue(CartService.addProductToCart("A", 2));
        // check size of cart after add product
        Assertions.assertEquals(1, CartService.getShoppingCart().size());
        // check quantity of product added
        Assertions.assertEquals(2, getQuantityOfProductInCart("A"));
    }

    @Test
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseInsufficientStock() {
        // check size of cart before add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
        // add product
        Assertions.assertFalse(CartService.addProductToCart("A", 11));
        // check size of cart after add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    void testWhenAddingProductToCart_thenProductIsNotAdded_becauseDoesNotExist() {
        // check size of cart before add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
        // add product
        Assertions.assertFalse(CartService.addProductToCart("C", 2));
        // check size of cart after add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    void testWhenAddingNoProductToCart_thenProductIsNotAdded_becauseQuantityIsEqualZero() {
        // check size of cart before add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
        // add product
        Assertions.assertFalse(CartService.addProductToCart("A", 0));
        // check size of cart after add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    void testWhenAddingEmptyProductToCart_thenProductIsNotAdded_becauseDoesNotExist() {
        // check size of cart before add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
        // add product
        Assertions.assertFalse(CartService.addProductToCart("", 2));
        // check size of cart after add product
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    void testWhenRemoveProductFromCart_thenProductIsRemoved_becauseItIsExist() {
        // add products to cart
        CartService.setShoppingCart(getProducts());
        int quantityOfProduct_A_BeforeRemoving = getQuantityOfProductInCart("A");
        int quantityOfProduct_B_BeforeRemoving = getQuantityOfProductInCart("B");
        // check if removing is done
        Assertions.assertTrue(CartService.removeProductFromCart("A", 1));
        // check quantity of product A after removing
        Assertions.assertEquals(quantityOfProduct_A_BeforeRemoving - 1, getQuantityOfProductInCart("A"));
        // check quantity of product B after removing
        Assertions.assertEquals(quantityOfProduct_B_BeforeRemoving, getQuantityOfProductInCart("B"));
    }

    @Test
    void testWhenRemoveProductFromCart_thenProductIsNotRemoved_becauseDoesNotExist() {
        // add products to cart
        CartService.setShoppingCart(getProducts());
        int quantityOfProduct_A_BeforeRemoving = getQuantityOfProductInCart("A");
        int quantityOfProduct_B_BeforeRemoving = getQuantityOfProductInCart("B");
        // check if removing is done
        Assertions.assertFalse(CartService.removeProductFromCart("X", 1));
        // check quantity of product A after removing
        Assertions.assertEquals(quantityOfProduct_A_BeforeRemoving, getQuantityOfProductInCart("A"));
        // check quantity of product B after removing
        Assertions.assertEquals(quantityOfProduct_B_BeforeRemoving, getQuantityOfProductInCart("B"));
    }

    @Test
    void testWhenEmptyCart() {
        // add products to cart
        CartService.setShoppingCart(getProducts());
        // check size before empty cart
        Assertions.assertTrue(CartService.getShoppingCart().size() > 0);
        CartService.emptyCart();
        // check size after empty cart
        Assertions.assertEquals(0, CartService.getShoppingCart().size());
    }

    @Test
    void testWhenCalculateTotalCartPrice() {
        // add products to cart
        CartService.setShoppingCart(getProducts());
        // check total of cart
        Assertions.assertEquals(31, CartService.calculateTotalOfCart());
    }

    private static Map<ProductModel, Integer> getProducts() {
        Map<ProductModel, Integer> products = new HashMap<>();
        products.put(new ProductModel("A", "pound", 2.5f, new PromotionNone()), 10);
        products.put(new ProductModel("B", "unit", 1.5f, new PromotionBuyOneGetOneFree()), 8);
        return products;
    }

    private static int getQuantityOfProductInCart(String productName) {
        int quantityTest = 0;
        for (Map.Entry<ProductModel, Integer> entry : CartService.getShoppingCart().entrySet()) {
            if (entry.getKey().getProductName().equalsIgnoreCase(productName)) {
                quantityTest = entry.getValue();
            }
        }
        return quantityTest;
    }
}