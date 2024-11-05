package com.seuprojeto.sistema_notificacoes.notifications.presentation.dto;
import lombok.Data;

@Data
public class NotificationRequest {

    private Long usuarioId;

    private String titulo;

    private String corpo;

    private String tipo;

    private boolean paraTodos;
}
