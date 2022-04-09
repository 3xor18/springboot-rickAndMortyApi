package com.xor18.rickAndMortyApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xor18.rickAndMortyApi.dto.responses.character.CharacterResponseDto;
import com.xor18.rickAndMortyApi.services.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RickAndMortyController.class)
class RickAndMortyControllerTest {

    private final String url = "/api/v1/character/";

    ObjectMapper resMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharacterService characterService;

    @BeforeEach
    void setUp() {
        resMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Si no le pasan un id en la url")
    void idNoSuministrado() throws Exception {
        mvc.perform(get(url)).andExpect(status().is5xxServerError()).andExpect(jsonPath("$.errorMessage").value("No puedes consultar todos los caracteres al mismo tiempo"));
    }

    @Test
    @DisplayName("Si no encontro el id en el api")
    void idNoEncontrado() throws Exception {
        when(characterService.findById(any())).thenThrow(HttpClientErrorException.class);
        mvc.perform(get(url + '0')).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.errorMessage").value("Este character no existe"));
    }

    @Test
    @DisplayName("Si le pasan un id no numerico como un alfabetico")
    void idNoNumerico() throws Exception {
        when(characterService.findById(any())).thenThrow(MethodArgumentTypeMismatchException.class);
        mvc.perform(get(url + 'a')).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.errorMessage").value("el id debe ser un número entero"));
    }

    @Test
    @DisplayName("Todo OK")
    void idOk() throws Exception {
        Long idMock=1L;
        String nameMock="aaa";
        CharacterResponseDto dtoMock=new CharacterResponseDto();
        dtoMock.setId(idMock);
        dtoMock.setName(nameMock);
        ResponseEntity<CharacterResponseDto> mockResponse=ResponseEntity.status(HttpStatus.OK).body(dtoMock);
        when(characterService.findById(any())).thenReturn(mockResponse);
        mvc.perform(get(url + '1')).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(idMock)).andExpect(jsonPath("$.name").value(nameMock));
    }
}