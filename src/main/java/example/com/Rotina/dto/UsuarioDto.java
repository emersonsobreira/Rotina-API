package example.com.Rotina.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O nome é obrigatório")
    private String senha;

    public @Email(message = "Email deve ser válido") @NotBlank(message = "O email é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email deve ser válido") @NotBlank(message = "O email é obrigatório") String email) {
        this.email = email;
    }

    public @NotBlank(message = "O nome é obrigatório") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "O nome é obrigatório") String senha) {
        this.senha = senha;
    }

    public @NotBlank(message = "O nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório") String nome) {
        this.nome = nome;
    }





}
