package com.seuprojeto.sistema_notificacoes.notifications.services.interfaces;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;

import java.util.List;

public interface GetUserNotificationsService {
    List<NotificationResponse> getUserNotifications(Long userId);
}
