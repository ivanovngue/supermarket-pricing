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
        return 0;
    }
}