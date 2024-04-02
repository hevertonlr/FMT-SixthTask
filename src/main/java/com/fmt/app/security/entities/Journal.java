package com.fmt.app.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "caderno")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome",nullable = false)
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private User usuario;
    @OneToMany(mappedBy = "caderno",fetch = FetchType.EAGER)
    private List<Note> notes;
}
