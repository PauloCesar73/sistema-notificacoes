package com.seuprojeto.sistema_notificacoes.auth;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{10,15}", message = "O telefone deve conter apenas números e ter entre 10 a 15 dígitos")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 a 20 caracteres")
    private String senha;
}
