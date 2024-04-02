package com.fmt.app.security.controllers;

import com.fmt.app.security.controllers.dto.request.note.InsertNoteRequest;
import com.fmt.app.security.controllers.dto.request.note.UpdateNoteRequest;
import com.fmt.app.security.entities.Note;
import com.fmt.app.security.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("nota")
@RequiredArgsConstructor
public class NotaController {
    private final NoteService service;
    @GetMapping("list")
    public List<Note> buscarTodos(@RequestHeader(name = "Authorization") String token){
        return service.list(token);
    }
    @GetMapping("{id}")
    public Note buscarPorId(@PathVariable Long id,
                            @RequestHeader(name = "Authorization") String token){
        return service.findById(id,token);
    }

    @PostMapping
    public Note criar(@RequestBody InsertNoteRequest record,
                      @RequestHeader(name = "Authorization") String token){
        return service.create(record.toEntity(),token);
    }

    @PutMapping
    public Note atualizar(@RequestBody UpdateNoteRequest record,
                          @RequestHeader(name = "Authorization") String token){
        return service.update(record.toEntity(),token);
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id,
                        @RequestHeader(name = "Authorization") String token){
        service.delete(id,token);
        return new ResponseEntity<>("Nota deletada com Sucesso!", HttpStatus.OK);
    }
}
