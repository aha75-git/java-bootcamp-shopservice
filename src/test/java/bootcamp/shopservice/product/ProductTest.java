package bootcamp.shopservice.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    public void testProduct() {
        Product product = new Product("1", "Buch", BigDecimal.valueOf(3.49), 5);
        assertEquals("1", product.id());
        assertEquals("Buch", product.name());
        assertEquals("3.49", product.price().toString());
        assertEquals(5, product.stockQuantity());
    }

    @Test
    public void testGetTotalPrice() {
        Product product = new Product("1", "Buch", BigDecimal.valueOf(3.49), 5);
        BigDecimal totalPrice = product.getTotalPrice(5);
        assertEquals("17.45", totalPrice.toString());
    }
}