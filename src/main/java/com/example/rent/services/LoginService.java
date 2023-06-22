package com.example.rent.services;

import com.example.rent.auth.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

	@Autowired
	private CustomAuthenticationProvider authenticantionConnector;
	
    public void doLogin(String username, String password) {
        List<SimpleGrantedAuthority> updatedAuthorities = this.brindarAutorities(username);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password, updatedAuthorities);
        Authentication authentic = authenticantionConnector.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentic);
    }
    
    private List<SimpleGrantedAuthority> brindarAutorities(String username){
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();

        return updatedAuthorities;
    }
}
