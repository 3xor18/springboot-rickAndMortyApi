package com.xor18.rickAndMortyApi.dto.responses.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private int episode_count;
    private CharacterOriginResponseDto origin;
}
