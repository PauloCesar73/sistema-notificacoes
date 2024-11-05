package com.seuprojeto.sistema_notificacoes.notifications.presentation.controller;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.GetUserNotificationsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class GetUserNotificationsController {

    private final GetUserNotificationsService getUserNotificationsService;

    @Autowired
    public GetUserNotificationsController(GetUserNotificationsService getUserNotificationsService) {
        this.getUserNotificationsService = getUserNotificationsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getUserNotifications(@PathVariable Long userId) {
        List<NotificationResponse> notifications = getUserNotificationsService.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }
}
