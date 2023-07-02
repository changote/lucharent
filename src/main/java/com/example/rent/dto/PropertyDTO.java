package com.example.rent.dto;

import com.example.rent.entity.Photo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    @JsonProperty("propertyId")
    private Long propertyId;

    @JsonProperty("ownerId")
    private Long ownerId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("city_id")
    private Long cityId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("rooms")
    private int numberRooms;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("state")
    private Boolean state;

    @JsonProperty("wifi")
    private Boolean wifi;

    @JsonProperty("parking")
    private Boolean parking;

    @JsonProperty("bathrooms")
    private int bathrooms;

    @JsonProperty("whenUpdated")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate whenUpdated;

    @JsonProperty("photo")
    private List<String> photoList;

    @JsonProperty("opinion")
    private MiniOpinionDTO opinion;

    @JsonProperty("price")
    private double price;
}