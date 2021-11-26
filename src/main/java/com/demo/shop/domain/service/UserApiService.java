package com.demo.shop.domain.service;

import com.demo.shop.domain.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserApiService implements UserDetailsService {

    private final UserService userService;

    public UserApiService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> existUser = userService.findByName(username);
        return existUser
                .map(userDto -> new User(userDto.getName(), "{noop}"+userDto.getPassword(), new ArrayList<>()))
                .orElse(null);
    }
}
