package com.example.exam2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    //ID , name , number_of_pages , price  , category  , isAvailable
    @NotEmpty(message = "not valid id")
    private String ID;
    @NotEmpty(message = "not valid name")
    private String name;
    @NotNull(message = "not valid number of pages")
    private int number_of_pages;
    @NotNull(message = "not valid price")
    private int price;
    @NotEmpty(message = "not valid category")
    @Pattern(regexp = "^(noval|academic)$",message = "category must be noval or academic")
    private String category;
    @NotNull(message = "not valid isAvailable")
    private boolean isAvailable;
}
