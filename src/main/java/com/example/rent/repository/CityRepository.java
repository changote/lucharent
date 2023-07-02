package com.example.rent.repository;

import com.example.rent.entity.City;
import com.example.rent.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select rc.name " +
            "from rent_property rp " +
            "JOIN rent_city rc ON rp.city_id = rc.city_id "+
            "where rp.property_id = :propertyId",
            nativeQuery = true)
    String findCityByPropertyId(@Param("propertyId") Long propertyId);

    @Query(value = "select rc.name " +
            "from rent_city rc " +
            "where rc.city_id = :cityId",
            nativeQuery = true)
    String findCityByCityId(@Param("cityId") Long cityId);
}
