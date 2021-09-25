package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class is promotion DTO
 *
 * @author Ivan
 */
@Getter
@AllArgsConstructor
public class Promotion {
    private int quantity;
    private double priceQuantity;
    private String productName;
}