package com.max.spring_boot_book_seller.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
@Entity
@Table(name = "purchases")
public class Purchase {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalTime purchaseTime;
    private Double price;

    // Getters and Setters
}

