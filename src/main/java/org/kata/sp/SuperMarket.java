package org.kata.sp;

/**
 * This is the main class
 *
 * @author Ivan
 */
public class SuperMarket {
    public static void main(String[] args) {
        System.out.println("Supermarket Pricing");

        System.out.println();
        System.out.println("Add product :");
        System.out.println("Product : A, Quantity : 4");
        Cart.addProductToCart("A", 4);
        System.out.println("Product : B, Quantity : 1");
        Cart.addProductToCart("B", 1);
        System.out.println("Product : C, Quantity : 1");
        Cart.addProductToCart("C", 1);

        System.out.println();
        System.out.println("Cart review :");
        Cart.reviewCart();

        System.out.println();
        System.out.println("Promotion review :");
        Cart.reviewPromotion();

        System.out.println();
        System.out.print("Calculation of total cart : ");
        System.out.println(Cart.calculateTotalOfCart());

        System.out.println();
        System.out.println("Empty cart...");
        Cart.emptyCart();

        System.out.println();
        System.out.println("Cart review...");
        Cart.reviewCart();
    }
}