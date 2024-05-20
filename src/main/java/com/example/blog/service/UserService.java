package com.example.blog.service;

import com.example.blog.dto.UserDTO;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> checkLoginInfoByEmail(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
            }
        }
        else return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> checkLoginInfoByUsername(String username, String password){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
            }
        }
        else return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
    }

    public String registerUser(String username, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        Optional<User> user2 = userRepository.findByUsername(username);
        if(user.isPresent()){
            return "Email already registered";
        }
        if(user2.isPresent()){
            return "Username already exists";
        }
        User userRegister = new User();
        SaveUser(username, email, password, userRegister);
        userRepository.save(userRegister);
        return "Registration Succesful";
    }

    private static void SaveUser(String username, String email, String password, User user) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
    }
}
