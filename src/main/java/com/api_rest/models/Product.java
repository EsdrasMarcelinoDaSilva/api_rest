package com.api_rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "The product name must not be empty")
    @Size(message = "The product name must be between 2 and 100 characters long")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The price of the product cannot be zero")
    @Positive(message = "The price of the product must be a positive value")
    @Digits(integer = 10, fraction = 2, message = "The price must have a maximum of 10 whole digits and 2 decimal places")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;


    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "The product description must not exceed 500 characters")
    @Column()
    private String description;
}
