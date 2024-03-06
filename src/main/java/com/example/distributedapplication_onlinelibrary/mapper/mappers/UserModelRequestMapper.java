package com.example.distributedapplication_onlinelibrary.mapper.mappers;

import com.example.distributedapplication_onlinelibrary.mapper.dto.UserDto;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelRequestMapper {
    UserDto userModelToUserDto(UserModel user);

    UserModel userDtoToUserModel(UserDto appUserDto);
}
