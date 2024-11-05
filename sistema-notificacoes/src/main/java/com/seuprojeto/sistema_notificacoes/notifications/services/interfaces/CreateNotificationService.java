package com.seuprojeto.sistema_notificacoes.notifications.services.interfaces;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationRequest;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;

public interface CreateNotificationService {
    NotificationResponse createNotification(NotificationRequest request);
}
