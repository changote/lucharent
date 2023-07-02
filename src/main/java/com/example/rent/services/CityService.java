package com.example.rent.services;

import com.example.rent.dto.CityMiniDTO;
import com.example.rent.dto.MiniOpinionDTO;
import com.example.rent.dto.PropertyHomeDTO;
import com.example.rent.entity.City;
import com.example.rent.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    private final EntityManager entityManager;

    public CityService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<CityMiniDTO> getAllCitysByName(String city){
        String nativeQuery = "select rc.name, rc.city_id " +
                "from rent_city rc " +
                "where rc.name LIKE :city " +
                "ORDER BY CASE WHEN rc.name LIKE :city THEN 1 ELSE 2 END, rc.name";

        Query query = entityManager.createNativeQuery(nativeQuery);

        query.setParameter("city", "%" + city + "%");

        List<CityMiniDTO> citys = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Object[]> auxObject = query.getResultList();

        for (Object[] result : auxObject) {
            CityMiniDTO cityMiniDTO = new CityMiniDTO();
            cityMiniDTO.setName(result[0].toString());
            cityMiniDTO.setCityId(Long.valueOf(result[1].toString()));
            citys.add(cityMiniDTO);
        }
        return citys;
    }

    public String getCityById(Long propertyId){
        return cityRepository.findCityByPropertyId(propertyId);
    }
}
