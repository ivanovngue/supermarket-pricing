package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "buy two, get one for free"
 * The third item have a price
 *
 * @author Ivan
 */
public class PromotionBuyTwoGetOneFree implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        return 0;
    }
}