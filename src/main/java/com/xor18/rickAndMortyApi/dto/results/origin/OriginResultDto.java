package com.xor18.rickAndMortyApi.dto.results.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Contiene la data que retorna el fetch al api de Origin by id
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginResultDto {
    private Long id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
    private String created;
}
