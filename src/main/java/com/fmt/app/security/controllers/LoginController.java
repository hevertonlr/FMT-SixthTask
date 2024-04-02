package com.fmt.app.security.controllers;

import com.fmt.app.security.controllers.dto.request.LoginRequest;
import com.fmt.app.security.controllers.dto.response.LoginResponse;
import com.fmt.app.security.entities.User;
import com.fmt.app.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    private static final long EXPIRATION_TIME = 36000L;

    @PostMapping
    public ResponseEntity<LoginResponse> token(@RequestBody LoginRequest loginRequest) throws Exception {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new Exception("Erro, usuário não existe"));

        if (!user.validPassword(loginRequest, bCryptEncoder))
            throw new Exception("Erro ao Logar, senha incorreta");

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("${spring.application.name}")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRATION_TIME))
                .subject(user.getId().toString())
                .build();

        var valorJWT = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(valorJWT, EXPIRATION_TIME));
    }
}
