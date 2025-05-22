package com.devall.dto;

import com.devall.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String titulo;
    private String resumo;
    private String url;
    private String dataPublicacao;

    private UsuarioDTO autor;
    private SiteDTO site;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.titulo = post.getTitulo();
        this.resumo = post.getResumo();
        this.url = post.getUrl();
        this.dataPublicacao = post.getDataPublicacao() != null ? post.getDataPublicacao().toString() : null;

        if (post.getAutor() != null) {
            this.autor = new UsuarioDTO(post.getAutor());
        }

        if (post.getSite() != null) {
            this.site = new SiteDTO(post.getSite());
        }
    }
}
