package com.orderManagement.controller;

import com.orderManagement.dto.OrderDto;
import com.orderManagement.entity.Order;
import com.orderManagement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDto orderDto) {
        Order ordered = orderService.createOrder(orderDto);
        return new ResponseEntity<>(ordered, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable String orderId) {
        Order orderStatus = orderService.updateStatus(orderId);
        return new ResponseEntity<>(orderStatus, HttpStatus.OK);
    }
}