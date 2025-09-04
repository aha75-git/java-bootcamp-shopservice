package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void testOrder() {
        Map<Product, Integer> mapProductQuantity = new HashMap<>();
        Product product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49), 5);
        Product product2 = new Product("2", "Samsung", BigDecimal.valueOf(22.99), 6);
        Product product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98), 4);
        //products.add(product1);
        //products.add(product2);
        //products.add(product3);
        mapProductQuantity.put(product1, 3);
        mapProductQuantity.put(product2, 1);
        mapProductQuantity.put(product3, 2);

        Order order = new Order("1", mapProductQuantity);
        assertEquals("1", order.orderId());
        assertEquals(1, order.mapProductQuantity().get(product2));
    }

    @Test
    void testGetTotalOrderPrice() {
        Map<Product, Integer> mapProductQuantity = new HashMap<>();
        Product product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49), 5);
        Product product2 = new Product("2", "Samsung", BigDecimal.valueOf(22.99), 6);
        Product product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98), 4);
        mapProductQuantity.put(product1, 3);
        mapProductQuantity.put(product2, 1);
        mapProductQuantity.put(product3, 2);

        Order order = new Order("1", mapProductQuantity);
        assertEquals("125.42", order.getTotalOrderPrice().toString());
    }
}