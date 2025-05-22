package com.devall.dto;

import com.devall.model.Site;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SiteDTO {
    private Long id;
    private String nome;
    private String url;

    public SiteDTO(Site site) {
        this.id = site.getId();
        this.nome = site.getNome();
        this.url = site.getUrl();
    }
}
