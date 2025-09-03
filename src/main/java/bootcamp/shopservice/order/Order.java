package bootcamp.shopservice.order;

import bootcamp.shopservice.product.Product;

import java.util.List;

public record Order(String orderId, List<Product> products) {

}
