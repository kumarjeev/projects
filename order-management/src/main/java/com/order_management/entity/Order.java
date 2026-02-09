package com.order_management.entity;

public class Order {

    private String orderId;
    private String customerName;
    private Double amount;
    private OrderStatus status;


    public Order(String orderId, String customerName, Double amount, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
