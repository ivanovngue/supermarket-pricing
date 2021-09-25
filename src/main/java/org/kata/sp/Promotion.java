package org.kata.sp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Promotion {
    private int quantity;
    private double priceQuantity;
    private String productName;
}