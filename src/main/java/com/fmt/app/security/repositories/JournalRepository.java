package com.fmt.app.security.repositories;

import com.fmt.app.security.entities.Journal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal,Long> {
    List<Journal> findAllByUsuarioId(Long idUsuario);

    Optional<Journal> findByIdAndUsuarioId(Long id, Long idUsuario);
    @Transactional
    Integer deleteJournalByIdAndUsuarioId(Long id, Long idUsuario);
}
