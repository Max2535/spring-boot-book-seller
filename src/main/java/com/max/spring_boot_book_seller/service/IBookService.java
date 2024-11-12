package com.max.spring_boot_book_seller.service;

import com.max.spring_boot_book_seller.model.Book;

import java.util.List;

public interface IBookService {
    Book save(Book book);

    void deleteById(Long id);

    List<Book> findAll();
}
