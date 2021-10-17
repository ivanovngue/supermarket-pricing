package org.kata.sp.domain.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is the test class for promotion "three for a dollar"
 *
 * @author Ivan
 */
public class TestPromotionBuyThreeForADollar {
    @Test
    void testCalculatePromotion() {
        Promotion promotion = new PromotionBuyThreeForADollar();
        Assertions.assertEquals(0f, promotion.calculatePromotion(2f, 0));
        Assertions.assertEquals(2f, promotion.calculatePromotion(2f, 1));
        Assertions.assertEquals(4f, promotion.calculatePromotion(2f, 2));
        Assertions.assertEquals(1f, promotion.calculatePromotion(2f, 3));
        Assertions.assertEquals(3f, promotion.calculatePromotion(2f, 4));
        Assertions.assertEquals(5f, promotion.calculatePromotion(2f, 5));
        Assertions.assertEquals(2f, promotion.calculatePromotion(2f, 6));
    }
}