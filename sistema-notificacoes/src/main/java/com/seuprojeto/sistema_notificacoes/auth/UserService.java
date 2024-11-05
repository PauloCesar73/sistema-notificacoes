package com.seuprojeto.sistema_notificacoes.auth;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserSummary> getAllUserSummaries() {
        return userRepository.findAll().stream()
                .map(user -> new UserSummary(user.getId(), user.getNome()))
                .collect(Collectors.toList());
    }
}
