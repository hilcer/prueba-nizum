package com.nizum.prueba.service;

import com.nizum.prueba.dto.PhoneInDto;
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
import com.nizum.prueba.service.imp.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@SpringBootTest
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserInDtoToUserEntity userInDtoToUserEntity;
    @Mock
    private UserEntityToUserOutDto userEntityToUserOutDto;
    @Mock
    private PhoneInDtoToPhoneEntity phoneInDtoToPhoneEntity;
    @Mock
    private PhoneRepository phoneRepository;

    private User expectedUserEntity;
    private UserOutDto expectedUserOutDto;
    private PhoneInDto phoneInDtoSimulated;
    private Phone phoneSimulated;
    private UserInDto userInDtoSimulated;

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        expectedUserEntity = new User();
        expectedUserEntity.setId(uuid);
        expectedUserEntity.setEmail("test@test.com");
        expectedUserEntity.setPassword("123456");
        expectedUserEntity.setName("Name Test");
        expectedUserEntity.setActive(true);
        expectedUserEntity.setCreated(time);
        expectedUserEntity.setModified(time);
        expectedUserEntity.setLastLogin(time);
        expectedUserEntity.setToken("dfsdaasfasfasfasffsadg");

        expectedUserOutDto = UserOutDto.builder()
                .id(uuid)
                .created(time)
                .lastLogin(time)
                .isActive(true)
                .token("dfsdaasfasfasfasffsadg").build();

        phoneSimulated = new Phone();
                phoneSimulated.setNumber("70503425");
                phoneSimulated.setCityCode("123");
                phoneSimulated.setCountryCode("509");

        phoneInDtoSimulated = PhoneInDto.builder()
                .number("70503425")
                .cityCode("123")
                .countryCode("509").build();

        Phone phoneSimulated = new Phone();
        phoneSimulated.setNumber("70503425");
        phoneSimulated.setCityCode("123");
        phoneSimulated.setCountryCode("509");

        List<PhoneInDto> phonesSimulated = new ArrayList<>();
        phonesSimulated.add(phoneInDtoSimulated);
        userInDtoSimulated = UserInDto.builder()
                .email("test@test.com")
                .password("123456")
                .name("Name Test")
                .phones(phonesSimulated).build();

    }

    @DisplayName("Dado un usuario, este debe ser creado de manera exitosa")
    @Test
    void createUser() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUserEntity);
        Mockito.when(userInDtoToUserEntity.map(Mockito.any(UserInDto.class))).thenReturn(expectedUserEntity);
        Mockito.when(phoneInDtoToPhoneEntity.map(Mockito.any(PhoneInDto.class))).thenReturn(phoneSimulated);
        Mockito.when(userEntityToUserOutDto.map(Mockito.any(User.class))).thenReturn(expectedUserOutDto);
        UserOutDto result = userService.createUser(userInDtoSimulated);

        Assertions.assertNotNull(expectedUserOutDto);
        Assertions.assertEquals(expectedUserOutDto.getId(), result.getId());
        Assertions.assertEquals(expectedUserOutDto.getToken(), result.getToken());
    }

    @DisplayName("Dado un usuario con email repetido, este debe lanzar una excepcion ")
    @Test
    void createUser_WithRepetEmail() {

        String email = "test@test.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUserEntity));
        Assertions.assertThrows(EmailExistException.class, () -> {
            try {
                userService.createUser(userInDtoSimulated);
            } catch (EmailExistException e) {
                throw e;
            }
        });
    }

}
