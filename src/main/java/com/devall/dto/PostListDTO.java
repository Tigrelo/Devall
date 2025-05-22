package com.devall.dto;

import com.devall.model.Post;
import lombok.Data;

@Data
public class PostListDTO {
    private Long id;
    private String titulo;
    private String resumo;
    private String dataPublicacao;
    private SiteDTO site;
    private UsuarioDTO autor;

    public PostListDTO(Post post) {
        this.id = post.getId();
        this.titulo = post.getTitulo();
        this.resumo = post.getResumo();
        this.dataPublicacao = post.getDataPublicacao() != null ? post.getDataPublicacao().toString() : null;
        this.site = new SiteDTO(post.getSite());
        this.autor = new UsuarioDTO(post.getAutor());
    }
}
