package bootcamp.shopservice.product;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal price, int stockQuantity) {
    public BigDecimal getTotalPrice(int orderedQuantity) {
        return price.multiply(BigDecimal.valueOf(orderedQuantity));
    }
}
