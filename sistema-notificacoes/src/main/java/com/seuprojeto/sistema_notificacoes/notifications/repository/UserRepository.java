package com.seuprojeto.sistema_notificacoes.notifications.repository;
import com.seuprojeto.sistema_notificacoes.notifications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTelefone(String telefone);
}
