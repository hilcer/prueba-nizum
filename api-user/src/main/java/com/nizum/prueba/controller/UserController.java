package com.nizum.prueba.controller;

import com.nizum.prueba.dto.UserInDto;
import com.nizum.prueba.dto.UserOutDto;
import com.nizum.prueba.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private ResponseEntity<UserOutDto> createUser(@Valid @RequestBody UserInDto userInDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userInDto));
    }

    @GetMapping
    private List<UserOutDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
