package com.mustache.bbs.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user2")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
