package com.example.rent.connectors;

import com.example.rent.dto.UserDTO;
import com.example.rent.entity.User;
import com.example.rent.exceptions.AuthenticantionException;
import com.example.rent.repository.UserRepository;
import com.example.rent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

@Service
public class AuthenticantionConnector {

	@Autowired
	private UserService userService;

	public void authenticate(String username, String password) {
		try {
			if (password == null || password.equals("")) {
				throw new RuntimeException();
			}
			User user = userService.findUserByUsername(username);

			if (user == null || !user.getPassword().equals(password)) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new AuthenticantionException("Ocurrió un error al autenticar el usuario " + username, e);
		}
	}

	public UserDTO getInformationUser(String username, String password) {
		try {
			UserDTO usuario = new UserDTO();
			usuario.setUsername(username);

			User user = userService.findUserByUsername(username);
			if (user != null) {
				usuario.setName(user.getName());
				usuario.setLastName(user.getLastName());
				usuario.setEmail(user.getEmail());
				usuario.setUserId(user.getUserId());
			}
			return usuario;
		} catch (Exception ex) {
			throw new AuthenticantionException("Error al intentar obtener la información del usuario: Problemas de conexión a la base de datos.", ex);
		}
	}

}