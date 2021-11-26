package com.demo.shop.persistence.mapper;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto dto);
}
