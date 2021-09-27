package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is product DTO
 *
 * @author Ivan
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Product {
    private String productName;
    private String billingType;
    private double unitPrice;
}