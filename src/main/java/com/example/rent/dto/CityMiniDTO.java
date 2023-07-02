package com.example.rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityMiniDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("cityId")
    private Long cityId;
}
