package com.isaac.devall.controller;

import com.isaac.devall.dto.PostDetailDTO;
import com.isaac.devall.dto.PostListDTO;
import com.isaac.devall.model.Click;
import com.isaac.devall.model.Post;
import com.isaac.devall.repository.ClickRepository;
import com.isaac.devall.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ClickRepository clickRepository;

    @GetMapping
    public List<PostListDTO> searchPosts(@RequestParam(required = false, name = "search") String search) {
        List<Post> posts = (search != null && !search.isBlank())
                ? postService.searchByContent(search)
                : postService.findAll();

        return posts.stream()
                .sorted(Comparator.comparing(Post::getPublishedAt).reversed())
                .map(PostListDTO::new)
                .toList();
    }

    @GetMapping("/clique/{id}")
    public ResponseEntity<PostDetailDTO> registerClick(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = optionalPost.get();

        Click click = new Click();
        click.setPost(post);
        clickRepository.save(click);

        return ResponseEntity.ok(new PostDetailDTO(post));
    }
}
