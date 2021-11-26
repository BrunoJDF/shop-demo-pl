package com.demo.shop.persistence.mapper;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role"),
    })
    UserDto toUserDto(User user);

    @InheritInverseConfiguration
    User toUser(UserDto dto);
}
