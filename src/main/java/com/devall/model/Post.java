package com.isaac.devall.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Usuario author;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}
