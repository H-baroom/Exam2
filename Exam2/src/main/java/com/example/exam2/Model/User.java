package com.example.exam2.Model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "not valid id")
    private String ID;
    @NotEmpty(message = "not valid name")
    private String name;
    @NotNull(message = "not valid age")
    private int age;
    @NotNull(message = "not valid balance")
    private int balance;
    @NotEmpty(message = "not valid role")
    @Pattern(regexp = "^(customer|libraian)$",message = "role  must be customer or libraian")
    private String role;
}
