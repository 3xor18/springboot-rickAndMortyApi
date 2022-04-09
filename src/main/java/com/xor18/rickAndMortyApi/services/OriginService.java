package com.xor18.rickAndMortyApi.services;

import com.xor18.rickAndMortyApi.dto.results.origin.OriginResultDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;

public interface OriginService {

    /**
     * Se le pasa la url que viene en el objeto del character, esta hace el fetch y retorna la data del origin
     * @param name
     * @return OriginResultDto data del origin en la url
     * @throws AppInternalServerErrorException
     */
    public OriginResultDto findOriginByUrl(String name) throws AppInternalServerErrorException;
}
