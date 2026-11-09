package com.vipin.userinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vipin.userinfo.dto.UserDTO;
import com.vipin.userinfo.entity.User;
import com.vipin.userinfo.mapper.UserMapper;
import com.vipin.userinfo.repo.UserRepo;

public class UserServiceTest {
    @Mock 
    UserRepo userRepo;

    @InjectMocks 
    UserService userService;

    @BeforeEach 
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    public void testAddUser(){
        UserDTO userDTO=new UserDTO(1, "User 1", "User Password", "User Address", "User City");
        User user=UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userRepo.save(user)).thenReturn(user);

        UserDTO response=userService.addUser(userDTO);

        assertEquals(userDTO, response);

        verify(userRepo,times(1)).save(user);

    }

    @Test 
    public void testFetchUserDetailsByExistingId(){
        Integer id=1;
        UserDTO userDTO=new UserDTO(1, "User 1", "User Password", "User Address", "User City");
        User user=UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userRepo.findById(id)).thenReturn(Optional.of(user));

        ResponseEntity<UserDTO> response=userService.fetchUserDetailsById(id);

        assertEquals(userDTO, response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepo,times(1)).findById(id);

    }

    @Test 
    public void testFetchUserDetailsByNonExistingId(){
        Integer id=1;
        UserDTO userDTO=new UserDTO(1, "User 1", "User Password", "User Address", "User City");
        User user=UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userRepo.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response=userService.fetchUserDetailsById(id);

        assertEquals(null, response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userRepo,times(1)).findById(id);

    }
}
