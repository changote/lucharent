package com.example.rent.repository;

import com.example.rent.entity.Property;
import com.example.rent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query(value = "select * " +
            "from rent_property rp " +
            "where rp.city LIKE %:city%",
            nativeQuery = true)
    List<Property> findAllByCity(@Param("city") String city);


    @Query(value = "select rp.title ,rp.description ,rp.address ,rp.city ,rp.type, rp.rooms ,rp.capacity ,rp.bathrooms , AVG(rr.stars) AS promedio_estrellas, rp.time " +
            "    from rent_property rp " +
            "    join rent_reviews rr " +
            "    where rp.state = 1 and rr.property_id = rp.property_id " +
            "    group by rp.property_id",
            nativeQuery = true)
    List<Property> findAllToHome (@Param("city") String city);

//    @Query(value = "select * " +
//            "from rent_property rp " +
//            "where rp.property_id = :propertyId",
//            nativeQuery = true)
    Property findByPropertyId(@Param("propertyId") Long propertyId);


}
