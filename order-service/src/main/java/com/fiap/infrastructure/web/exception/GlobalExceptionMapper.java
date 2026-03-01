package com.fiap.infrastructure.web.exception;


import com.fiap.core.exception.DomainException;
import com.fiap.infrastructure.web.exception.dto.ApiErrorResponse;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @ServerExceptionMapper
    public Response toResponse(Throwable ex) {

        Throwable root = unwrap(ex);

        // =========================
        // 1️⃣ ERROS DE DOMÍNIO
        // =========================
        if (root instanceof DomainException domainEx) {
            return Response.status(mapDomainStatus(domainEx))
                    .entity(new ApiErrorResponse(
                            domainEx.errorType(),
                            domainEx.getMessage()
                    ))
                    .build();
        }

        // =========================
        // 2️⃣ SEGURANÇA
        // =========================
        if (root instanceof NotAuthorizedException) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiErrorResponse(
                            "UNAUTHORIZED",
                            "Token inválido ou ausente."
                    ))
                    .build();
        }

        if (root instanceof ForbiddenException) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new ApiErrorResponse(
                            "FORBIDDEN",
                            "Você não tem permissão para acessar este recurso."
                    ))
                    .build();
        }

        if (root instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ApiErrorResponse(
                            "NOT_FOUND",
                            "Recurso não encontrado."
                    ))
                    .build();
        }

        logUnexpected(ex);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiErrorResponse(
                        "INTERNAL_ERROR",
                        "Erro interno inesperado."
                ))
                .build();
    }

    private Throwable unwrap(Throwable ex) {
        Throwable current = ex;
        while (current.getCause() != null &&
                !(current instanceof DomainException)) {
            current = current.getCause();
        }
        return current;
    }

    private Response.Status mapDomainStatus(DomainException ex) {
        return switch (ex.errorType()) {
            case "NOT_FOUND" -> Response.Status.NOT_FOUND;
            case "BUSINESS_RULE_VIOLATION" -> Response.Status.CONFLICT;
            case "VALIDATION_ERROR" -> Response.Status.BAD_REQUEST;
            case "UNAUTHORIZED" -> Response.Status.UNAUTHORIZED;
            default -> Response.Status.BAD_REQUEST;
        };
    }

    private void logUnexpected(Throwable ex) {
        ex.printStackTrace();
    }
}
