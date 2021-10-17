package org.kata.sp.domain.cart;

import lombok.Getter;
import lombok.Setter;
import org.kata.sp.domain.discount.Promotion;
import org.kata.sp.domain.product.ProductModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.kata.sp.domain.product.ProductService.getAllProducts;

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

        if (productModel != null && quantity > 0) {
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
        float totalOfCart = 0f;
        // Looping into cart
        while (products.hasNext()) {
            Map.Entry<ProductModel, Integer> entry = products.next();
            int quantityOfProductInCart = entry.getValue();
            float priceOfProductInCart = entry.getKey().getUnitPrice();
            Promotion promotion = entry.getKey().getPromotion();

            totalOfCart = totalOfCart + promotion.calculatePromotion(priceOfProductInCart, quantityOfProductInCart);
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
}