package com.seuprojeto.sistema_notificacoes.notifications.repository;
import com.seuprojeto.sistema_notificacoes.notifications.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByUsuarioId(Long usuarioId);
}
