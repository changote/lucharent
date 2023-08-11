package com.example.rent.services;

import com.example.rent.entity.User;
import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void saveNewUser(User newUser){
        newUser.setPassword(passwordService.encryptPassword(newUser.getPassword()));
        if(newUser.getPhoto() == null){
            newUser.setPhoto("asd");
        }
        passwordService.saveUser(newUser);
    }

    public boolean isUserAdmin(String username) {
        User user = userRepository.findUserByUsername(username);
        return user.getLevel() == 11;
    }
}
