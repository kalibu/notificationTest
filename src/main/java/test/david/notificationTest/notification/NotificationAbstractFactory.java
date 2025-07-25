package test.david.notificationTest.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.david.notificationTest.enums.NotificationTypeEnum;
import test.david.notificationTest.notification.impl.EmailNotification;
import test.david.notificationTest.notification.impl.PushNotificationNotification;
import test.david.notificationTest.notification.impl.SmsNotification;

@Service
public class NotificationAbstractFactory {

    @Autowired
    private EmailNotification emailNotification;

    @Autowired
    private PushNotificationNotification pushNotificationNotification;

    @Autowired
    private SmsNotification smsNotification;

    public AbstractNotification getNotification(NotificationTypeEnum notificationTypeEnum) {

        return switch (notificationTypeEnum) {
            case NotificationTypeEnum.PUSH_NOTIFICATION -> pushNotificationNotification;
            case NotificationTypeEnum.E_MAIL -> emailNotification;
            case NotificationTypeEnum.SMS -> smsNotification;
        };

    }

}
