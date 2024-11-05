// application/services/AuthenticationService.java
package com.seuprojeto.sistema_notificacoes.auth;

import com.seuprojeto.sistema_notificacoes.notifications.model.User;
import com.seuprojeto.sistema_notificacoes.notifications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public LoginResponse login(String telefone, String senha) {
        User user = userRepository.findByTelefone(telefone)
                .orElseThrow(() -> new AuthenticationException("Usuário ou senha incorretos."));

        if (!passwordEncoder.matches(senha, user.getHashSenha())) {
            throw new AuthenticationException("Usuário ou senha incorretos.");
        }

        String token = jwtProvider.generateToken(user);
        return new LoginResponse(user.getNome(), user.isAdmin(), user.getId(), user.getDeviceToken(), token);
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.findByTelefone(request.getTelefone()).isPresent()) {
            throw new AuthenticationException("Telefone já registrado.");
        }
        User user = new User();
        user.setNome(request.getNome());
        user.setTelefone(request.getTelefone());
        user.setHashSenha(passwordEncoder.encode(request.getSenha()));
        user.setAdmin(request.isAdmin());
        user.setDeviceToken(request.getDeviceToken());
        userRepository.save(user);
        String token = jwtProvider.generateToken(user);
        return new LoginResponse(user.getNome(), user.isAdmin(), user.getId(), user.getDeviceToken(), token);
    }
}
