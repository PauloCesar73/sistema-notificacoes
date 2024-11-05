package com.seuprojeto.sistema_notificacoes.notifications.presentation.dto;
import com.seuprojeto.sistema_notificacoes.notifications.model.Notification;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private final Long id;
    private final String titulo;
    private final String corpo;
    private final String tipo;
    private final LocalDateTime dataCriacao;
    private final boolean lida;

    public NotificationResponse(Notification notification, boolean lida) {
        this.id = notification.getId();
        this.titulo = notification.getTituloNotificacao();
        this.corpo = notification.getCorpoNotificacao();
        this.tipo = notification.getTipo();
        this.dataCriacao = notification.getDataCriacao();
        this.lida = lida;
    }

    public NotificationResponse(Notification notification) {
        this(notification, false);
    }
}
