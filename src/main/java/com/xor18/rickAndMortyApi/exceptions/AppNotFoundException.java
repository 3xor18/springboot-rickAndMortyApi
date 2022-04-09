package com.xor18.rickAndMortyApi.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppNotFoundException extends AppGlobalException {

    public AppNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    private static final long serialVersionUID = 1L;

}