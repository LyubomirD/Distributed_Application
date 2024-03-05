package com.example.distributedapplication_onlinelibrary.mapper.mappers;

import com.example.distributedapplication_onlinelibrary.mapper.dto.UserModelDto;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelRequestMapper {
    UserModelDto userModelToUserModelDto(UserModel user);

    UserModel userModelDtoToUserModel(UserModelDto appUserDto);
}

