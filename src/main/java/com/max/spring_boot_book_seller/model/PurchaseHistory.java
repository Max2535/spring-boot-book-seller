package com.max.spring_boot_book_seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "purchases_history")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "book_id", nullable = false)
    private long bookId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;
}
