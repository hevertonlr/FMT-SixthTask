package com.fmt.app.security.controllers;

import com.fmt.app.security.controllers.dto.request.journal.InsertJournalRequest;
import com.fmt.app.security.controllers.dto.request.journal.UpdateJournalRequest;
import com.fmt.app.security.entities.Journal;
import com.fmt.app.security.services.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("caderno")
@RequiredArgsConstructor
public class CadernoController {
    private final JournalService service;
    @GetMapping("list")
    public List<Journal> buscarTodos(@RequestHeader(name = "Authorization") String token){
        return service.list(token);
    }
    @GetMapping("{id}")
    public Journal buscarPorId(@PathVariable Long id,
                               @RequestHeader(name = "Authorization") String token){
        return service.findById(id,token);
    }

    @PostMapping
    public Journal criar(@RequestBody InsertJournalRequest record,
                         @RequestHeader(name = "Authorization") String token){
        return service.create(record.toEntity(),token);
    }

    @PutMapping
    public Journal atualizar(@RequestBody UpdateJournalRequest record,
                             @RequestHeader(name = "Authorization") String token){
        return service.update(record.toEntity(),token);
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id,
                        @RequestHeader(name = "Authorization") String token){
        service.delete(id,token);
        return new ResponseEntity<>("Caderno deletado com Sucesso!", HttpStatus.OK);
    }


}
