package com.xor18.rickAndMortyApi.services.impl;

import com.xor18.rickAndMortyApi.dto.results.origin.OriginResultDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import com.xor18.rickAndMortyApi.services.OriginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class OriginServiceImpl implements OriginService {

    private final RestTemplate restTemplate;

    public OriginServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OriginResultDto findOriginByUrl(String url) throws AppInternalServerErrorException {
        try {
            if(url.isEmpty()){
                return new OriginResultDto();
            }
            return restTemplate.getForObject(url, OriginResultDto.class);
        } catch (Error e) {
            log.error("[Error] findOriginByUrl");
            log.error(e.getMessage());
            throw new AppInternalServerErrorException("Error en el fecth a la api para buscar el origin");
        }
    }

}
