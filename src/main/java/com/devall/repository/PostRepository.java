package com.isaac.devall.repository;

import com.isaac.devall.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE lower(p.title) LIKE lower(concat('%', :q, '%')) OR lower(p.summary) LIKE lower(concat('%', :q, '%')) ORDER BY p.publishedAt DESC")
    List<Post> searchByTitleOrSummary(@Param("q") String q);
}
