package test.david.notificationTest.notification.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import test.david.notificationTest.entity.User;
import test.david.notificationTest.notification.AbstractNotification;

@Service
@Slf4j
public class SmsNotification extends AbstractNotification {

    public void notifySubscribed(User user, String message) {
        log.info("Sending SMS notification for user: {}, phoneNumber: {}, message: {}", user.getName(), user.getPhoneNumber(), message);
    }
}
