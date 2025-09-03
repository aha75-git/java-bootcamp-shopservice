package bootcamp.shopservice.order;

import java.util.List;

public interface OrderRepo {
    boolean add(Order order);
    boolean remove(String orderId);
    boolean remove(Order order);
    Order getOrder(String orderId);
    List<Order> getOrders();
    void removeAll();
    boolean isEmpty();
}
