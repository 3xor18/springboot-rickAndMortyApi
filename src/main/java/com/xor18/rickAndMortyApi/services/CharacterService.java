package com.xor18.rickAndMortyApi.services;

import com.xor18.rickAndMortyApi.dto.responses.character.CharacterResponseDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import org.springframework.http.ResponseEntity;

public interface CharacterService {

    /**
     * Busca la informacion de un character por id
     * @return Response Entity con la info del caracter
     */
    public ResponseEntity<CharacterResponseDto> findById(Long idCharacter) throws AppInternalServerErrorException;

}
