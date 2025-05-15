package com.nizum.prueba.mapper;

import com.nizum.prueba.dto.UserOutDto;
import com.nizum.prueba.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserOutDto implements IMapper<User, UserOutDto> {

    @Override
    public UserOutDto map(User input) {
        return UserOutDto.builder()
            .id(input.getId())
            .created(input.getCreated())
            .modified(input.getModified())
            .lastLogin(input.getLastLogin())
            .isActive(input.isActive())
            .token(input.getToken()).build();
    }
}
