package com.xor18.rickAndMortyApi.exceptions;

import com.xor18.rickAndMortyApi.exceptions.dto.ErrorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppGlobalException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int responseCode;

    private final List<ErrorDto> errorsList = new ArrayList<>();

    public AppGlobalException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}