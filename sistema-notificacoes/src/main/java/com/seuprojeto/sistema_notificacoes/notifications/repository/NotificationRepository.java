package com.seuprojeto.sistema_notificacoes.notifications.repository;
import com.seuprojeto.sistema_notificacoes.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
