package org.kata.sp.domain.discount;

/**
 * This class does not implement a particular promotion
 * We just used it to calculate total
 *
 * @author Ivan
 */
public class PromotionNone implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        return 0;
    }
}