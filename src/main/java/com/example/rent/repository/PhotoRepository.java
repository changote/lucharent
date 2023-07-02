package com.example.rent.repository;

import com.example.rent.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query(value = "select rp.url " +
            "from rent_photo rp " +
            "where rp.property_id = :propertyId ",
            nativeQuery = true)
    List<String> findAllByPropertyId(@Param("propertyId") Long propertyId);
}
