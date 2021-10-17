package org.kata.sp.domain.discount;

/**
 * This interface is used by product as attribute to define discount
 *
 * @author Ivan
 */
public interface Promotion {
    float calculatePromotion(float unitPrice, Integer quantity);
}