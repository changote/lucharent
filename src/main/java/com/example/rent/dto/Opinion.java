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
public class Opinion {

    @JsonProperty("propertyId")
    private Long propertyId;

    @JsonProperty("ownerId")
    private Long ownerId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
}
