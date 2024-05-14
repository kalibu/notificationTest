package test.david.notificationTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.entity.Channel;
import test.david.notificationTest.entity.Notification;
import test.david.notificationTest.entity.Subscribed;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.notification.AbstractNotification;
import test.david.notificationTest.notification.NotificationAbstractFactory;
import test.david.notificationTest.repository.ChannelRepository;
import test.david.notificationTest.repository.NotificationRepository;
import test.david.notificationTest.repository.SubscribedRepository;
import test.david.notificationTest.service.SendNotificationService;

import java.util.Calendar;
import java.util.LinkedList;
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

    @Override
    public void sendNotifications(final SendNotificationDTO dto){

        for (CategoryEnum category : dto.getCategories()){

            log.info("Searching subscriber by category {}", category);
            List<Subscribed> subscribers = subscribedRepository.findAllByCategory(category);

            for (Subscribed subscribed : subscribers){

                List<Notification> notifications = getNotificationsForUser(subscribed, dto.getMessage());

                for (Notification notification : notifications) {

                    notificationRepository.save(notification);

                    AbstractNotification notifyImpl = notificationAbstractFactory.getNotification(notification.getNotificationType());
                    notifyImpl.notifySubscribed(notification.getUser(), notification.getMessage());
                }
            }
        }
    }

    private List<Notification> getNotificationsForUser(final Subscribed subscribed, final String message) {

        List<Notification> notifications = new LinkedList<>();

        List<Channel> channelsForUser = channelRepository.findAllByUserId(subscribed.getUser().getId());

        for (Channel channel : channelsForUser){
            Notification notification = new Notification();
            notification.setUser(subscribed.getUser());
            notification.setCategory(subscribed.getCategory());
            notification.setNotificationDate(Calendar.getInstance());
            notification.setMessage(message);
            notification.setNotificationType(channel.getNotificationType());
            notifications.add(notification);
        }

        return notifications;
    }

}
