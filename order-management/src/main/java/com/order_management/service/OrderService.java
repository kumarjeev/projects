package com.order_management.service;

import com.order_management.dto.OrderRequest;
import com.order_management.entity.Order;
import com.order_management.entity.OrderStatus;
import com.order_management.exception.InvalidStatusException;
import com.order_management.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, Order> orderStore = new ConcurrentHashMap<>();

    public Order createOrder(OrderRequest request) {
        int num=100+new Random().nextInt(10);
        String orderId = ""+num;//UUID.randomUUID().toString();
        Order order = new Order(
                orderId,
                request.getCustomerName(),
                request.getAmount(),
                OrderStatus.NEW
        );
        orderStore.put(orderId, order);
        return order;
    }

    public Order getOrderById(String orderId) {
        Order order = orderStore.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderStore.values());
    }

    public Order updateOrderStatus(String orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.NEW) {
            order.setStatus(OrderStatus.PROCESSING);
        } else if (order.getStatus() == OrderStatus.PROCESSING) {
            order.setStatus(OrderStatus.COMPLETED);
        } else {
            throw new InvalidStatusException("Order already completed");
        }

        return order;
    }
}
