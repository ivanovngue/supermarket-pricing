package org.kata.sp.application;

import org.kata.sp.domain.cart.CartService;

import static org.kata.sp.domain.promotion.PromotionService.getAllPromotions;

/**
 * This is the main class and user side
 *
 * @author Ivan
 */
public class SuperMarket {
    public static void main(String[] args) {
        System.out.println("SUPERMARKET PRICING");

        System.out.println();
        System.out.println("Add product");
        System.out.println("Product : A, Quantity : 4");
        CartService.addProductToCart("A", 4);
        System.out.println("Product : B, Quantity : 2");
        CartService.addProductToCart("B", 2);
        System.out.println("Product : C, Quantity : 3");
        CartService.addProductToCart("C", 3);
        System.out.println("Product : P, Quantity : 4");
        CartService.addProductToCart("P", 4);

        System.out.println();
        System.out.println("Cart review");
        CartService.getShoppingCart().entrySet().forEach(System.out::println);

        System.out.println();
        System.out.println("Promotion review");
        getAllPromotions().forEach(System.out::println);

        System.out.println();
        System.out.print("Calculation of total cart : ");
        System.out.println(CartService.calculateTotalOfCart());

        System.out.println();
        System.out.println("Checkout in progress...");
        CartService.emptyCart();
    }
}