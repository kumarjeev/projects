package com.orderManagement.serviceTest;
import com.orderManagement.dto.OrderDto;
import com.orderManagement.entity.Order;
import com.orderManagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void testCreateOrder_Success() {
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("Jeevan Kumar");
        orderDto.setAmount(1150.0);
        Order savedOrder = orderService.createOrder(orderDto);
        assertNotNull(savedOrder.getOrderId());
        assertEquals("Jeevan Kumar", savedOrder.getCustomerName());
        assertEquals(1150.0, savedOrder.getAmount());
        assertEquals(Order.OrderStatus.NEW, savedOrder.getStatus());
    }
    @Test
    void testUpdateStatus_shouldMoveFromNewToProcessing() {
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("Jeevan Kumar");
        orderDto.setAmount(1200.0);

        Order order = orderService.createOrder(orderDto);

        Order updatedOrder = orderService.updateStatus(order.getOrderId());

        assertEquals(Order.OrderStatus.PROCESSING, updatedOrder.getStatus());
    }
    @Test
    void testUpdateStatus_shouldMoveFromProcessingToComplete() {

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("Jeevan Kumar");
        orderDto.setAmount(1200.0);

        Order order = orderService.createOrder(orderDto);

        orderService.updateStatus(order.getOrderId());

        Order updatedOrder = orderService.updateStatus(order.getOrderId());

        assertNotNull(updatedOrder);
        assertEquals(Order.OrderStatus.COMPLETED, updatedOrder.getStatus());
    }

}