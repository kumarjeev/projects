package com.orderManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderDto {

    @NotBlank(message = "CustomerName is Mandatory")//NotNull+NotEmpty+Not WhiteSpace
    private String customerName;

    @Positive(message = "amount should be >0")//Must be +ve exclude 0
    private Double amount;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getAmount() {
        return amount;
    }
}
