package com.example.rent.auth;

import com.example.rent.connectors.AuthenticantionConnector;
import com.example.rent.dto.UserDTO;
import com.example.rent.exceptions.AuthenticantionException;
import com.example.rent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationManager {

	@Autowired
	private AuthenticantionConnector authenticationConnector;

	@Autowired
	private UserService userService;

	/**
	 * Toma el obj Authentication que recibe como parametro (el cual contiene los datos de un usuario), y busca los permisos (authorities) que tiene
	 * dicho usuario.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		if (password == null || password.equals(""))
			throw new BadCredentialsException("1");

		try {
			authenticationConnector.authenticate(username, password);
		} catch (AuthenticantionException e) {
			throw new BadCredentialsException("3", e);
		}
		
		/*
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		Boolean esAdmin = auth.contains(new SimpleGrantedAuthority(RolesEnum.USER_BACKOFFICE.getRolSeteo()));
		Usuario usuario = usuarioService.crearUsuarioEnCasoDeQueNoExista(username, password, esAdmin);
		*/
		UserDTO userDTO = authenticationConnector.getInformationUser(username, password);
		return new UsernamePasswordAuthenticationToken(userDTO, "password", null);
	}
}