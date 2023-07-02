package com.example.rent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rent_property")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="city_id")
    private Long cityId;

    @Column(name = "type")
    private String type;

    @Column(name = "rooms")
    private int numberRooms;

    @Column(name="capacity")
    private int capacity;

    @Column(name="state")
    private boolean state;

    @Column(name="wifi")
    private boolean wifi;

    @Column(name="parking")
    private boolean parking;

    @Column(name="price")
    private double price;

    @Column(name="bathrooms")
    private int bathrooms;

    @Column(name="when_updated")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate whenUpdated;
}
