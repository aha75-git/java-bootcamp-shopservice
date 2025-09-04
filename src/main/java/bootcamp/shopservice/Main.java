package bootcamp.shopservice;

import bootcamp.shopservice.order.Order;
import bootcamp.shopservice.order.OrderMapRepo;
import bootcamp.shopservice.order.OrderRepo;
import bootcamp.shopservice.product.Product;
import bootcamp.shopservice.product.ProductRepo;
import bootcamp.shopservice.shop.ProductManagementSystem;
import bootcamp.shopservice.shop.ShopService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        OrderRepo orderRepo = new OrderMapRepo();
        ProductRepo productRepo = new ProductRepo();

        Product product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49), 6);
        Product product2 = new Product("2", "Heft", BigDecimal.valueOf(2.99), 15);
        Product product3 = new Product("3", "Sony", BigDecimal.valueOf(45.98), 5);
        Product product4 = new Product("4", "Samsung", BigDecimal.valueOf(34.59), 3);
        Product product5 = new Product("5", "Grundig", BigDecimal.valueOf(44.99), 3);
        Product product6 = new Product("4", "Kugelschreiber", BigDecimal.valueOf(5.99), 22);
        Product product7 = new Product("5", "Tinte", BigDecimal.valueOf(2.99), 13);

        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);
        productRepo.add(product4);
        productRepo.add(product5);
        productRepo.add(product6);
        productRepo.add(product7);

        Map<Product, Integer> mapProductQuantity1 = new HashMap<>();
        Map<Product, Integer> mapProductQuantity2 = new HashMap<>();
        Map<Product, Integer> mapProductQuantity3 = new HashMap<>();

        // Zur Bestellung 1
        mapProductQuantity1.put(product1, 3);
        mapProductQuantity1.put(product2, 1);
        // Zur Bestellung 2
        mapProductQuantity2.put(product3, 2);
        mapProductQuantity2.put(product4, 4);
        mapProductQuantity2.put(product5, 5);
        // Zur Bestellung 3
        mapProductQuantity3.put(product6, 6);
        mapProductQuantity3.put(product7, 7);

        Order order1 = new Order("1", mapProductQuantity1);
        Order order2 = new Order("2", mapProductQuantity2);
        Order order3 = new Order("3", mapProductQuantity3);

        orderRepo.add(order1);
        orderRepo.add(order2);
        orderRepo.add(order3);

        ShopService shopService = new ShopService(orderRepo, productRepo);
        shopService.placeOrder(order1);
        shopService.placeOrder(order2);


        ProductManagementSystem system = new ProductManagementSystem();
        system.start();
    }
}
