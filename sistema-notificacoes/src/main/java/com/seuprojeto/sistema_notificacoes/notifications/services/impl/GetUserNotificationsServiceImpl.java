package com.seuprojeto.sistema_notificacoes.notifications.services.impl;
import com.seuprojeto.sistema_notificacoes.notifications.Exceptions.UserNotFoundException;
import com.seuprojeto.sistema_notificacoes.notifications.model.UserNotification;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserNotificationRepository;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserRepository;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.GetUserNotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetUserNotificationsServiceImpl implements GetUserNotificationsService {

    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public GetUserNotificationsServiceImpl(UserNotificationRepository userNotificationRepository, UserRepository userRepository) {
        this.userNotificationRepository = userNotificationRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<NotificationResponse> getUserNotifications(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<UserNotification> userNotifications = userNotificationRepository.findByUsuarioId(userId);
        return userNotifications.stream()
                .map(un -> new NotificationResponse(un.getNotificacaoEnviada(), un.isLida()))
                .toList();
    }
}