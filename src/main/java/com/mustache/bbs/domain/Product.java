package com.mustache.bbs.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="product")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private  Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
