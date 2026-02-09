package com.orderManagement.service;

import com.orderManagement.dto.OrderDto;
import com.orderManagement.entity.Order;
import com.orderManagement.exception.InvalidOrderException;
import com.orderManagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class OrderService {
    private final Map<String, Order> orderStorage = new ConcurrentHashMap<>();
    public Order createOrder(OrderDto orderDto) {
//        if (order.getCustomerName() == null || order.getCustomerName().isEmpty() || order.getCustomerName().isBlank())
//            throw new InvalidOrderException("customerName is mandatory");
//        if (order.getAmount() == null || order.getAmount() <= 0)
//            throw new InvalidOrderException("Invalid amount (must be > 0)");
        Order order = new Order();
        order.setOrderId(""+new Random().nextInt(10000));//UUID.randomUUID().toString()
        order.setCustomerName(orderDto.getCustomerName());
        order.setAmount(orderDto.getAmount());
        order.setStatus(Order.OrderStatus.NEW);
        orderStorage.put(order.getOrderId(), order);
        return order;
    }

    public Order getOrderById(String orderId) {
        if (!orderStorage.containsKey(orderId))
            throw new ResourceNotFoundException("Order not found with ID: " + orderId);
        return orderStorage.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderStorage.values());
    }

    public Order updateStatus(String orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == Order.OrderStatus.NEW) {
            order.setStatus(Order.OrderStatus.PROCESSING);
        } else if (order.getStatus() == Order.OrderStatus.PROCESSING) {
            order.setStatus(Order.OrderStatus.COMPLETED);
        } else {
            throw new InvalidOrderException("Order already completed");
        }

        return order;
    }
}