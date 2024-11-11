package com.max.spring_boot_book_seller.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Double price;

    // One book can have many purchases
    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases;

    // Getters and Setters
}
