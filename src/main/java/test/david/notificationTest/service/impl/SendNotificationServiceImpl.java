package test.david.notificationTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    /**
     * Async call to send notification, that in the future can be changed into a queue, batch process, etc
     * @param dto
     */
    @Override
    @Async
    public void asyncSendNotifications(final SendNotificationDTO dto) {
        log.info("Starting async send notification");
        sendNotifications(dto);
    }

    /**
     * Send notification to all the users based on categories list
     * @param dto
     */
    @Override
    public void sendNotifications(final SendNotificationDTO dto){

        for (CategoryEnum category : dto.getCategories()){

            log.info("Searching subscriber by category {}", category);
            List<Subscribed> subscribers = subscribedRepository.findAllByCategory(category);

            for (Subscribed subscribed : subscribers){

                List<Notification> notifications = getNotificationsForUser(subscribed, dto.getMessage());

                for (Notification notification : notifications) {

                    //save history
                    notificationRepository.save(notification);

                    AbstractNotification notifyImpl = notificationAbstractFactory.getNotification(notification.getNotificationType());
                    if(notifyImpl != null) {
                        notifyImpl.notifySubscribed(notification.getUser(), notification.getMessage());
                    }else{
                        //if by any reason the notification type doesn't has a implementation
                        log.error("Error while sending a notifications for userId: {}, notificationType: {}", notification.getUser().getId(), notification.getNotificationType());
                    }
                }
            }
        }
    }

    /**
     * Get a list of notifications to be sent to the users
     * @param subscribed
     * @param message
     * @return
     */
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
