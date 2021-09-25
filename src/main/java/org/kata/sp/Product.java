package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class is product DTO
 *
 * @author Ivan
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Product {
    private String productName;
    private String billingType;
    private double unitPrice;
}