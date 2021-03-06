package com.xor18.rickAndMortyApi.controllers;

import com.xor18.rickAndMortyApi.dto.responses.character.CharacterResponseDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import com.xor18.rickAndMortyApi.exceptions.handler.ExceptionResponse;
import com.xor18.rickAndMortyApi.services.CharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@CrossOrigin("*")
@RequestMapping("api/v1/character")
public class RickAndMortyController {

    private final  CharacterService characterService;

    public RickAndMortyController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @ApiOperation(value = "Metodo para obtener todos los caracteres", response = boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "No puedes consultar todos los caracteres al mismo tiempo", response = ExceptionResponse.class)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExceptionResponse> getAll() throws AppInternalServerErrorException {
        log.info("[GET][getAll] - request to get all Characters");
        throw new AppInternalServerErrorException("No puedes consultar todos los caracteres al mismo tiempo");
    }


    @ApiOperation(value = "Metodo para obtener un caracter por ID", response = CharacterResponseDto.class)
    @GetMapping(value = "{idCharacter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterResponseDto> getById(@PathVariable("idCharacter") Long idCharacter) throws AppInternalServerErrorException {
        log.info("[GET][getById] - request to find a Character with id " + idCharacter);
        return characterService.findById(idCharacter);
    }
}
