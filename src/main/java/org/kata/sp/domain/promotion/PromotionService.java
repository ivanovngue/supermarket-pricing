package org.kata.sp.domain.promotion;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is promotion service
 *
 * @author Ivan
 */
public class PromotionService {
    private PromotionService() {
    }

    public static List<PromotionModel> getAllPromotions() {
        List<PromotionModel> promotionModels = new ArrayList<>();
        promotionModels.add(new PromotionModel(3, 3.0f, "A"));
        promotionModels.add(new PromotionModel(3, 2.0f, "B"));
        return promotionModels;
    }
}