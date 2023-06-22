package com.example.rent.controllers;

import com.example.rent.dto.LoginDTO;
import com.example.rent.dto.RespuestaDTO;
import com.example.rent.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Log4j2
@RestController
public class LoginRestController {
	
	@Autowired
	private LoginService loginService;

	@Operation(summary = "Inicio de sesión del usuario")
	@PostMapping(value = "/login", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<RespuestaDTO> login(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO) {
		/* ****************** INTENTO DE LOGIN ****************** */
		try {
			log.info("Se procede autenticar al usuario: " + loginDTO.getUsername());
			HttpSession session = request.getSession(false);
			if(session!=null)
				session.invalidate();
			request.getSession(true);
			loginService.doLogin(loginDTO.getUsername(), loginDTO.getPassword());
		} catch(Exception e) {
			log.info("Ocurrió un error autenticando al usuario: " + loginDTO.getUsername());
			return new ResponseEntity<>(new RespuestaDTO("Credenciales incorrectas"), HttpStatus.UNAUTHORIZED);
		}
		log.info("Se autenticó correctamente al usuario: " + loginDTO.getUsername());
		response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + " ; SameSite=None ; Secure");
		return ResponseEntity.ok(new RespuestaDTO("Login OK"));
    }
	
}
