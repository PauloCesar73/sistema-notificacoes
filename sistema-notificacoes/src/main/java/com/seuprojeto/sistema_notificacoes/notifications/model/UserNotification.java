package com.seuprojeto.sistema_notificacoes.notifications.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios_notificacoes")
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "notificacao_id", nullable = false)
    private Notification notificacaoEnviada;

    @Column(name = "lida", nullable = false)
    private boolean lida = false;

    @Column(name = "data_envio", nullable = false, updatable = false)
    private LocalDateTime dataEnvio = LocalDateTime.now();

    public UserNotification(User usuarioNotificado, Notification notificacaoEnviada) {
        this.usuario = usuarioNotificado;
        this.notificacaoEnviada = notificacaoEnviada;
        this.dataEnvio = LocalDateTime.now();
    }
}