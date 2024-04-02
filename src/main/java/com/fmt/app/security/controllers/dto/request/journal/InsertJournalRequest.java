package com.fmt.app.security.controllers.dto.request.journal;

import com.fmt.app.security.entities.Journal;
import jakarta.validation.constraints.NotBlank;

public record InsertJournalRequest(@NotBlank String nome) {
    public Journal toEntity(){
        Journal journal = new Journal();
        journal.setName(this.nome);
        return journal;
    }
}
