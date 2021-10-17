package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "buy two, get one for free"
 * Buy three and the third one is free
 *
 * @author Ivan
 */
public class PromotionBuyTwoGetOneFree implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        float totalPromotion = 0f;
        int occurrenceOfPromotion = quantity % 3;
        if (quantity >= 3) {
            // quantity is a multiple of three
            if (occurrenceOfPromotion == 0) {
                totalPromotion = (quantity / 3) * 2 * unitPrice;
            }
            // quantity is not a multiple of three
            if (occurrenceOfPromotion > 0) {
                totalPromotion = ((quantity / 3) * 2 * unitPrice) + unitPrice;
            }
        } else {
            totalPromotion = quantity * unitPrice;
        }
        return totalPromotion;
    }
}