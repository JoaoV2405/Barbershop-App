package br.com.joao.barber_api.config;


import br.com.joao.barber_api.controller.dto.ClientLoginDTO;
import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.mapper.ClientMapper;
import br.com.joao.barber_api.service.query.IClientQueryService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value(("${security.jwt.token.secret-key:secret-key}"))
    private String secretKey;

    private final IClientQueryService clientService;
    private final ClientMapper mapper;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(ClientLoginDTO client){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return JWT.create()
                .withSubject(client.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("name", client.getName())
                .withClaim("phone", client.getPhone())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        ClientLoginDTO client = ClientLoginDTO.builder()
                .email(jwt.getSubject())
                .name(jwt.getClaim("name").asString())
                .phone(jwt.getClaim("phone").asString())
                .build();
        return new UsernamePasswordAuthenticationToken(client, null, Collections.emptyList());

    }
    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        Client_Entity user = clientService.findByEmail(decoded.getSubject());

        ClientLoginDTO client = mapper.Client_EntityToClientLoginDTO(user);

        return new UsernamePasswordAuthenticationToken(client, null, Collections.emptyList());
    }
}
