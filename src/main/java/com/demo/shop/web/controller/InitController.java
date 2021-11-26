package com.demo.shop.web.controller;

import com.demo.shop.common.ApiMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiMapping.HOME)
public class InitController {

    @GetMapping
    public String getHome(){
        return ApiMapping.HOME;
    }
}
