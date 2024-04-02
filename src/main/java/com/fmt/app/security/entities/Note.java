package com.fmt.app.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nota")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",nullable = false)
    private String title;
    @Column(name = "conteudo")
    private String content;

    @ManyToOne
    @JsonIgnoreProperties("notes")
    @JoinColumn(name = "id_caderno",nullable = false)
    private Journal caderno;
}
