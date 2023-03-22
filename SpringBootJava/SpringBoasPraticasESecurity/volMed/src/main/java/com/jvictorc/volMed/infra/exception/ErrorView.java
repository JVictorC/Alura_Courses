package com.jvictorc.volMed.infra.exception;

public record ErrorView(
        String error,
        int status
) {
}
