package com.api.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
    @Size(min = 2,message = "Should be at least 2 characters")
    private  String name;
    @Email
    private String email;
    @Size(min = 10,max = 10)
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
