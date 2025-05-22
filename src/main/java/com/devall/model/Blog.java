package com.devall.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sites") // tabela sites, não blog
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome") // nome no banco é 'nome'
    private String nome;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
