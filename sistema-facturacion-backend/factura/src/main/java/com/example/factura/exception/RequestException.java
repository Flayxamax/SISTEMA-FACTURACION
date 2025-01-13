package com.example.factura.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestException extends RuntimeException {
    private String code;

    public RequestException(String code, String message) {
        super(message);
        this.code = code;
    }
}
