package com.fmt.app.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TesteController {
    @GetMapping()
    public String buscarTodos(){
        return "TESTE";
    }
}
