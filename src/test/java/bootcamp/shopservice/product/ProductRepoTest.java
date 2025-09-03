package bootcamp.shopservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepoTest {
    ProductRepo productRepo;
    Product product1;
    Product product2;
    Product product3;

    @BeforeEach
    void setUp() {
        productRepo = new ProductRepo();
        product1 = new Product("1", "Buch", BigDecimal.valueOf(3.49));
        product2 = new Product("2", "Samsung", BigDecimal.valueOf(22.99));
        product3 = new Product("3", "Samsung", BigDecimal.valueOf(45.98));
    }

    @Test
    void tesGetProducts_whenCountIsEquals() {
        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);
        assertEquals(3, productRepo.getProducts().size());
    }

    @Test
    void testGetProductByIndex() {
        productRepo.add(product1);
        productRepo.add(product2);
        assertEquals(product1.id(), productRepo.getProduct(0).id());
    }

    @Test
    void testGetProductByProductId() {
        String productId = product2.id();
        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);
        assertEquals(product2.id(), productRepo.getProduct(productId).id());
    }

    @Test
    void testAdd() {
        assertTrue(productRepo.add(product1));
        assertTrue(productRepo.add(product2));
        assertTrue(productRepo.add(product3));
    }

    @Test
    void testRemove() {
        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);

        assertEquals(3, productRepo.getProducts().size());
        assertTrue(productRepo.remove(product1));
        assertTrue(productRepo.remove(product3));
        assertEquals(1, productRepo.getProducts().size());
        assertEquals(product2.id(), productRepo.getProduct(product2.id()).id());
    }

    @Test
    void testRemoveByProductId() {
        productRepo.add(product1);
        productRepo.add(product2);
        productRepo.add(product3);

        assertEquals(3, productRepo.getProducts().size());
        assertTrue(productRepo.remove(product1.id()));
        assertTrue(productRepo.remove(product3.id()));
        assertEquals(1, productRepo.getProducts().size());
        assertEquals(product2.id(), productRepo.getProduct(product2.id()).id());
    }
}