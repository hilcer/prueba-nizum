package com.nizum.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImp userService;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void createUser_Success() throws Exception {

        BDDMockito.given(userService.createUser(Mockito.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userInDtoSimulated)));

        response.andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }

}
