package com.demo.shop.domain.repository;

import com.demo.shop.domain.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findByName(String username);
    UserDto save(UserDto dto);
    Optional<UserDto> findById(long id);
    void delete(long id);
    Optional<UserDto> update(long id, UserDto dto);
}
