package com.example.rent.controllers;

import com.example.rent.dto.PropertyDTO;
import com.example.rent.dto.RespuestaDTO;
import com.example.rent.dto.UserDTO;
import com.example.rent.entity.User;
import com.example.rent.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Guardado de nuevo usuario")
    @PostMapping(value = "/createuser", produces = { "application/json" })
    public ResponseEntity<?> setUser(@RequestBody @Valid User newUser) {
        /* ****************** INTENTO DE USER SAVE ****************** */
        try {
            log.info("Se procede guardar al usuario: " + newUser.getUsername());
            userService.saveNewUser(newUser);
        } catch (Exception ex) {
            log.info("Ocurrió un error al guardar al usuario: " + newUser.getUsername());
            return ResponseEntity.ok(new RespuestaDTO("Error al intentar guardar."));
        }
        log.info("Exito");
        return ResponseEntity.ok(new RespuestaDTO("Evento guardado correctamente"));
    }
}
