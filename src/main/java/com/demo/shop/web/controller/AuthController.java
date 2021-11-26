package com.demo.shop.web.controller;

import com.demo.shop.common.ApiMapping;
import com.demo.shop.common.auth.AuthRequest;
import com.demo.shop.common.auth.AuthResponse;
import com.demo.shop.domain.service.UserApiService;
import com.demo.shop.web.config.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMapping.AUTH)
public class AuthController {

    private final AuthenticationManager manager;
    private final UserApiService apiService;
    private final JwtUtils jwt;

    public AuthController(AuthenticationManager manager, UserApiService apiService, JwtUtils jwt) {
        this.manager = manager;
        this.apiService = apiService;
        this.jwt = jwt;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest request){
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
            UserDetails userDetails = apiService.loadUserByUsername(request.getUser());
            if(userDetails == null){
                throw new BadCredentialsException("userDetail is null");
            }
            String jwt = this.jwt.generateToken(userDetails);
            return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
