package com.xor18.rickAndMortyApi.services.impl;

import com.xor18.rickAndMortyApi.dto.responses.character.CharacterOriginResponseDto;
import com.xor18.rickAndMortyApi.dto.responses.character.CharacterResponseDto;
import com.xor18.rickAndMortyApi.dto.results.character.RickAndMortyCharacterResultDto;
import com.xor18.rickAndMortyApi.dto.results.origin.OriginResultDto;
import com.xor18.rickAndMortyApi.exceptions.AppInternalServerErrorException;
import com.xor18.rickAndMortyApi.services.CharacterService;
import com.xor18.rickAndMortyApi.services.OriginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class CharacterServiceImpl implements CharacterService {

    @Value("${url.rick_and_morty.characters}")
    private String urlApiFindCharacterById;

    private final RestTemplate restTemplate;

    private final OriginService originService;

    public CharacterServiceImpl(RestTemplate restTemplate, OriginService originService) {
        this.restTemplate = restTemplate;
        this.originService = originService;
    }

    @Override
    public ResponseEntity<CharacterResponseDto> findById(Long idCharacter) throws AppInternalServerErrorException {
        RickAndMortyCharacterResultDto characterInfo = fetchApi(idCharacter);
        String originUrl=characterInfo.getOrigin().getUrl();
        OriginResultDto locationInfo= originService.findOriginByUrl(originUrl);
        CharacterResponseDto dto=makeDtoForResponse(characterInfo,locationInfo);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    private CharacterResponseDto makeDtoForResponse(RickAndMortyCharacterResultDto characterInfo, OriginResultDto locationInfo){
        CharacterResponseDto characterInfoDto=new CharacterResponseDto();
        characterInfoDto.setId(characterInfo.getId());
        characterInfoDto.setName(characterInfo.getName());
        characterInfoDto.setStatus(characterInfo.getStatus());
        characterInfoDto.setSpecies(characterInfo.getSpecies());
        characterInfoDto.setType(characterInfo.getType());
        characterInfoDto.setEpisode_count(characterInfo.getEpisode().size());
        characterInfoDto.setOrigin(makeDtoOrigin(locationInfo));
        return  characterInfoDto;
    }

    private CharacterOriginResponseDto makeDtoOrigin(OriginResultDto locationInfo){
        CharacterOriginResponseDto dto=new CharacterOriginResponseDto();
        dto.setName(locationInfo.getName());
        dto.setUrl(locationInfo.getUrl());
        dto.setDimension(locationInfo.getDimension());
        dto.setResidents(locationInfo.getResidents());
        return dto;
    }

    /**
     * Metodo para hacerle la fetch al api y obtener al info del character
     * @param idCharacter  (Long) id del caracter a buscar
     * @return RickAndMortyCharacterResultDto  esto contiene la Data del Character
     * @throws AppInternalServerErrorException
     */
    private RickAndMortyCharacterResultDto fetchApi(Long idCharacter) throws AppInternalServerErrorException {
        try {
            return restTemplate.getForObject(urlApiFindCharacterById + idCharacter, RickAndMortyCharacterResultDto.class);
        } catch (Error e) {
            log.error("[Error] fetchApi");
            log.error(e.getMessage());
            throw new AppInternalServerErrorException("Error en el fecth a la api para buscar un character por id");
        }
    }
}
