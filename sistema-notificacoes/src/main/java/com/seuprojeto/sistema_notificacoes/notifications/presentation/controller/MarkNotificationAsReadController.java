package com.seuprojeto.sistema_notificacoes.notifications.presentation.controller;
import com.seuprojeto.sistema_notificacoes.notifications.presentation.dto.NotificationResponse;
import com.seuprojeto.sistema_notificacoes.notifications.services.interfaces.MarkNotificationAsReadService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class MarkNotificationAsReadController {

    private final MarkNotificationAsReadService markNotificationAsReadService;

    @Autowired
    public MarkNotificationAsReadController(MarkNotificationAsReadService markNotificationAsReadService) {
        this.markNotificationAsReadService = markNotificationAsReadService;
    }

    @PutMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<NotificationResponse> markAsRead(@PathVariable Long notificationId) {
        NotificationResponse notificationResponse = markNotificationAsReadService.markAsRead(notificationId);
        return ResponseEntity.ok(notificationResponse);
    }
}
