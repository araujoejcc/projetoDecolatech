package br.com.decolatech.projetoDecolatech.exceptions;

import java.util.Date;
import java.util.Map;

public class ValidationErrorDetails extends ErrorDetails {
    
    private final Map<String, String> errors;

    public ValidationErrorDetails(Date timestamp, int status, String error, String message, String path,
                                 Map<String, String> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}