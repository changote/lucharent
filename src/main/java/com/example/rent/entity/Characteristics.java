package com.example.rent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="rent_characteristics")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "characteristics_id")
    private Long characteristicsId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "air_conditioner")
    private boolean airConditioner;

    @Column(name = "calefaction")
    private boolean calefaction;

    @Column(name = "parking")
    private boolean parking;

    @Column(name = "yard")
    private boolean yard;

    @Column(name = "pool")
    private boolean pool;

    @Column(name = "tv")
    private boolean tv;

    @Column(name = "wifi")
    private boolean wifi;

    @Column(name = "pet_friendly")
    private boolean petFriendly;
}
