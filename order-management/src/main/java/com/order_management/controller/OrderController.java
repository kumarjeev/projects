package com.order_management.controller;

import com.order_management.dto.OrderRequest;
import com.order_management.entity.Order;
import com.order_management.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
//http://localhost:8080/orders
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order orderById = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderById, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable String orderId) {
        Order orderStatus = orderService.updateOrderStatus(orderId);
        return new ResponseEntity<>(orderStatus, HttpStatus.OK);
    }
}
