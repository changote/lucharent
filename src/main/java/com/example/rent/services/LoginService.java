package com.example.rent.services;

import com.example.rent.auth.CustomAuthenticationProvider;
import com.example.rent.entity.User;
import com.example.rent.enums.Roles;
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

    @Autowired
    private UserService userService;
	
    public void doLogin(String username, String password) {
        List<SimpleGrantedAuthority> updatedAuthorities = this.brindarAutorities(username);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password, updatedAuthorities);
        Authentication authentic = authenticantionConnector.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentic);
    }

    private List<SimpleGrantedAuthority> brindarAutorities(String username){
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authority;

        User user = userService.findUserByUsername(username);
        if(user != null){
            if(userService.isUserAdmin(user.getUsername())){
                authority = new SimpleGrantedAuthority(Roles.ADMIN.getRolSeteo());
            }else {
                authority = new SimpleGrantedAuthority(Roles.BASIC.getRolSeteo());
            }
            updatedAuthorities.add(authority);
        }

        return updatedAuthorities;
    }
}
