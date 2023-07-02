package com.example.rent.controllers;

import com.example.rent.dto.CityMiniDTO;
import com.example.rent.entity.City;
import com.example.rent.entity.Property;
import com.example.rent.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class CityRestController {
    @Autowired
    private CityService cityService;
    @Operation(summary = "Consulta por ciudades")
    @GetMapping(value = "/citysbyname", produces = { "application/json" })
    public ResponseEntity<List<CityMiniDTO>> getCitys(@RequestParam(value = "city", defaultValue = "") String city) {

        log.info("Se solicitan las ciudades con el nombre " + city);
        List<CityMiniDTO>cityList = cityService.getAllCitysByName(city);
        return ResponseEntity.ok(cityList);
    }

    @Operation(summary = "Consulta por ciudad")
    @GetMapping(value = "/citybypropertyid", produces = { "application/json" })
    public ResponseEntity<String> getCityById(@RequestParam("propertyId") Long propertyId) {

        log.info("Se solicita la ciudad de la propiedad " + propertyId);
        String city = cityService.getCityById(propertyId);
        return ResponseEntity.ok(city);
    }
}
