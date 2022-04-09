package com.xor18.rickAndMortyApi.exceptions;

import org.springframework.http.HttpStatus;

public class AppInternalServerErrorException extends AppGlobalException {

    public AppInternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    private static final long serialVersionUID = 1L;

}