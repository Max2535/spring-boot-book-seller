package com.max.spring_boot_book_seller.service;

import com.max.spring_boot_book_seller.model.User;

import java.util.Optional;

public interface IUserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);
}
