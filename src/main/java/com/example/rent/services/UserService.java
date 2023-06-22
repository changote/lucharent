package com.example.rent.services;

import com.example.rent.entity.User;
import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
