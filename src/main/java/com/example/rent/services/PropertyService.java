package com.example.rent.services;

import com.example.rent.dto.MiniOpinionDTO;
import com.example.rent.dto.PropertyDTO;
import com.example.rent.dto.PropertyHomeDTO;
import com.example.rent.entity.Property;
import com.example.rent.repository.CityRepository;
import com.example.rent.repository.PhotoRepository;
import com.example.rent.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {
    private final EntityManager entityManager;
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private CityRepository cityRepository;

    public PropertyService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    public List<Property> getAllByCity(String city){
//        return propertyRepository.findAllByCity(city);
//    }

    public void setProperty(PropertyDTO newProperty){
        Property property = new Property();
        property.setAddress(newProperty.getAddress());
        property.setCityId(newProperty.getCityId());
        property.setBathrooms(newProperty.getBathrooms());
        property.setState(newProperty.getState());
        property.setCapacity(newProperty.getCapacity());
        property.setNumberRooms(newProperty.getNumberRooms());
        property.setTitle(newProperty.getTitle());
        property.setWhenUpdated(newProperty.getWhenUpdated());
        property.setDescription(newProperty.getDescription());
        property.setType(newProperty.getType());
        property.setOwnerId(newProperty.getOwnerId());

        propertyRepository.save(property);
    }

    public List<PropertyHomeDTO> getAllByCity(Long city){

        String nativeQuery = "select rp.title,rp.description,rp.address, rp.city_id, rp.type, rp.rooms, rp.capacity, rp.bathrooms, rp.price, MIN(rp2.url) AS first_url, promedio_estrellas, " +
                "cantidad_reviews, rp.property_id, rp.time " +
                "FROM(SELECT rp.property_id, AVG(stars) AS promedio_estrellas, COUNT(review_id) AS cantidad_reviews " +
                "        FROM rent_property rp " +
                "            JOIN rent_reviews rr ON rp.property_id = rr.property_id " +
                "        WHERE rp.state = 1 AND rp.city_id = :city " +
                "        GROUP BY rp.property_id " +
                "    ) AS rr " +
                "    JOIN rent_property rp ON rr.property_id = rp.property_id " +
                "    JOIN rent_photo rp2 ON rp.property_id = rp2.property_id " +
                "GROUP BY rp.property_id;";

        Query query = entityManager.createNativeQuery(nativeQuery);

        query.setParameter("city",city);

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
            propertyHomeDTO.setPrice(setPriceDTO(Double.parseDouble(result[8].toString()),result[13].toString()));
            propertyHomeDTO.setPhoto(result[9].toString());
            miniOpinionDTO.setStars(Math.round(Double.parseDouble(result[10].toString())));
            miniOpinionDTO.setCantOpinion(Integer.parseInt(result[11].toString()));
            propertyHomeDTO.setPropertyId(Long.valueOf(result[12].toString()));

            propertyHomeDTO.setOpinion(miniOpinionDTO);

            propertyHomeDTOS.add(propertyHomeDTO);
        }

        return propertyHomeDTOS;
    }

    public String setPriceDTO(double price, String time){
        int priceInt = (int) price;
        switch(time){
            case "day":
                return "$" + priceInt + " por dia";
            case "week":
                return "$" + priceInt + " por semana";
            case "month":
                return "$" + priceInt + " por mes";
            case "fortnight":
                return "$" + priceInt + " por quincena";
        }
        return "Sin precio";
    }

    public PropertyDTO getPropertyById(Long propertyId){
        TypedQuery<Object[]> query = entityManager.createQuery("SELECT AVG(rr.stars) AS promStars, COUNT(rr.reviewId) AS cantReviews " +
                "        FROM Property rp " +
                "        JOIN Review rr ON rp.propertyId = rr.propertyId " +
                "        WHERE rp.propertyId = :propertyId", Object[].class);

        query.setParameter("propertyId",propertyId);

        PropertyDTO propertyDTO = propertyToDTO(propertyRepository.findByPropertyId(propertyId));
        System.out.println(propertyDTO.getPropertyId());
        propertyDTO.setPhotoList(photoRepository.findAllByPropertyId(propertyId));
        Object[] result = query.getSingleResult();
        MiniOpinionDTO miniOpinionDTO = new MiniOpinionDTO(0,0);

        if (result.length != 0) {

            miniOpinionDTO.setStars(Math.round(Double.parseDouble(result[0].toString())));
            miniOpinionDTO.setCantOpinion(Integer.parseInt(result[1].toString()));
        }
        propertyDTO.setOpinion(miniOpinionDTO);
        return propertyDTO;
    }

    public PropertyDTO propertyToDTO(Property property){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPropertyId(property.getPropertyId());
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setOwnerId(property.getOwnerId());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setNumberRooms(property.getNumberRooms());
        propertyDTO.setBathrooms(property.getBathrooms());
        propertyDTO.setCity(cityRepository.findCityByCityId(property.getCityId()));
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setCapacity(property.getCapacity());
        propertyDTO.setType(property.getType());
        propertyDTO.setPrice(setPriceDTO(property.getPrice(),property.getTime()));
        propertyDTO.setWhenUpdated(property.getWhenUpdated());

        return propertyDTO;
    }
}
