package bootcamp.shopservice.shop;

import bootcamp.shopservice.order.Order;
import bootcamp.shopservice.order.OrderMapRepo;
import bootcamp.shopservice.order.OrderRepo;
import bootcamp.shopservice.product.Product;
import bootcamp.shopservice.product.ProductRepo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void testPlaceOrder_shouldReturnTrue_whenPlaceOrderIsOk() {
        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo productRepo = new ProductRepo();

        Product product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49));
        Product product2 = new Product("2", "Heft", BigDecimal.valueOf(2.99));
        Product product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98));
        Product product4 = new Product("4", "Samsung", BigDecimal.valueOf(34.59));
        Product product5 = new Product("5", "Grundig", BigDecimal.valueOf(44.99));
        Product product6 = new Product("4", "Kugelschreiben", BigDecimal.valueOf(5.99));
        Product product7 = new Product("5", "Tinte", BigDecimal.valueOf(2.99));

        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);
        productRepo.add(product4);
        productRepo.add(product5);
        productRepo.add(product6);
        productRepo.add(product7);

        Order order1 = new Order("1", Arrays.asList(product1, product2));
        Order order2 = new Order("2", Arrays.asList(product3, product4, product5));
        Order order3 = new Order("3", Arrays.asList(product6, product7));

        ShopService shopService = new ShopService(orderRepo, productRepo);
        assertTrue(shopService.placeOrder(order1));
        assertTrue(shopService.placeOrder(order2));
        assertTrue(shopService.placeOrder(order3));

    }

    @Test
    void testPlaceOrder_shouldReturnFalse_whenPlaceOrderIsNotOk() {
        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo productRepo = new ProductRepo();

        Product product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49));
        Product product2 = new Product("2", "Heft", BigDecimal.valueOf(2.99));
        Product product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98));
        Product product4 = new Product("4", "Samsung", BigDecimal.valueOf(34.59));

        productRepo.add(product1);
        productRepo.add(product2);

        Order order = new Order("3", Arrays.asList(product3, product4));

        ShopService shopService = new ShopService(orderRepo, productRepo);
        assertFalse(shopService.placeOrder(order));
    }
}