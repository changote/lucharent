package com.example.rent.services;

import com.example.rent.entity.User;
import com.example.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private UserRepository userRepository;

    public String encryptPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public boolean verifiyPassword(){
        return false;
    }

    public User saveUser(User newUser){
        return this.userRepository.save(newUser);
    }
}
