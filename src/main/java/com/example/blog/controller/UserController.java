package com.example.blog.controller;

import com.example.blog.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
       if(userDTO.getUsername().equals(""))
           return userService.checkLoginInfoByEmail(userDTO.getEmail(), userDTO.getPassword());
       else
           return userService.checkLoginInfoByUsername(userDTO.getUsername(), userDTO.getPassword());
    }

    @PutMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        String result = userService.registerUser(userDTO.getUsername(), userDTO.getEmail(),
                userDTO.getPassword());
        if(result.equals("Registration Successfull")){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }
}
