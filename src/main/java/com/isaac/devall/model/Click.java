package com.isaac.devall.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "click")
public class Click {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime clickedAt;

    @PrePersist
    public void prePersist() {
        clickedAt = LocalDateTime.now();
    }

    // getters e setters
}
