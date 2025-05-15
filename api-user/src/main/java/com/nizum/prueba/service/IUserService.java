package com.nizum.prueba.service;

import com.nizum.prueba.dto.UserInDto;
import com.nizum.prueba.dto.UserOutDto;

import java.util.List;

public interface IUserService {

    public UserOutDto createUser(UserInDto user);

    public List<UserOutDto> getAllUsers();
}
