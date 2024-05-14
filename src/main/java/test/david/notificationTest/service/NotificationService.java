package test.david.notificationTest.service;

import test.david.notificationTest.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    public List<NotificationDTO> getAllNotificationOrderedFromNewest();

}
