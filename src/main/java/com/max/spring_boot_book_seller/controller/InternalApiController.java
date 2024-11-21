package com.max.spring_boot_book_seller.controller;


import com.max.spring_boot_book_seller.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")//This is the base path for this controller
public class InternalApiController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/make-admin/{username}")//This is the path for this method
    public ResponseEntity<?> makeAdmin(@PathVariable String username){
        userService.makeAdmin(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
