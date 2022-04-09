package com.xor18.rickAndMortyApi.dto.results.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginCharacterResultDto {
    private String name;
    private String url;
}
