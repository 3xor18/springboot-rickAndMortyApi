package com.xor18.rickAndMortyApi.services.impl;

import com.xor18.rickAndMortyApi.dto.responses.character.CharacterResponseDto;
import com.xor18.rickAndMortyApi.dto.results.character.OriginCharacterResultDto;
import com.xor18.rickAndMortyApi.dto.results.character.RickAndMortyCharacterResultDto;
import com.xor18.rickAndMortyApi.dto.results.origin.OriginResultDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import com.xor18.rickAndMortyApi.services.OriginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CharacterServiceImplTest {

    private CharacterServiceImpl characterService;
    private RestTemplate restTemplate;
    private OriginService originService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        originService=mock(OriginService.class);
        characterService = new CharacterServiceImpl(restTemplate,originService);
    }

    @Test
    @DisplayName("Error en el fetch del api")
    void characterNoEncontrado() throws AppInternalServerErrorException {
        Long idMock=1L;
        when(characterService.fetchApi(idMock)).thenThrow(Error.class);
        assertThrows(AppInternalServerErrorException.class, () ->
                characterService.findById(idMock)
        );
    }

    @Test
    @DisplayName("Character con Origin null")
    void elCharacterNoTieneOrigin()  throws AppInternalServerErrorException {
        Long idMock=1l;
        Long idLocationMock=2l;
        String nameMock="aaaa";
        RickAndMortyCharacterResultDto characterInfoMock=new RickAndMortyCharacterResultDto();
        characterInfoMock.setId(idMock);
        characterInfoMock.setOrigin(null);
        when(characterService.fetchApi(idMock)).thenReturn(characterInfoMock);
        OriginResultDto originMock=new OriginResultDto();
        originMock.setId(idLocationMock);
        originMock.setName(nameMock);
        when(originService.findOriginByUrl(any())).thenReturn(originMock);
        ResponseEntity<CharacterResponseDto> response=characterService.findById(idMock);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(idMock,response.getBody().getId());
        assertEquals(nameMock,response.getBody().getOrigin().getName());
    }

    @Test
    @DisplayName("Todo OK")
    void todoOk() throws AppInternalServerErrorException  {
        Long idMock=1l;
        Long idLocationMock=2l;
        String nameMock="aaaa";
        String urlMck="bbbbb";
        RickAndMortyCharacterResultDto characterInfoMock=new RickAndMortyCharacterResultDto();
        characterInfoMock.setId(idMock);
        OriginCharacterResultDto origin=new OriginCharacterResultDto();
        origin.setName(nameMock);
        origin.setUrl(urlMck);
        characterInfoMock.setOrigin(origin);
        when(characterService.fetchApi(idMock)).thenReturn(characterInfoMock);
        OriginResultDto originMock=new OriginResultDto();
        originMock.setId(idLocationMock);
        originMock.setName(nameMock);
        when(originService.findOriginByUrl(any())).thenReturn(originMock);
        ResponseEntity<CharacterResponseDto> response=characterService.findById(idMock);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(idMock,response.getBody().getId());
        assertEquals(nameMock,response.getBody().getOrigin().getName());
    }
}