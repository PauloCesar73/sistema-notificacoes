package com.seuprojeto.sistema_notificacoes.notifications.services.impl;
import com.seuprojeto.sistema_notificacoes.notifications.model.Notification;
import com.seuprojeto.sistema_notificacoes.notifications.model.User;
import com.seuprojeto.sistema_notificacoes.notifications.model.UserNotification;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationRequest;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.repository.NotificationRepository;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserNotificationRepository;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserRepository;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.PushNotificationService;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.CreateNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreateNotificationServiceImpl implements CreateNotificationService {

    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final PushNotificationService pushNotificationService;

    @Autowired
    public CreateNotificationServiceImpl(NotificationRepository notificationRepository,
                                         UserNotificationRepository userNotificationRepository,
                                         UserRepository userRepository,
                                         SimpMessagingTemplate messagingTemplate,
                                         PushNotificationService pushNotificationService) {
        this.notificationRepository = notificationRepository;
        this.userNotificationRepository = userNotificationRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
        this.pushNotificationService = pushNotificationService;
    }

    @Async
    @Transactional
    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        Notification notification = createAndSaveNotification(request);

        if (request.isParaTodos()) {
            notifyAllUsers(notification, request);
        } else {
            notifySingleUser(notification, request);
        }

        return new NotificationResponse(notification);
    }

    private Notification createAndSaveNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setTituloNotificacao(request.getTitulo());
        notification.setCorpoNotificacao(request.getCorpo());
        notification.setTipo(request.getTipo());
        return notificationRepository.save(notification);
    }

    private void notifyAllUsers(Notification notification, NotificationRequest request) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            UserNotification userNotification = createUserNotification(user, notification);
            sendWebSocketNotification(user, notification);
            sendPushNotificationIfAvailable(user, request.getTitulo(), request.getCorpo());
        }
    }

    private void notifySingleUser(Notification notification, NotificationRequest request) {
        User user = userRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        createUserNotification(user, notification);
        sendWebSocketNotification(user, notification);
        sendPushNotificationIfAvailable(user, request.getTitulo(), request.getCorpo());
    }

    private UserNotification createUserNotification(User user, Notification notification) {
        UserNotification userNotification = new UserNotification();
        userNotification.setUsuario(user);
        userNotification.setNotificacaoEnviada(notification);
        return userNotificationRepository.save(userNotification);
    }

    private void sendWebSocketNotification(User user, Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications/" + user.getId(), new NotificationResponse(notification));
    }

    private void sendPushNotificationIfAvailable(User user, String titulo, String corpo) {
        if (user.getDeviceToken() != null) {
            pushNotificationService.sendPushNotification(user.getDeviceToken(), titulo, corpo);
        }
    }
}