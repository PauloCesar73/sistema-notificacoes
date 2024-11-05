package com.seuprojeto.sistema_notificacoes.notifications.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", unique = true, nullable = false)
    private String telefone;

    @Column(name = "senha_hash", nullable = false)
    private String hashSenha;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public User(String nome, String telefone, String hashSenha, boolean admin, String deviceToken) {
        this.nome = nome;
        this.telefone = telefone;
        this.hashSenha = hashSenha;
        this.admin = admin;
        this.deviceToken = deviceToken;
        this.dataCriacao = LocalDateTime.now();
    }
}
