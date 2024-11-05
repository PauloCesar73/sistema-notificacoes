package com.seuprojeto.sistema_notificacoes.notifications.presentation.controller;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationRequest;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.CreateNotificationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class CreateNotificationController {

    private final CreateNotificationService createNotificationService;

    @Autowired
    public CreateNotificationController(CreateNotificationService createNotificationService) {
        this.createNotificationService = createNotificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
        NotificationResponse notificationResponse = createNotificationService.createNotification(request);
        return ResponseEntity.ok(notificationResponse);
    }
}
