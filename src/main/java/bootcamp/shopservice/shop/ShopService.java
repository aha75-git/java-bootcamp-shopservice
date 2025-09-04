package bootcamp.shopservice.shop;

import bootcamp.shopservice.order.Order;
import bootcamp.shopservice.order.OrderRepo;
import bootcamp.shopservice.product.Product;
import bootcamp.shopservice.product.ProductRepo;

import java.util.Map;

public class ShopService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public ShopService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public boolean placeOrder(Order order) {
        for (Map.Entry<Product, Integer> entries : order.mapProductQuantity().entrySet() ) {
            Product product = entries.getKey();
            Integer quantity = entries.getValue();
            if (this.productRepo.getProduct(product.id()) == null) {
                System.out.println("Produkt mit ID " + product.id() + " existiert nicht.");
                return false;
            }

            if(!this.productRepo.isProductInStock(product.id(), quantity)) {
                System.out.println("Produkt mit ID " + product.id() + " ist nicht ausreichend auf Lager.");
                return false;
            }
        }

        this.orderRepo.add(order);

        for (Map.Entry<Product, Integer> entries : order.mapProductQuantity().entrySet() ) {
            Product product = entries.getKey();
            Integer quantity = entries.getValue();
            productRepo.updateStock(product.id(), product.stockQuantity() - quantity);
        }

        return true;
    }
}
