package test.david.notificationTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import test.david.notificationTest.entity.Notification;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
public class NotificationDTO {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private final Long id;
    private final String user;
    private final String category;
    private final String notificationType;
    private final String notificationDate;

    public NotificationDTO(final Notification notification) {
        this.id = notification.getId();
        this.user = notification.getUser().getName();
        this.category = notification.getCategory().toString();
        this.notificationType = notification.getNotificationType().toString();
        this.notificationDate = SDF.format(notification.getNotificationDate().getTime());
    }

}
