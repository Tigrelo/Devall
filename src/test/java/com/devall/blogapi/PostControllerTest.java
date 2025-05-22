package com.devall.blogapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testBuscarPosts_retornaCamposCorretos() throws Exception {
		mockMvc.perform(get("/api/v2/post")
						.param("search", "texto"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].titulo").exists())
				.andExpect(jsonPath("$[0].resumo").exists())
				.andExpect(jsonPath("$[0].site").exists())
				.andExpect(jsonPath("$[0].autor").exists())
				// Verifica que o autor NÃO tem os campos avatar e miniBiografia
				.andExpect(jsonPath("$[0].autor.avatar").doesNotExist())
				.andExpect(jsonPath("$[0].autor.miniBiografia").doesNotExist())
				// Verifica que o campo url NÃO existe no post
				.andExpect(jsonPath("$[0].url").doesNotExist())
				// Verifica que o campo dataPublicacao existe (pode colocar mais validações aqui)
				.andExpect(jsonPath("$[0].dataPublicacao").exists());
	}

	@Test
	public void testRegistrarCliqueERetornarUrl() throws Exception {
		Long postId = 1L; // Use um ID válido que exista no seu banco de teste

		mockMvc.perform(get("/api/v2/post/clique/{id}", postId))
				.andExpect(status().isOk())
				// Verifica se retorna o campo url
				.andExpect(jsonPath("$.url").exists());
		// Se quiser, pode validar mais propriedades aqui
	}
}
