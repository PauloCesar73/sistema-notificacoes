package com.seuprojeto.sistema_notificacoes.notifications.services.impl;
import com.seuprojeto.sistema_notificacoes.notifications.Exceptions.NotificationNotFoundException;
import com.seuprojeto.sistema_notificacoes.notifications.model.UserNotification;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserNotificationRepository;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.MarkNotificationAsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarkNotificationAsReadServiceImpl implements MarkNotificationAsReadService {

    private final UserNotificationRepository userNotificationRepository;

    @Autowired
    public MarkNotificationAsReadServiceImpl(UserNotificationRepository userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }

    @Transactional
    @Override
    public NotificationResponse markAsRead(Long notificationId) {
        UserNotification userNotification = userNotificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException(notificationId));

        boolean novoEstadoLida = !userNotification.isLida();
        userNotification.setLida(novoEstadoLida);
        userNotificationRepository.save(userNotification);

        return new NotificationResponse(userNotification.getNotificacaoEnviada(), novoEstadoLida);
    }
}