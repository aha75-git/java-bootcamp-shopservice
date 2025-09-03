package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void testOrder() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Buch", BigDecimal.valueOf(3.49)));
        products.add(new Product("2", "Samsung", BigDecimal.valueOf(22.99)));
        products.add(new Product("3", "Sony", BigDecimal.valueOf(45.98)));

        Order order = new Order("1", products);
        assertEquals("1", order.orderId());
        assertEquals("2", order.products().get(1).id());
        assertEquals("Sony", order.products().get(2).name());
    }
}