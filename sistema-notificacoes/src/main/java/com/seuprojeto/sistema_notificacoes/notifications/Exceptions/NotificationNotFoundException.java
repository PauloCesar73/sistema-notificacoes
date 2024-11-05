package com.seuprojeto.sistema_notificacoes.notifications.Exceptions;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException(String message) {
        super(message);
    }

    public NotificationNotFoundException(Long notificationId) {
        super("Notificação com ID " + notificationId + " não encontrada");
    }
}
