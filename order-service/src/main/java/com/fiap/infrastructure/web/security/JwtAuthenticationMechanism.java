package com.fiap.infrastructure.web.security;



import com.fiap.core.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class JwtAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    TokenProvider tokenProvider;

    @Override
    public Uni<SecurityIdentity> authenticate(
            RoutingContext context,
            io.quarkus.security.identity.IdentityProviderManager identityProviderManager
    ) {

        String path = context.request().path();

        // =========================
        // Rotas públicas
        // =========================
        if (path.startsWith("/api/v1/auth/")) {
            return Uni.createFrom().nullItem();
        }

        String header = context.request().getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            return Uni.createFrom().nullItem();
        }

        String token = header.substring(7);

        try {
            Jws<Claims> jws = tokenProvider.parseToken(token);
            Claims claims = jws.getBody();

            String subject = claims.getSubject();
            String email = claims.get("email", String.class);
            String role = claims.get("role", String.class);

            if (subject == null || email == null || role == null) {
                return Uni.createFrom().nullItem();
            }

            UUID userId = UUID.fromString(subject);

            SecurityIdentity identity =
                    QuarkusSecurityIdentity.builder()
                            .setPrincipal(userId::toString)
                            .addRole(role)
                            .addAttribute("userId", userId)
                            .addAttribute("email", email)
                            .build();

            return Uni.createFrom().item(identity);

        } catch (JwtException | IllegalArgumentException e) {
            return Uni.createFrom().nullItem();
        }
    }

    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        return Uni.createFrom().item(
                new ChallengeData(
                        401,
                        HttpHeaders.WWW_AUTHENTICATE,
                        "Bearer"
                )
        );
    }

    @Override
    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
        return HttpAuthenticationMechanism.super.getCredentialTypes();
    }

    @Override
    public Uni<Boolean> sendChallenge(RoutingContext context) {
        return HttpAuthenticationMechanism.super.sendChallenge(context);
    }

    @Override
    public Uni<HttpCredentialTransport> getCredentialTransport(RoutingContext context) {
        return HttpAuthenticationMechanism.super.getCredentialTransport(context);
    }

    @Override
    public int getPriority() {
        return HttpAuthenticationMechanism.super.getPriority();
    }

    /**
     * Principal exposto no SecurityIdentity
     * (equivalente ao UsernamePasswordAuthenticationToken do Spring)
     */
    public record AuthenticatedUserPrincipal(
            UUID id,
            String email,
            String role
    ) implements Principal {

        @Override
        public String getName() {
            return id.toString();
        }
    }
}

