package org.kata.sp.domain.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is the test class for promotion "Buy one, get one free"
 *
 * @author Ivan
 */
public class TestPromotionBuyOneGetOneFree {
    @Test
    void testCalculatePromotion() {
        Promotion promotion = new PromotionBuyOneGetOneFree();
        Assertions.assertEquals(0f, promotion.calculatePromotion(2f, 0));
        Assertions.assertEquals(2f, promotion.calculatePromotion(2f, 1));
        Assertions.assertEquals(2f, promotion.calculatePromotion(2f, 2));
        Assertions.assertEquals(4f, promotion.calculatePromotion(2f, 3));
        Assertions.assertEquals(4f, promotion.calculatePromotion(2f, 4));
    }
}