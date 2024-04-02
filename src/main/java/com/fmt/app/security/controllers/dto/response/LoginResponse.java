package com.fmt.app.security.controllers.dto.response;

public record LoginResponse(String token, long expiration) {
}
