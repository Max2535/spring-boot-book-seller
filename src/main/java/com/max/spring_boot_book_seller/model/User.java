package com.max.spring_boot_book_seller.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",unique = true,nullable = false,length = 100)
    private String username;
    @Column(name = "password",nullable = false,length = 100)
    private String password;
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @Column(name = "create_time",nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;
}
