package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "buy two, get one for free"
 * The third item has a price
 *
 * @author Ivan
 */
public class PromotionBuyTwoGetOneFree implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        float totalPromotion = 0f;
        int occurrenceOfPromotion = quantity % 3;
        if (quantity >= 3) {
            if (occurrenceOfPromotion == 0) {
                totalPromotion = (quantity / 3) * 2 * unitPrice;
            }
            if (occurrenceOfPromotion > 0) {
                totalPromotion = ((quantity / 3) * 2 * unitPrice) + unitPrice;
            }
        } else {
            totalPromotion = quantity * unitPrice;
        }
        return totalPromotion;
    }
}