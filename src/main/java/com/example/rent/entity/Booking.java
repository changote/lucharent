package com.example.rent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rent_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name="start_time")
    private LocalDate startTime;

    @Column(name = "end_time")
    private LocalDate endTime;

    @Column(name="state")
    private String state;

    @Column(name="price")
    private double price;

    @Column(name="comments")
    private String comments;

}
