
package com.devall.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "criado_em", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    @org.hibernate.annotations.UpdateTimestamp
    private LocalDateTime atualizadoEm;
}
