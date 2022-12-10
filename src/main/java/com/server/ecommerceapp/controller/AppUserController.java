package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.service.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping(value ="/api/v1/user")
public class AppUserController {

    private final AppUserServiceImpl userService;



}

