package bootcamp.shopservice.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {
    private final Map<String, Order> orders;

    public OrderMapRepo() {
        this.orders = new HashMap<>();
    }

    @Override
    public boolean add(Order order) {
        boolean isAvailable = this.orders.containsKey(order.orderId());
        var previousOrder  = this.orders.put(order.orderId(), order);
        return isAvailable && previousOrder != null || !isAvailable && previousOrder == null;
    }

    @Override
    public boolean remove(String orderId) {
        return this.orders.remove(orderId) != null;
    }

    @Override
    public boolean remove(Order order) {
        return this.orders.remove(order.orderId()) != null;
    }

    @Override
    public Order getOrder(String orderId) {
        return this.orders.get(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return this.orders.values().stream().toList();
    }

    @Override
    public void removeAll() {
        this.orders.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.orders.isEmpty();
    }
}
