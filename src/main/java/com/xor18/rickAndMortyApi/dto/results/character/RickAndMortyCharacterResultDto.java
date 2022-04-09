package com.xor18.rickAndMortyApi.dto.results.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyCharacterResultDto {

    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginCharacterResultDto origin;
    private LocationCharacterResultDto location;
    private String image;
    private List<String> episode=new ArrayList<>();
    private String url;
    private String created;

}
