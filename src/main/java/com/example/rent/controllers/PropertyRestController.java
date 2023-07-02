package com.example.rent.controllers;

import com.example.rent.dto.PropertyDTO;
import com.example.rent.dto.PropertyHomeDTO;
import com.example.rent.dto.RespuestaDTO;
import com.example.rent.dto.UserDTO;
import com.example.rent.entity.Property;
import com.example.rent.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
public class PropertyRestController {
    @Autowired
    private PropertyService propertyService;

//    @Operation(summary = "Consulta por propiedades de una ciudad o para todas")
//    @GetMapping(value = "/propertysbycity", produces = { "application/json" })
//    public ResponseEntity<List<Property>> getPropertysByCity(@RequestParam(value = "city", defaultValue = "") String city) {
//
//        log.info("Se solicitan las propiedades de la ciudad " + city);
//        List<Property>propertyList = propertyService.getAllByCity(city);
//        return ResponseEntity.ok(propertyList);
//    }

    @Operation(summary = "Consulta por propiedades de una ciudad")
    @GetMapping(value = "/propertysforhome", produces = { "application/json" })
    public ResponseEntity<List<PropertyHomeDTO>> getPropertysForHome(@RequestParam(value = "city") Long city) {
        log.info("Se solicitan las propiedades de la ciudad " + city);
        List<PropertyHomeDTO>propertyList = propertyService.getAllByCity(city);
        return ResponseEntity.ok(propertyList);
    }

    @Operation(summary = "Guardado de Propiedad")
    @PostMapping(value = "/setpropierty", produces = { "application/json" })
    public ResponseEntity<?> setPropierty(@AuthenticationPrincipal UserDTO usuario, @RequestBody @Valid PropertyDTO request) {
        /* ****************** INTENTO DE TIME SAVE ****************** */
        try {
            log.info("Se procede guardar el time al usuario: " + usuario.getUsername());
            propertyService.setProperty(request);
        } catch (Exception ex) {
            log.info("Ocurrió un error al guardar el/los time al usuario: " + usuario.getUsername());
            return ResponseEntity.ok(new RespuestaDTO("Error al intentar guardar la tarea."));
        }
        log.info("Exito");
        return ResponseEntity.ok(new RespuestaDTO("Evento guardado correctamente"));
    }

    @Operation(summary = "Consulta por una propiedad")
    @GetMapping(value = "/propertybyid", produces = { "application/json" })
    public ResponseEntity<PropertyDTO> getPropertyById(@RequestParam(value = "propertyid") Long propertyId) {
        log.info("Se solicita la propiedad " + propertyId);
        PropertyDTO propertyDTO = propertyService.getPropertyById(propertyId);
        return ResponseEntity.ok(propertyDTO);
    }

}
