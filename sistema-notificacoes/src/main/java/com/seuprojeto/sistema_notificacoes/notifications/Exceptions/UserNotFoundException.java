package com.seuprojeto.sistema_notificacoes.notifications.Exceptions;
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("Usuário com ID " + userId + " não encontrado");
    }
}
