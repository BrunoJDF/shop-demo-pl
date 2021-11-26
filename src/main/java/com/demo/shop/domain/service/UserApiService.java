package com.demo.shop.domain.service;

import com.demo.shop.domain.dto.UserDto;
import com.demo.shop.persistence.repository.UserRepositoryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserApiService implements UserDetailsService {

    private final UserRepositoryImpl repository;

    public UserApiService(UserRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> existUser = findByName(username);
        return existUser
                .map(userDto -> new User(userDto.getName(), "{noop}"+userDto.getPassword(), new ArrayList<>()))
                .orElse(null);
    }

    public Optional<UserDto> findByName(String username){
        return repository.findByName(username);
    }
}
