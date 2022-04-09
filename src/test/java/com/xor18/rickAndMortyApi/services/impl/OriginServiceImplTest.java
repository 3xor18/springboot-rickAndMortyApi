package com.xor18.rickAndMortyApi.services.impl;

import com.xor18.rickAndMortyApi.dto.results.origin.OriginResultDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OriginServiceImplTest {
    private OriginServiceImpl originService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        originService = new OriginServiceImpl(restTemplate);
    }

    @Test
    @DisplayName("Error al hacer fetch en api")
    void errorEnFetchApiRickiMorty() throws AppInternalServerErrorException {
        String url = "21312";
        when(restTemplate.getForObject(url, OriginResultDto.class)).thenThrow(Error.class);
        assertThrows(AppInternalServerErrorException.class, () ->
                originService.findOriginByUrl(url)
        );
    }

    @Test
    @DisplayName("Todo Ok")
    void urlOk() throws AppInternalServerErrorException {
        String url = "mock";
        Long idMock = 1L;
        String nameMock = "aaa";
        OriginResultDto dtoMock = new OriginResultDto();
        dtoMock.setId(idMock);
        dtoMock.setName(nameMock);
        when(restTemplate.getForObject(url, OriginResultDto.class)).thenReturn(dtoMock);
        OriginResultDto result = originService.findOriginByUrl(url);
        assertEquals(idMock, result.getId());
        assertEquals(nameMock, result.getName());
        verify(restTemplate,times(1)).getForObject(url, OriginResultDto.class);
    }

    @Test
    @DisplayName("URL en blanco")
    void urlEmpty() throws AppInternalServerErrorException {
        String url = "";
        OriginResultDto result = originService.findOriginByUrl(url);
        assertEquals(null, result.getId());
        assertEquals(null, result.getName());
        verify(restTemplate,times(0)).getForObject(url, OriginResultDto.class);
    }

}