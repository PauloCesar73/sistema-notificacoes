// presentation/controller/AuthController.java
package com.seuprojeto.sistema_notificacoes.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Autenticar usuário", description = "Realiza o login do usuário com telefone e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas ou usuário não encontrado")
    })
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Public")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authenticationService.login(request.getTelefone(), request.getSenha());
    }

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário com telefone, nome, senha e deviceToken")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Usuário já registrado com este telefone")
    })
    @PostMapping("/register")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Public")
    public LoginResponse register(@Valid @RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }
}
