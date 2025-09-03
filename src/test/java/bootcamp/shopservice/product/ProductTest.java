package bootcamp.shopservice.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    public void testProduct() {
        Product product = new Product("1", "Buch", BigDecimal.valueOf(3.49));
        assertEquals("1", product.id());
        assertEquals("Buch", product.name());
        assertEquals("3.49", product.price().toString());
    }
}