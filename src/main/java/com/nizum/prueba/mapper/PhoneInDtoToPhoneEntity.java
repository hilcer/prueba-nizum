package com.nizum.prueba.mapper;

import com.nizum.prueba.dto.PhoneInDto;
import com.nizum.prueba.persistence.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneInDtoToPhoneEntity implements IMapper<PhoneInDto, Phone>{

    @Override
    public Phone map(PhoneInDto input) {
        Phone phone = new Phone();
        phone.setNumber(input.getNumber());
        phone.setCityCode(input.getCityCode());
        phone.setCountryCode(input.getCountryCode());
        return phone;
    }
}
