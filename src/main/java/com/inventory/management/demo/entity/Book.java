package com.inventory.management.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BOOKS_JPA")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    public Book(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


}

