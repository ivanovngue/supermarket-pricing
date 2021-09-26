package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is promotion DTO
 *
 * @author Ivan
 */
@Getter
@AllArgsConstructor
@ToString
public class Promotion {
    private int quantity;
    private double priceQuantity;
    private String productName;
}