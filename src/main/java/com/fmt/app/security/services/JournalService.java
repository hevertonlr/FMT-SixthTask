package com.fmt.app.security.services;

import com.fmt.app.security.entities.Journal;
import com.fmt.app.security.entities.User;
import com.fmt.app.security.repositories.JournalRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fmt.app.security.utils.Utils.getParsedToken;

@Service
@AllArgsConstructor
public class JournalService {
    private final JournalRepository repository;
    private final UserService userService;
    private final JwtDecoder jwtDecoder;
    public List<Journal> list(String token){
        Long userID = getUserIdByToken(token);
        return repository.findAllByUsuarioId(userID);
    }
    public Journal findById(Long id,String token){
        Long userID = getUserIdByToken(token);
        return repository.findByIdAndUsuarioId(id,userID).orElseThrow(() -> new RuntimeException("Caderno não encontrado!"));
    }

    public Journal create(Journal entity,String token){
        User user = userService.findById(getUserIdByToken(token));
        entity.setUsuario(user);
        return repository.save(entity);
    }

    public Journal update(Journal entity,String token){
        Journal existent = repository.findByIdAndUsuarioId(entity.getId(),getUserIdByToken(token)).orElseThrow(() -> new RuntimeException("Caderno não encontrado!"));
        existent.setName(entity.getName());
        return repository.save(existent);
    }

    public void delete(Long id,String token){
        if(repository.deleteJournalByIdAndUsuarioId(id,getUserIdByToken(token))==0)
            throw new RuntimeException("Caderno não encontrado!");
    }

    private Long getUserIdByToken(String token){
        return Long.valueOf(jwtDecoder.decode(getParsedToken(token)).getClaim("sub").toString());
    }

}
