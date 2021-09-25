package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Product {
    private String productName;
    private String billingType;
    private double unitPrice;
}