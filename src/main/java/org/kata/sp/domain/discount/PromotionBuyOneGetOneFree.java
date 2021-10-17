package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "buy one, get one for free"
 * Buy two and the second one is free
 *
 * @author Ivan
 */
public class PromotionBuyOneGetOneFree implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        float totalPromotion = 0f;
        int occurrenceOfPromotion = quantity % 2;
        if (quantity >= 2) {
            // quantity is a multiple of two
            if (occurrenceOfPromotion == 0) {
                totalPromotion = (quantity / 2) * unitPrice;
            }
            // quantity is not a multiple of two
            if (occurrenceOfPromotion > 0) {
                totalPromotion = (((quantity - 1) / 2) * unitPrice) + unitPrice;
            }
        } else {
            totalPromotion = quantity * unitPrice;
        }
        return totalPromotion;
    }
}