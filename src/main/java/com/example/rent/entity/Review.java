package com.example.rent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rent_reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stars")
    private int stars;

    @Column(name = "description")
    private String description;

    @Column(name="property_id")
    private Long propertyId;

    @Column(name="date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
}
