package org.kata.sp.domain.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is the test class for promotion "buy two, get one free"
 *
 * @author Ivan
 */
public class TestPromotionBuyTwoGetOneFree {
    @Test
    void testCalculatePromotion() {
        Promotion promotion = new PromotionBuyTwoGetOneFree();
        Assertions.assertEquals(0f, promotion.calculatePromotion(2f, 0));
        Assertions.assertEquals(2f, promotion.calculatePromotion(2f, 1));
        Assertions.assertEquals(4f, promotion.calculatePromotion(2f, 2));
        Assertions.assertEquals(4f, promotion.calculatePromotion(2f, 3));
        Assertions.assertEquals(6f, promotion.calculatePromotion(2f, 4));
    }
}