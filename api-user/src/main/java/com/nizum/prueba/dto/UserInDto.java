package com.nizum.prueba.dto;

import com.nizum.prueba.context.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInDto {
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank(message = "El correo no puede ser vacio")
    @Email(message = "El correo electrónico no es válido")
    private String email;
    @NotBlank(message = "La contraseña no puede vacio")
    @ValidPassword(message = "La contraseña es invalida")
    private String password;
    private List<PhoneInDto> phones;
}
