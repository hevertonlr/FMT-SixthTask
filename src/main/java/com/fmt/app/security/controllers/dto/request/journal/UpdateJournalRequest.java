package com.fmt.app.security.controllers.dto.request.journal;

import com.fmt.app.security.entities.Journal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateJournalRequest(@NotNull Long id, @NotBlank String nome) {
    public Journal toEntity(){
        Journal journal = new Journal();
        journal.setId(id);
        journal.setName(nome);
        return journal;
    }
}
