package com.demo.shop.persistence.repository;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.domain.repository.UserRepository;
import com.demo.shop.persistence.crud.UserCrudRepository;
import com.demo.shop.persistence.entity.User;
import com.demo.shop.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserCrudRepository crudRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(UserCrudRepository crudRepository, UserMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserDto> findByName(String username) {
        return crudRepository.findByName(username).map(mapper::toUserDto);
    }

    @Override
    public UserDto save(UserDto dto) {
        User user  = mapper.toUser(dto);
        return mapper.toUserDto(crudRepository.save(user));
    }

    @Override
    public Optional<UserDto> findById(long id) {
        return crudRepository.findById(id).map(mapper::toUserDto);
    }

    @Override
    public void delete(long id) {
        crudRepository.deleteById(id);
    }

}
