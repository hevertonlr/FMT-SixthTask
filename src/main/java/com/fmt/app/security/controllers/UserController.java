package com.fmt.app.security.controllers;

import com.fmt.app.security.controllers.dto.request.InsertUserRequest;
import com.fmt.app.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody InsertUserRequest insertUserRequest) throws Exception {
        service.create(insertUserRequest.toEntity());
        return new ResponseEntity<>("Usuário Criado com Sucesso!", HttpStatus.CREATED);
    }
}
