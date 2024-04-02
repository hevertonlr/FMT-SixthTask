package com.fmt.app.security.services;

import com.fmt.app.security.entities.Journal;
import com.fmt.app.security.entities.Note;
import com.fmt.app.security.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fmt.app.security.utils.Utils.getParsedToken;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository repository;
    private final JournalService journalService;
    private final JwtDecoder jwtDecoder;
    public List<Note> list(String token){
        Long userID = getUserIdByToken(token);
        return repository.findAllByCadernoUsuarioId(userID);
    }
    public Note findById(Long id, String token){
        Long userID = getUserIdByToken(token);
        return repository.findByIdAndCadernoUsuarioId(id,userID).orElseThrow(() -> new RuntimeException("Nota não encontrada!"));
    }

    public Note create(Note entity,String token){
        Journal journal = journalService.findById(entity.getCaderno().getId(),token);
        entity.setCaderno(journal);
        return repository.save(entity);
    }

    public Note update(Note entity,String token){
        Note existent = repository.findByIdAndCadernoUsuarioId(entity.getId(),getUserIdByToken(token)).orElseThrow(() -> new RuntimeException("Nota não encontrada!"));
        existent.setTitle(entity.getTitle());
        existent.setContent(entity.getContent());
        return repository.save(existent);
    }

    public void delete(Long id,String token){
        if(repository.deleteJournalByIdAndCadernoUsuarioId(id,getUserIdByToken(token))==0)
            throw new RuntimeException("Nota não encontrada!");
    }

    private Long getUserIdByToken(String token){
        return Long.valueOf(jwtDecoder.decode(getParsedToken(token)).getClaim("sub").toString());
    }
}
