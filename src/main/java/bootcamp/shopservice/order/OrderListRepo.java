package bootcamp.shopservice.order;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return this.orders;
    }

    public boolean add(Order order) {
        return this.orders.add(order);
    }

    public Order getOrder(String orderId) {
        for (Order order : this.orders) {
            if(order.orderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public boolean remove(Order order) {
        return this.orders.remove(order);
    }

    public boolean remove(String orderId) {
        return this.orders.removeIf(order -> order.orderId().equals(orderId));
    }

    public void removeAll() {
        this.orders.clear();
    }

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }
}
