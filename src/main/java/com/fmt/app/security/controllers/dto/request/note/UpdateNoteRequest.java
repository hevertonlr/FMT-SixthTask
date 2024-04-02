package com.fmt.app.security.controllers.dto.request.note;

import com.fmt.app.security.entities.Note;
import jakarta.validation.constraints.NotNull;

public record UpdateNoteRequest(@NotNull Long id, String titulo, String conteudo) {
    public Note toEntity(){
        Note entity = new Note();
        entity.setId(id);
        entity.setTitle(titulo);
        entity.setContent(conteudo);
        return entity;
    }
}
