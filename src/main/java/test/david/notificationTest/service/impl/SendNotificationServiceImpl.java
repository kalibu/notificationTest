package test.david.notificationTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.entity.Channel;
import test.david.notificationTest.entity.Notification;
import test.david.notificationTest.entity.Subscribed;
import test.david.notificationTest.notification.AbstractNotification;
import test.david.notificationTest.notification.NotificationAbstractFactory;
import test.david.notificationTest.repository.ChannelRepository;
import test.david.notificationTest.repository.NotificationRepository;
import test.david.notificationTest.repository.SubscribedRepository;
import test.david.notificationTest.service.SendNotificationService;

import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class SendNotificationServiceImpl implements SendNotificationService {

    @Autowired
    private SubscribedRepository subscribedRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationAbstractFactory notificationAbstractFactory;

    /**
     * Async call to send notification, that in the future can be changed into a queue, batch process, etc
     *
     * @param dto
     */
    @Override
    @Async
    public void asyncSendNotifications(final SendNotificationDTO dto) {
        log.info("Starting async send notification");
        sendNotifications(dto);
    }

    @Override
    public void sendNotifications(final SendNotificationDTO dto) {
        log.info("Searching subscribers by category: {}", dto.getCategory());

        List<Subscribed> subscribers = subscribedRepository.findAllByCategory(dto.getCategory());

        if (subscribers.isEmpty()) {
            log.warn("No subscribers found for category: {}", dto.getCategory());
            return;
        }

        subscribers.forEach(subscribed -> {
            try {
                processNotificationsForSubscribed(subscribed, dto.getMessage());
            } catch (Exception e) {
                log.error("Failed to process notifications for userId: {}",
                        subscribed.getUser().getId(), e);
            }
        });
    }

    private void processNotificationsForSubscribed(Subscribed subscribed, String message) {
        List<Channel> channels = channelRepository.findAllByUserId(subscribed.getUser().getId());

        if (channels.isEmpty()) {
            log.warn("No channels configured for userId: {}", subscribed.getUser().getId());
            return;
        }

        channels.stream()
                .map(channel -> buildNotification(subscribed, channel, message))
                .forEach(this::handleNotification);
    }

    private Notification buildNotification(Subscribed subscribed, Channel channel, String message) {
        return Notification.builder()
                .user(subscribed.getUser())
                .category(subscribed.getCategory())
                .notificationType(channel.getNotificationType())
                .notificationDate(Calendar.getInstance())
                .message(message)
                .build();
    }

    private void handleNotification(Notification notification) {
        try {
            notificationRepository.save(notification);
            sendNotification(notification);
        } catch (Exception e) {
            log.error("Failed to handle notification for userId: {}, type: {}",
                    notification.getUser().getId(), notification.getNotificationType(), e);
        }
    }

    private void sendNotification(Notification notification) {
        AbstractNotification notifyImpl = notificationAbstractFactory.getNotification(notification.getNotificationType());

        if (notifyImpl == null) {
            log.error("No implementation found for notificationType: {} (userId: {})",
                    notification.getNotificationType(), notification.getUser().getId());
            return;
        }

        try {
            notifyImpl.notifySubscribed(notification.getUser(), notification.getMessage());
            log.info("Notification sent successfully to userId: {} via {}",
                    notification.getUser().getId(), notification.getNotificationType());
        } catch (Exception e) {
            log.error("Error sending notification to userId: {} via {}",
                    notification.getUser().getId(), notification.getNotificationType(), e);
        }
    }
}
