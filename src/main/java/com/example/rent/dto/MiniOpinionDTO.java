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
public class MiniOpinionDTO {

    @JsonProperty("cantOpinion")
    private int cantOpinion;

    @JsonProperty("stars")
    private double stars;
}
