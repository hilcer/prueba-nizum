package com.nizum.prueba.mapper;

import com.nizum.prueba.config.JwtService;
import com.nizum.prueba.dto.UserInDto;
import com.nizum.prueba.persistence.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserInDtoToUserEntity implements IMapper<UserInDto, User>{

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    UserInDtoToUserEntity(PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User map(UserInDto input) {

        LocalDateTime now = LocalDateTime.now();
        User user = new User();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setActive(true);
        user.setToken(jwtService.generateToken(user));
        return user;
    }
}
