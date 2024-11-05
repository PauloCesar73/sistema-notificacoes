package com.seuprojeto.sistema_notificacoes.notifications.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notificacoes")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String tituloNotificacao;

    @Column(name = "corpo", nullable = false)
    private String corpoNotificacao;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Notification(String tituloNotificacao, String corpoNotificacao, String tipo) {
        this.tituloNotificacao = tituloNotificacao;
        this.corpoNotificacao = corpoNotificacao;
        this.tipo = tipo;
    }
}
