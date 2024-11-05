package com.seuprojeto.sistema_notificacoes.notifications.services.impl;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.PushNotificationService;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    @Override
    public String sendPushNotification(String token, String titulo, String corpo) {
        Notification notification = Notification.builder()
                .setTitle(titulo)
                .setBody(corpo)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            return FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();  // Restaura o estado de interrupção
            e.printStackTrace();
            return "Erro ao enviar notificação push: " + e.getMessage();
        }
    }
}