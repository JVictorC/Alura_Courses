package com.jvictorc.volMed.exception;

public record ErrorView(
        String error,
        int status
) {
}
