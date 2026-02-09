package com.order_management.service;

import com.order_management.dto.OrderRequest;
import com.order_management.entity.Order;
import com.order_management.entity.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void createOrder_shouldCreateOrderWithNewStatus() {
        OrderRequest request = new OrderRequest();

        request.setCustomerName("Jeevan Kumar");
        request.setAmount(1200.0);

        Order order = orderService.createOrder(request);

        assertNotNull(order);
        assertNotNull(order.getOrderId());
        assertEquals("Jeevan Kumar", order.getCustomerName());
        assertEquals(1200.0, order.getAmount());
        assertEquals(OrderStatus.NEW, order.getStatus());
    }
    @Test
    void updateOrderStatus_shouldMoveFromNewToProcessing() {
        OrderRequest request = new OrderRequest();
        request.setCustomerName("Jeevan Kumar");
        request.setAmount(1200.0);

        Order order = orderService.createOrder(request);

        Order updatedOrder = orderService.updateOrderStatus(order.getOrderId());

        assertEquals(OrderStatus.PROCESSING, updatedOrder.getStatus());
    }
    @Test
    void updateOrderStatus_shouldMoveFromProcessingToComplete() {

        OrderRequest request = new OrderRequest();
        request.setCustomerName("Jeevan Kumar");
        request.setAmount(1200.0);

        Order order = orderService.createOrder(request);

        orderService.updateOrderStatus(order.getOrderId());

        Order updatedOrder = orderService.updateOrderStatus(order.getOrderId());

        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.COMPLETED, updatedOrder.getStatus());
    }

}
