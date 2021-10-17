package org.kata.sp.domain.discount;

/**
 * This class Implements promotion : "buy one, get one for free"
 * The second item have a price
 *
 * @author Ivan
 */
public class PromotionBuyOneGetOneFree implements Promotion {
    @Override
    public float calculatePromotion(float unitPrice, Integer quantity) {
        return 0;
    }
}