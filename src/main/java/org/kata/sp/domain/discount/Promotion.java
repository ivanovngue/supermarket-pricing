package org.kata.sp.domain.discount;

/**
 * This interface is used by product as attribute to define promotion
 *
 * @author Ivan
 */
public interface Promotion {
    /**
     * @param price    this can represent unit or pound price of product
     * @param quantity this is the quantity of product from cart
     * @return promotion amount
     */
    float calculatePromotion(float price, Integer quantity);
}