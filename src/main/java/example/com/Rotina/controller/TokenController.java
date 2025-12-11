package example.com.Rotina.controller;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.com.Rotina.dto.LoginRequest;
import example.com.Rotina.dto.LoginResponse;
import example.com.Rotina.repository.UsuarioModelRepository;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UsuarioModelRepository userModelRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Injeção de dependências através do construtor
    public TokenController(JwtEncoder jwtEncoder, UsuarioModelRepository userModelRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userModelRepository = userModelRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // Corrigido: Mudança de findyByUsername para findByName
        var user = userModelRepository.findByName(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new BadJwtException("Invalid username or password");
        }

        var now = Instant.now();
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getUserid().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
