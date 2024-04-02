package com.fmt.app.security.controllers.dto.request.note;


import com.fmt.app.security.entities.Journal;
import com.fmt.app.security.entities.Note;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertNoteRequest(@NotNull Long idCaderno, @NotBlank String titulo, String conteudo) {
    public Note toEntity(){
        Note entity = new Note();
        entity.setTitle(titulo);
        entity.setContent(conteudo);
        Journal journal = new Journal();
        journal.setId(idCaderno);
        entity.setCaderno(journal);
        return entity;
    }
}
