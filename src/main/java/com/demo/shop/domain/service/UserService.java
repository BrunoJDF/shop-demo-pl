package com.demo.shop.domain.service;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.persistence.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepositoryImpl repository;

    public UserService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    public Optional<UserDto> findById(long id){
        return repository.findById(id);
    }

    public UserDto save(UserDto dto){
        return repository.save(dto);
    }

    public boolean delete(long id){
        return repository.findById(id).map(userDto -> {
            repository.delete(userDto.getId());
            return true;
        }).orElse(false);
    }

    public Optional<UserDto> findByName(String username){
        return repository.findByName(username);
    }

    public Optional<UserDto> updateUser(long id, UserDto dto){
        return repository.update(id, dto);
    }

}
