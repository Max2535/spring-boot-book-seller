package com.max.spring_boot_book_seller.service;

import com.max.spring_boot_book_seller.model.Book;
import com.max.spring_boot_book_seller.repository.IBookRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookService implements IBookService {
    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        book.setCreateTime(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
