package com.isaac.devall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository<Post> extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE lower(p.title) LIKE lower(concat('%', :q, '%')) OR lower(p.summary) LIKE lower(concat('%', :q, '%')) ORDER BY p.publishedAt DESC")
    Page<Post> searchByTitleOrSummary(@Param("q") String q, Pageable pageable);
}