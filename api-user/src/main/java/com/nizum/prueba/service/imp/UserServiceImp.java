package com.nizum.prueba.service.imp;

import com.nizum.prueba.dto.UserInDto;
import com.nizum.prueba.dto.UserOutDto;
import com.nizum.prueba.exceptions.EmailExistException;
import com.nizum.prueba.mapper.PhoneInDtoToPhoneEntity;
import com.nizum.prueba.mapper.UserEntityToUserOutDto;
import com.nizum.prueba.mapper.UserInDtoToUserEntity;
import com.nizum.prueba.persistence.entity.Phone;
import com.nizum.prueba.persistence.entity.User;
import com.nizum.prueba.persistence.reporsitory.PhoneRepository;
import com.nizum.prueba.persistence.reporsitory.UserRepository;
import com.nizum.prueba.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final UserInDtoToUserEntity userInDtoToUserEntity;
    private final UserEntityToUserOutDto userEntityToUserOutDto;
    private final PhoneInDtoToPhoneEntity phoneInDtoToPhoneEntity;
    private final PhoneRepository phoneRepository;

    public UserServiceImp(UserRepository userRepository, UserInDtoToUserEntity userInDtoToUserEntity,
                          UserEntityToUserOutDto userEntityToUserOutDto,
                          PhoneInDtoToPhoneEntity phoneInDtoToPhoneEntity,
                          PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.userInDtoToUserEntity = userInDtoToUserEntity;
        this.userEntityToUserOutDto = userEntityToUserOutDto;
        this.phoneInDtoToPhoneEntity = phoneInDtoToPhoneEntity;
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public UserOutDto createUser(UserInDto userInDto) {

        Optional<User> userExist = userRepository.findByEmail(userInDto.getEmail());
        if (userExist.isPresent()) {
            throw new EmailExistException("El correo ya registrado", HttpStatus.NOT_FOUND);
        }

        User user = userInDtoToUserEntity.map(userInDto);

        userInDto.getPhones().forEach(phoneInDto -> {
                    Phone phone = phoneInDtoToPhoneEntity.map(phoneInDto);
                    phone.setUser(user);
                    phoneRepository.save(phone);
                }
        );

        return userEntityToUserOutDto.map(userRepository.save(user));
    }

    @Override
    public List<UserOutDto> getAllUsers() {
        return userRepository.findAll().stream().map(userEntityToUserOutDto::map).collect(Collectors.toList());
    }
}
