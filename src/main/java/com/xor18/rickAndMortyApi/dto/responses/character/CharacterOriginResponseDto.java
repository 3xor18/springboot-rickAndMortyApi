package com.xor18.rickAndMortyApi.dto.responses.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CharacterOriginResponseDto {

    private String name;
    private String url;
    private String dimension;
    private List<String> residents=new ArrayList<>();
}
