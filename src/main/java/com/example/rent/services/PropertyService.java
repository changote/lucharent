package com.example.rent.services;

import com.example.rent.dto.MiniOpinionDTO;
import com.example.rent.dto.PropertyDTO;
import com.example.rent.dto.PropertyHomeDTO;
import com.example.rent.entity.Property;
import com.example.rent.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {
    private final EntityManager entityManager;
    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Property> getAllByCity(String city){
        return propertyRepository.findAllByCity(city);
    }

    public void setProperty(PropertyDTO newProperty){
        Property property = new Property();
        property.setAddress(newProperty.getAddress());
        property.setCity(newProperty.getCity());
        property.setBathrooms(newProperty.getBathrooms());
        property.setPhone(newProperty.getPhone());
        property.setParking(newProperty.getParking());
        property.setState(newProperty.getState());
        property.setCapacity(newProperty.getCapacity());
        property.setNumberRooms(newProperty.getNumberRooms());
        property.setTitle(newProperty.getTitle());
        property.setWhenUpdated(newProperty.getWhenUpdated());
        property.setDescription(newProperty.getDescription());
        property.setType(newProperty.getType());
        property.setOwnerId(newProperty.getOwnerId());
        property.setWifi(newProperty.getWifi());

        propertyRepository.save(property);
    }

    public List<PropertyHomeDTO> getAllForHome(){

        String nativeQuery = "SELECT rp.title, rp.description, rp.address, rp.city, rp.type, rp.rooms, rp.capacity, rp.bathrooms, rp.price, AVG(rr.stars) AS prom_stars, COUNT(rr.review_id) AS cant_reviews " +
                "FROM rent_property rp " +
                "JOIN rent_reviews rr ON rr.property_id = rp.property_id " +
                "WHERE rp.state = 1 " +
                "GROUP BY rp.property_id";

        Query query = entityManager.createNativeQuery(nativeQuery);

        List<PropertyHomeDTO> propertyHomeDTOS = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Object[]> auxObject = query.getResultList();

        for (Object[] result : auxObject) {
            MiniOpinionDTO miniOpinionDTO = new MiniOpinionDTO();
            PropertyHomeDTO propertyHomeDTO = new PropertyHomeDTO();
            propertyHomeDTO.setTitle(result[0].toString());
            propertyHomeDTO.setDescription(result[1].toString());
            propertyHomeDTO.setAddress(result[2].toString());
            propertyHomeDTO.setCity(result[3].toString());
            propertyHomeDTO.setType(result[4].toString());
            propertyHomeDTO.setNumberRooms(Integer.parseInt(result[5].toString()));
            propertyHomeDTO.setCapacity(Integer.parseInt(result[6].toString()));
            propertyHomeDTO.setBathrooms(Integer.parseInt(result[7].toString()));
            propertyHomeDTO.setPrice(Double.parseDouble(result[8].toString()));
            miniOpinionDTO.setStars(Double.parseDouble(result[9].toString()));
            miniOpinionDTO.setCantOpinion(Integer.parseInt(result[10].toString()));

            propertyHomeDTO.setOpinion(miniOpinionDTO);


            propertyHomeDTOS.add(propertyHomeDTO);
        }

        return propertyHomeDTOS;
    }


}
