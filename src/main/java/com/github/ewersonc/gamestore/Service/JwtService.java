package com.github.ewersonc.gamestore.Service;

import com.github.ewersonc.gamestore.Entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final long expiracaoEmMinutos;

    public JwtService(JwtEncoder jwtEncoder,
                      @Value("${app.jwt.expiration-minutes}") long expiracaoEmMinutos) {
        this.jwtEncoder = jwtEncoder;
        this.expiracaoEmMinutos = expiracaoEmMinutos;
    }

    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("gamestore")
                .subject(usuario.getEmail())
                .issuedAt(agora)
                .expiresAt(agora.plus(expiracaoEmMinutos, ChronoUnit.MINUTES))
                .claim("id", usuario.getId())
                .claim("nome", usuario.getNome())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
