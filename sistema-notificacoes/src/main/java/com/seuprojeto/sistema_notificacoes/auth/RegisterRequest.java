package com.seuprojeto.sistema_notificacoes.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nome;
    private String telefone;
    private String senha;
    private boolean admin;
    private String deviceToken;
}
