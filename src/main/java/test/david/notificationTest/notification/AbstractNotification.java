package test.david.notificationTest.notification;

import test.david.notificationTest.entity.User;

public abstract class AbstractNotification {

    public abstract void notifySubscribed(User user, String message);

}
