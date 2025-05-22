package com.devall.controller;

import com.devall.dto.PostDetailDTO;
import com.devall.dto.PostListDTO;
import com.devall.dto.PostResponseDTO;
import com.devall.model.Click;
import com.devall.model.Post;
import com.devall.repository.ClickRepository;
import com.devall.service.PostService;
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

    // Endpoint GET /api/v2/post?search=texto
    @GetMapping
    public List<PostListDTO> searchPosts(@RequestParam(required = false, name = "search") String search) {
        List<Post> posts = (search != null && !search.isBlank())
                ? postService.searchByContent(search)
                : postService.findAll();

        return posts.stream()
                .sorted(Comparator.comparing(Post::getDataPublicacao).reversed())
                .map(PostListDTO::new)
                .toList();
    }

    // Endpoint GET /api/v2/post/clique/{id}
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

    // Endpoint GET /api/v2/post/{id} - busca post por id
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = optionalPost.get();
        return ResponseEntity.ok(new PostResponseDTO(post));
    }

    // Endpoint POST /api/v2/post para criar um novo post
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody Post post) {
        Post novoPost = postService.save(post);
        return ResponseEntity.ok(new PostResponseDTO(novoPost));
    }
}
