package com.demo.shop.persistence.mapper;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.persistence.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
