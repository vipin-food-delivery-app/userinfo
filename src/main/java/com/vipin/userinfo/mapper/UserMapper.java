package com.vipin.userinfo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vipin.userinfo.dto.UserDTO;
import com.vipin.userinfo.entity.User;

@Mapper 
public interface UserMapper {
    UserMapper INSTANCE=Mappers.getMapper(UserMapper.class);
    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);
}
