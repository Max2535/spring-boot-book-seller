package com.max.spring_boot_book_seller.controller;

import com.max.spring_boot_book_seller.model.User;
import com.max.spring_boot_book_seller.service.IAuthenticationService;
import com.max.spring_boot_book_seller.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
