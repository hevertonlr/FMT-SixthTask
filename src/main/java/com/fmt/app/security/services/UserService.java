package com.fmt.app.security.services;

import com.fmt.app.security.entities.User;
import com.fmt.app.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptEncoder;

    public List<User> list(){
        return repository.findAll();
    }
    public User findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public User create(User entity){
        if(repository.existsByUsername(entity.getUsername()))
            throw new RuntimeException("Erro ao Cadastrar Usuário");
        entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
        return repository.save(entity);
    }

}
