package bootcamp.shopservice.shop;

import bootcamp.shopservice.order.Order;
import bootcamp.shopservice.order.OrderRepo;
import bootcamp.shopservice.product.Product;
import bootcamp.shopservice.product.ProductRepo;

public class ShopService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public ShopService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public boolean placeOrder(Order order) {
        boolean isOk = true;
        for (Product product : order.products()) {
            if (this.productRepo.getProduct(product.id()) == null) {
                System.out.println("Produkt mit ID " + product.id() + " existiert nicht.");
                isOk = false;
            }
        }
        if (isOk) {
            this.orderRepo.add(order);
        }
        return isOk;
    }
}
