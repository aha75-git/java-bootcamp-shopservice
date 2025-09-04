package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public record Order(String orderId, Map<Product, Integer> mapProductQuantity) {

    public BigDecimal getTotalOrderPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for(Map.Entry<Product, Integer> entry : mapProductQuantity.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            totalPrice = totalPrice.add(product.getTotalPrice(quantity));
        }
        return totalPrice;
    }
}
