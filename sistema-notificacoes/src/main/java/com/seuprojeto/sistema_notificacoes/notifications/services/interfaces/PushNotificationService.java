package com.seuprojeto.sistema_notificacoes.notifications.services.interfaces;

public interface PushNotificationService {
    String sendPushNotification(String token, String titulo, String corpo);
}
