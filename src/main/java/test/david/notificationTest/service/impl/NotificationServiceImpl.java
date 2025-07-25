package test.david.notificationTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.entity.Notification;
import test.david.notificationTest.repository.NotificationRepository;
import test.david.notificationTest.service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDTO> getAllNotificationOrderedFromNewest() {
        final Sort sortByNotificationDateDesc = Sort.by(Sort.Direction.DESC, "notificationDate");
        final List<Notification> notifications = notificationRepository.findAll(sortByNotificationDateDesc);

        List<NotificationDTO> notificationDTOS = notifications
                .stream()
                .map(NotificationDTO::new)
                .collect(Collectors.toList());

        return notificationDTOS;
    }

}
