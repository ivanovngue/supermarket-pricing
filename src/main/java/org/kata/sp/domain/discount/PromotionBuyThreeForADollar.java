package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "three for a dollar"
 * If I buy 4 then I will buy 1 in normal price
 *
 * @author Ivan
 */
public class PromotionBuyThreeForADollar implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        float totalPromotion = 0f;
        int occurrenceOfPromotion = quantity % 3;
        if (quantity >= 3) {
            if (occurrenceOfPromotion == 0) {
                totalPromotion = quantity / 3;
            }
            if (occurrenceOfPromotion > 0) {
                totalPromotion = (quantity / 3) + (occurrenceOfPromotion * unitPrice);
            }
        } else {
            totalPromotion = quantity * unitPrice;
        }
        return totalPromotion;
    }
}