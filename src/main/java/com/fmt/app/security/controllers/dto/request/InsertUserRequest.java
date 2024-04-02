package com.fmt.app.security.controllers.dto.request;


import com.fmt.app.security.entities.User;

public record InsertUserRequest(String username, String password) {
    public User toEntity(){
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        return entity;
    }
}
