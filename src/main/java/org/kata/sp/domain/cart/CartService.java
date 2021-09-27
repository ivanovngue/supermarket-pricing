package org.kata.sp.domain.cart;

import lombok.Getter;
import lombok.Setter;
import org.kata.sp.domain.product.ProductModel;
import org.kata.sp.domain.promotion.PromotionModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.kata.sp.domain.product.ProductService.getAllProducts;
import static org.kata.sp.domain.promotion.PromotionService.getAllPromotions;

/**
 * This class is used to manage the cart
 *
 * @author Ivan
 */
public class CartService {

    @Setter
    @Getter
    private static Map<ProductModel, Integer> shoppingCart = new HashMap<>();

    private CartService() {
    }

    public static boolean addProductToCart(String productName, int quantity) {
        boolean hasBeenAdded = false;
        ProductModel productModel = getProductFromStockIfExist(productName, quantity);

        if (productModel != null) {
            shoppingCart.put(productModel, quantity);
            hasBeenAdded = true;
        }
        return hasBeenAdded;
    }

    public static boolean removeProductFromCart(String productName, int quantity) {
        boolean hasBeenRemoved = false;
        ProductModel productModel = getProductFromCartIfExist(productName);

        if (productModel != null) {
            if (quantity >= shoppingCart.get(productModel)) {
                shoppingCart.remove(productModel);
            } else {
                shoppingCart.put(productModel, CartService.getShoppingCart().get(productModel) - quantity);
            }
            hasBeenRemoved = true;
        }
        return hasBeenRemoved;
    }

    public static void emptyCart() {
        shoppingCart.clear();
    }

    public static float calculateTotalOfCart() {
        Iterator<Map.Entry<ProductModel, Integer>> products = shoppingCart.entrySet().iterator();
        float totalOfCart = 0;
        boolean hasPromotion = false;
        // Looping into cart
        while (products.hasNext()) {
            Map.Entry<ProductModel, Integer> entry = products.next();
            int quantityOfProductInCart = entry.getValue();
            float unitPriceOfProductInCart = entry.getKey().getUnitPrice();
            String nameOfProductInCart = entry.getKey().getProductName();
            // For each product, we are looking for its promotion
            for (PromotionModel promotionModel : getAllPromotions()) {
                int quantityOfProductInPromotion = promotionModel.getQuantity();
                float priceOfProductInPromotion = promotionModel.getPriceQuantity();
                String nameOfProductInPromotion = promotionModel.getProductName();
                // Promotion exists if true
                if (nameOfProductInPromotion.equalsIgnoreCase(nameOfProductInCart)) {
                    hasPromotion = true;
                    if (isPromotionQuantityEqualsProductQuantity(quantityOfProductInCart, quantityOfProductInPromotion)) {
                        int occurrenceOfPromotion = quantityOfProductInCart / quantityOfProductInPromotion;
                        totalOfCart = totalOfCart + priceOfProductInPromotion * occurrenceOfPromotion;
                    } else if (isPromotionQuantityNotEqualsProductQuantity(quantityOfProductInCart, quantityOfProductInPromotion)) {
                        int occurrenceOfPromotion = quantityOfProductInCart % quantityOfProductInPromotion;
                        totalOfCart = totalOfCart + priceOfProductInPromotion * occurrenceOfPromotion
                                + (quantityOfProductInCart - occurrenceOfPromotion * quantityOfProductInPromotion) * unitPriceOfProductInCart;
                    } else {
                        totalOfCart = totalOfCart + unitPriceOfProductInCart * quantityOfProductInCart;
                    }
                }
            }
            if (!hasPromotion) {
                totalOfCart = totalOfCart + unitPriceOfProductInCart * quantityOfProductInCart;
            }
            hasPromotion = false;
        }
        return totalOfCart;
    }

    private static ProductModel getProductFromStockIfExist(String productName, int quantity) {
        return getProductFromMapIfExist(getAllProducts(), productName, quantity);
    }

    private static ProductModel getProductFromCartIfExist(String productName) {
        return getProductFromMapIfExist(shoppingCart, productName, 0);
    }

    private static ProductModel getProductFromMapIfExist(Map<ProductModel, Integer> productList, String productName, int quantity) {
        ProductModel productModel = null;

        for (Map.Entry<ProductModel, Integer> entry : productList.entrySet()) {
            if (productName.equalsIgnoreCase(entry.getKey().getProductName()) && quantity <= entry.getValue()) {
                productModel = entry.getKey();
                break;
            }
        }
        return productModel;
    }

    private static boolean isPromotionQuantityEqualsProductQuantity(int quantityOfProductInCart, int quantityOfProductInPromotion) {
        return quantityOfProductInCart >= quantityOfProductInPromotion && quantityOfProductInCart % quantityOfProductInPromotion == 0;
    }

    private static boolean isPromotionQuantityNotEqualsProductQuantity(int quantityOfProductInCart, int quantityOfProductInPromotion) {
        return quantityOfProductInCart >= quantityOfProductInPromotion && quantityOfProductInCart % quantityOfProductInPromotion != 0;
    }
}