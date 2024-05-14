package test.david.notificationTest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.controller.api.LogHistoryApi;
import test.david.notificationTest.dto.NotificationDTO;

@RestController
@Slf4j
@AllArgsConstructor
public class LogHistoryController implements LogHistoryApi {


    @Override
    public ResponseEntity<NotificationDTO> logHistory() {



        return ResponseEntity.ok().build();
    }
}
