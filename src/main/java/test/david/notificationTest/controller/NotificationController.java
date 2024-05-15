package test.david.notificationTest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.controller.api.NotificationApi;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.dto.SendNotificationReturnDTO;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.service.SendNotificationService;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class NotificationController implements NotificationApi {

    @Autowired
    private SendNotificationService sendNotificationService;

    @Override
    public ResponseEntity<SendNotificationReturnDTO> sendNotification(SendNotificationDTO sendNotificationDTO) {

        log.info(sendNotificationDTO.toString());
        sendNotificationService.asyncSendNotifications(sendNotificationDTO);

        return ResponseEntity.ok(new SendNotificationReturnDTO("Notifications being sent"));
    }

    @Override
    public ResponseEntity<List<String>> getCategoryList() {

        List<String> categories = Arrays
                .stream(CategoryEnum.values())
                .map(CategoryEnum::toString)
                .toList();

        log.info(categories.toString());

        return ResponseEntity.ok(categories);
    }
}
