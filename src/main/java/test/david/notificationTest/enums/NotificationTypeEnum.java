package test.david.notificationTest.enums;

import lombok.Getter;

public enum NotificationTypeEnum {

    SMS("SMS"),
    E_MAIL("E-Mail"),
    PUSH_NOTIFICATION("Push Notification");

    @Getter
    private final String description;

    NotificationTypeEnum(final String description) {
        this.description = description;
    }

}
