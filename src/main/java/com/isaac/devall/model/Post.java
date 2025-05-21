package com.isaac.devall.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String summary;

    private String url;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    // getters e setters
}

