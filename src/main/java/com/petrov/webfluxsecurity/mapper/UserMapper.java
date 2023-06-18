package com.petrov.webfluxsecurity.mapper;

import com.petrov.webfluxsecurity.dto.UserDto;
import com.petrov.webfluxsecurity.entity.UserEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritConfiguration
    UserEntity map(UserDto userDto);
}
