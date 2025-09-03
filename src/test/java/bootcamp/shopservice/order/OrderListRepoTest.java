package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class OrderListRepoTest {
    OrderListRepo  orderListRepo;
    Order order1;
    Order order2;
    Order order3;
    Product product1;
    Product product2;
    Product product3;
    Product product4;
    Product product5;
    Product product6;
    Product product7;

    @BeforeEach
    void setUp() {
        orderListRepo = new OrderListRepo();

        product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49));
        product2 = new Product("2", "Heft", BigDecimal.valueOf(2.99));
        product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98));
        product4 = new Product("4", "Samsung", BigDecimal.valueOf(34.59));
        product5 = new Product("5", "Grundig", BigDecimal.valueOf(44.99));
        product6 = new Product("4", "Kugelschreiben", BigDecimal.valueOf(5.99));
        product7 = new Product("5", "Tinte", BigDecimal.valueOf(2.99));

        order1 = new Order("1", Arrays.asList(product1, product2));
        order2 = new Order("2", Arrays.asList(product3, product4, product5));
        order3 = new Order("3", Arrays.asList(product6, product7));
    }

    @Test
    void testGetOrders_whenCountIsEquals() {
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);
        assertEquals(3, orderListRepo.getOrders().size());
    }

    @Test
    void testIsEmpty_shouldReturnFalse_whenIsNotEmpty() {
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);
        assertFalse( orderListRepo.isEmpty());
    }

    @Test
    void testIsEmpty_shouldReturnTrue_whenIsEmpty() {
        assertTrue(orderListRepo.isEmpty());
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);
        orderListRepo.removeAll();
        assertTrue(orderListRepo.isEmpty());
    }

    @Test
    void testAdd() {
        assertTrue(orderListRepo.add(order1));
        assertTrue(orderListRepo.add(order2));
        assertTrue(orderListRepo.add(order3));
    }

    @Test
    void testGetOrder() {
        String orderId = order2.orderId();
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);
        assertEquals(orderId, orderListRepo.getOrder(orderId).orderId());
    }

    @Test
    void testRemove() {
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);

        assertEquals(3, orderListRepo.getOrders().size());
        assertTrue(orderListRepo.remove(order1));
        assertTrue(orderListRepo.remove(order3));
        assertEquals(1, orderListRepo.getOrders().size());
        assertEquals(order2.orderId(), orderListRepo.getOrder(order2.orderId()).orderId());
    }

    @Test
    void testRemoveByOrderId() {
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);

        assertEquals(3, orderListRepo.getOrders().size());
        assertTrue(orderListRepo.remove(order1.orderId()));
        assertTrue(orderListRepo.remove(order3.orderId()));
        assertEquals(1, orderListRepo.getOrders().size());
        assertEquals(order2.orderId(), orderListRepo.getOrder(order2.orderId()).orderId());
    }

    @Test
    void removeAll() {
        orderListRepo.add(order1);
        orderListRepo.add(order2);
        orderListRepo.add(order3);

        assertEquals(3, orderListRepo.getOrders().size());
        orderListRepo.removeAll();
        assertTrue(orderListRepo.isEmpty());
    }
}