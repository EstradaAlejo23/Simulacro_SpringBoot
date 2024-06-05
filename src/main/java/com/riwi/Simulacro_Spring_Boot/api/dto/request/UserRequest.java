package com.riwi.Simulacro_Spring_Boot.api.dto.request;
import com.riwi.Simulacro_Spring_Boot.util.RoleUsers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "El nombre del usuario debe ser requerido")
    private String name;
    @NotBlank(message = "El email del usuario debe ser requerido")
    @Email
    private String email;
    @NotBlank(message = "La contraseña del usuario debe ser requerido")
    private String password;
    private String fullName;
    private RoleUsers role;
}
