package com.seuprojeto.sistema_notificacoes.auth;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String nome;
    private boolean adm;
    private long id;
    private String deviceToken;
    private String token;
}
