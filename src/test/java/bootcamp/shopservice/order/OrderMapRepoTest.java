package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {
    OrderMapRepo orderMapRepo;
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
        orderMapRepo = new OrderMapRepo();

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
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);
        assertEquals(3, orderMapRepo.getOrders().size());
    }

    @Test
    void testIsEmpty_shouldReturnFalse_whenIsNotEmpty() {
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);
        assertFalse( orderMapRepo.isEmpty());
    }

    @Test
    void testIsEmpty_shouldReturnTrue_whenIsEmpty() {
        assertTrue(orderMapRepo.isEmpty());
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);
        orderMapRepo.removeAll();
        assertTrue(orderMapRepo.isEmpty());
    }

    @Test
    void testAdd() {
        assertTrue(orderMapRepo.add(order1));
        assertTrue(orderMapRepo.add(order2));
        assertTrue(orderMapRepo.add(order3));
    }

    @Test
    void testGetOrder() {
        String orderId = order2.orderId();
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);
        assertEquals(orderId, orderMapRepo.getOrder(orderId).orderId());
    }

    @Test
    void testRemove() {
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);

        assertEquals(3, orderMapRepo.getOrders().size());
        assertTrue(orderMapRepo.remove(order1));
        assertTrue(orderMapRepo.remove(order3));
        assertEquals(1, orderMapRepo.getOrders().size());
        assertEquals(order2.orderId(), orderMapRepo.getOrder(order2.orderId()).orderId());
    }

    @Test
    void testRemoveByOrderId() {
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);

        assertEquals(3, orderMapRepo.getOrders().size());
        assertTrue(orderMapRepo.remove(order1.orderId()));
        assertTrue(orderMapRepo.remove(order3.orderId()));
        assertEquals(1, orderMapRepo.getOrders().size());
        assertEquals(order2.orderId(), orderMapRepo.getOrder(order2.orderId()).orderId());
    }

    @Test
    void removeAll() {
        orderMapRepo.add(order1);
        orderMapRepo.add(order2);
        orderMapRepo.add(order3);

        assertEquals(3, orderMapRepo.getOrders().size());
        orderMapRepo.removeAll();
        assertTrue(orderMapRepo.isEmpty());
    }
}