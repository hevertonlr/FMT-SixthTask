package com.fmt.app.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fmt.app.security.controllers.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "senha")
    private String password;

    public boolean validPassword(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(loginRequest.password(), this.password);
    }
}
