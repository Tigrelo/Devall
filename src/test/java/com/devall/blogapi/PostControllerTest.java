package com.devall.blogapi;

import com.devall.model.Post;
import com.devall.model.Site;

import com.devall.model.Usuario;
import com.devall.repository.PostRepository;
import com.devall.repository.SiteRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.devall.repository.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UsuarioRepository autorRepository;  // <- Injete aqui

	@Autowired
	private SiteRepository siteRepository;

	private Post postTeste;

	@BeforeEach
	public void setup() {
		postRepository.deleteAll();
		autorRepository.deleteAll();
		siteRepository.deleteAll();

		Site site = new Site();
		site.setNome("Site Teste");
		site.setUrl("http://teste.com");
		site = siteRepository.save(site);

		Usuario autor = new  Usuario();
		autor.setNome("Autor Teste");
		autor.setEmail("autor@teste.com");
		autor = autorRepository.save(autor);

		Post post = new Post();
		post.setTitulo("Post com texto");
		post.setResumo("Resumo com texto para busca");
		post.setDataPublicacao(LocalDateTime.now());
		post.setSite(site);
		post.setAutor(autor);
		post.setUrl("http://teste.com/post1");
		postTeste = postRepository.save(post);
	}
	@Test
	public void testBuscarPosts_retornaCamposCorretos() throws Exception {
		mockMvc.perform(get("/api/v2/post")
						.param("search", "texto"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].titulo").exists())
				.andExpect(jsonPath("$[0].resumo").exists())
				.andExpect(jsonPath("$[0].site").exists())
				.andExpect(jsonPath("$[0].autor").exists())
				// Autor NÃO tem avatar e miniBiografia
				.andExpect(jsonPath("$[0].autor.avatar").doesNotExist())
				.andExpect(jsonPath("$[0].autor.miniBiografia").doesNotExist())
				// URL NÃO existe no post da busca
				.andExpect(jsonPath("$[0].url").doesNotExist())
				// Verifica dataPublicacao
				.andExpect(jsonPath("$[0].dataPublicacao").exists());
	}

	@Test
	public void testRegistrarCliqueERetornarUrl() throws Exception {
		Long postId = postTeste.getId();

		mockMvc.perform(get("/api/v2/post/clique/{id}", postId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.url").exists());

		// Poderia verificar clique no banco aqui (opcional)
	}
}
