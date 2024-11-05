package com.seuprojeto.sistema_notificacoes.notifications.services.interfaces;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;

public interface MarkNotificationAsReadService {
    NotificationResponse markAsRead(Long notificationId);
}
