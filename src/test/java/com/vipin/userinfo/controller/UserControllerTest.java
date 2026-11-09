package com.vipin.userinfo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jspecify.annotations.Nullable;
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
import com.vipin.userinfo.service.UserService;

public class UserControllerTest {
    @Mock 
    UserService userService;

    @InjectMocks 
    UserController userController;

    @BeforeEach 
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    public void testAddUser(){
        UserDTO userDTO=new UserDTO(1, "User 1", "User Password", "User Address", "User City");
        User user=UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userService.addUser(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> repsonse=userController.addUser(userDTO);


        assertEquals(userDTO, repsonse.getBody());

        assertEquals(HttpStatus.CREATED, repsonse.getStatusCode());
        verify(userService,times(1)).addUser(userDTO);
    }

    @Test 
    public void testFetchUserDetailsById(){
        Integer id=1;
        UserDTO userDTO=new UserDTO(1, "User 1", "User Password", "User Address", "User City");
        User user=UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userService.fetchUserDetailsById(id)).thenReturn(new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK));

        ResponseEntity<UserDTO> repsonse=userController.fetchUserDetailsById(id);

        assertEquals(userDTO, repsonse.getBody());

        verify(userService,times(1)).fetchUserDetailsById(id);
    }

}
