package example.com.Rotina.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @Email(message = "Email deve ser válido")
        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "O name é obrigatório")
        String name,

        @NotBlank(message = "A senha é obrigatória")
        String password
) {}
