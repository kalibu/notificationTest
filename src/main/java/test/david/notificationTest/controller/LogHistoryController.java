package test.david.notificationTest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.controller.api.LogHistoryApi;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.service.NotificationService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class LogHistoryController implements LogHistoryApi {

    @Autowired
    private NotificationService notificationService;

    @Override
    public ResponseEntity<List<NotificationDTO>> logHistory() {

        final List<NotificationDTO> notificationDTOList = notificationService.getAllNotificationOrderedFromNewest();
        log.info("logHistory with: {} notifications", notificationDTOList.size());

        return ResponseEntity.ok(notificationDTOList);
    }
}
