package com.vipin.userinfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vipin.userinfo.dto.UserDTO;
import com.vipin.userinfo.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping ("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser=userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }   

    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<UserDTO> fetchUserDetailsById(@PathVariable Integer id) {
        return userService.fetchUserDetailsById(id);
    }
    
}
