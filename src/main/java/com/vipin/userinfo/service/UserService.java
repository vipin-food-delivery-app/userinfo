package com.vipin.userinfo.service;

import com.vipin.userinfo.controller.UserController;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vipin.userinfo.dto.UserDTO;
import com.vipin.userinfo.entity.User;
import com.vipin.userinfo.mapper.UserMapper;
import com.vipin.userinfo.repo.UserRepo;

@Service 
public class UserService {
    private UserRepo userRepo;
    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    public UserDTO addUser(UserDTO userDTO) {
        User savedUser=userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }
    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer id) {
      Optional<User> fetchedUser=userRepo.findById(id);
      if(fetchedUser.isPresent()){
        return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()),HttpStatus.OK);
      }
      return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);

    }

}
