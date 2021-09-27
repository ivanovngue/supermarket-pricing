package org.kata.sp.domain.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is promotion model
 *
 * @author Ivan
 */
@Getter
@ToString
@AllArgsConstructor
public class PromotionModel {
    private int quantity;
    private double priceQuantity;
    private String productName;
}