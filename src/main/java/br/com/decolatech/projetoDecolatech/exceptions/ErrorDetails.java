package br.com.decolatech.projetoDecolatech.exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorDetails(Date timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
