package com.fmt.app.security.repositories;

import com.fmt.app.security.entities.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findAllByCadernoUsuarioId(Long idUsuario);

    Optional<Note> findByIdAndCadernoUsuarioId(Long id,Long idUsuario);
    @Transactional
    Integer deleteJournalByIdAndCadernoUsuarioId(Long id, Long idUsuario);
}
