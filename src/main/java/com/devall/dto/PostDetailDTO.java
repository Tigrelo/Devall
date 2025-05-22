package com.devall.dto;

import com.devall.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDetailDTO {
    private Long id;
    private String titulo;
    private String resumo;
    private String dataPublicacao;
    private String url;
    private SiteDTO site;
    private UsuarioDTO autor;

    public PostDetailDTO(Post post) {
        this.id = post.getId();
        this.titulo = post.getTitulo();
        this.resumo = post.getResumo();
        this.dataPublicacao = post.getDataPublicacao() != null ? post.getDataPublicacao().toString() : null;
        this.url = post.getUrl();
        this.site = new SiteDTO(post.getSite());
        this.autor = new UsuarioDTO(post.getAutor());
    }
}
