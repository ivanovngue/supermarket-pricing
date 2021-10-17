package org.kata.sp.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.kata.sp.domain.discount.Promotion;

/**
 * This class is product model
 *
 * @author Ivan
 */
@Getter
@ToString
@AllArgsConstructor
public class ProductModel {
    private String productName;
    private String billingType;
    private float unitPrice;
    private Promotion promotion;
}