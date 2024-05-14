package test.david.notificationTest.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.david.notificationTest.enums.NotificationTypeEnum;
import test.david.notificationTest.notification.impl.EmailNotification;
import test.david.notificationTest.notification.impl.PushNotificationNotification;
import test.david.notificationTest.notification.impl.SmsNotification;

@SpringBootTest
public class NotificationAbstractFactoryTest {

    @Autowired
    private NotificationAbstractFactory factory;

    @Test
    public void assertPushNotificationFactory (){
        AbstractNotification notification = factory.getNotification(NotificationTypeEnum.PUSH_NOTIFICATION);

        Assertions.assertEquals(PushNotificationNotification.class, notification.getClass());
    }

    @Test
    public void assertEmailFactory (){
        AbstractNotification notification = factory.getNotification(NotificationTypeEnum.E_MAIL);

        Assertions.assertEquals(EmailNotification.class, notification.getClass());
    }

    @Test
    public void assertSmsFactory (){
        AbstractNotification notification = factory.getNotification(NotificationTypeEnum.SMS);

        Assertions.assertEquals(SmsNotification.class, notification.getClass());
    }

}
