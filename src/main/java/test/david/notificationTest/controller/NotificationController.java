package test.david.notificationTest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.controller.api.NotificationApi;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.service.SendNotificationService;

@RestController
@Slf4j
@AllArgsConstructor
public class NotificationController implements NotificationApi {

    @Autowired
    private SendNotificationService sendNotificationService;

    @Override
    public ResponseEntity<String> sendNotification(SendNotificationDTO sendNotificationDTO) {

        log.info(sendNotificationDTO.toString());
        sendNotificationService.sendNotifications(sendNotificationDTO);

        return ResponseEntity.ok("Notifications being sent");
    }
}
