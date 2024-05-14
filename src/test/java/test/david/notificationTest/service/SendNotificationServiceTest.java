package test.david.notificationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.entity.Channel;
import test.david.notificationTest.entity.Notification;
import test.david.notificationTest.entity.Subscribed;
import test.david.notificationTest.entity.User;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.enums.NotificationTypeEnum;
import test.david.notificationTest.repository.ChannelRepository;
import test.david.notificationTest.repository.NotificationRepository;
import test.david.notificationTest.repository.SubscribedRepository;
import test.david.notificationTest.repository.UserRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class SendNotificationServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SubscribedRepository subscribedRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private SendNotificationService sendNotificationService;

    @BeforeEach
    public void beforeEach(){
        notificationRepository.deleteAll();
        channelRepository.deleteAll();
        subscribedRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSendNotificationsNoNotificationTypeOnDB(){

        User user = new User();
        user.setName("David");
        user.setEmail("email@email.com");
        user.setPhoneNumber("123321123");
        userRepository.save(user);

        Subscribed subscribed = new Subscribed();
        subscribed.setUser(user);
        subscribed.setCategory(CategoryEnum.SPORTS);
        subscribedRepository.save(subscribed);

        Channel channel = new Channel();
        channel.setUser(user);
        channel.setNotificationType(NotificationTypeEnum.SMS);
        channelRepository.save(channel);

        SendNotificationDTO dto = new SendNotificationDTO();
        dto.setCategories(Arrays.asList(CategoryEnum.FINANCE));
        dto.setMessage("test");
        sendNotificationService.sendNotifications(dto);

        List<Notification> notifications = notificationRepository.findAll();
        Assertions.assertEquals(0, notifications.size());
    }

    @Test
    public void testSendNotificationsOneNotification(){

        User user = new User();
        user.setName("David");
        user.setEmail("email@email.com");
        user.setPhoneNumber("123321123");
        userRepository.save(user);

        Subscribed subscribed = new Subscribed();
        subscribed.setUser(user);
        subscribed.setCategory(CategoryEnum.SPORTS);
        subscribedRepository.save(subscribed);

        Channel channel = new Channel();
        channel.setUser(user);
        channel.setNotificationType(NotificationTypeEnum.SMS);
        channelRepository.save(channel);

        SendNotificationDTO dto = new SendNotificationDTO();
        dto.setCategories(Arrays.asList(CategoryEnum.SPORTS));
        dto.setMessage("test");
        sendNotificationService.sendNotifications(dto);

        List<Notification> notifications = notificationRepository.findAll();
        Assertions.assertEquals(1, notifications.size());
        Assertions.assertEquals(user.getId(), notifications.get(0).getUser().getId());
    }

    @Test
    public void testSendNotificationsMoreThanOnNotificationMethod(){

        User user = new User();
        user.setName("David");
        user.setEmail("email@email.com");
        user.setPhoneNumber("123321123");
        userRepository.save(user);

        Subscribed subscribed = new Subscribed();
        subscribed.setUser(user);
        subscribed.setCategory(CategoryEnum.SPORTS);
        subscribedRepository.save(subscribed);

        Channel channel = new Channel();
        channel.setUser(user);
        channel.setNotificationType(NotificationTypeEnum.SMS);
        channelRepository.save(channel);

        Channel channel2 = new Channel();
        channel2.setUser(user);
        channel2.setNotificationType(NotificationTypeEnum.PUSH_NOTIFICATION);
        channelRepository.save(channel2);

        SendNotificationDTO dto = new SendNotificationDTO();
        dto.setCategories(Arrays.asList(CategoryEnum.SPORTS));
        dto.setMessage("test");
        sendNotificationService.sendNotifications(dto);

        List<Notification> notifications = notificationRepository.findAll();
        Assertions.assertEquals(2, notifications.size());
        for (Notification notification : notifications) {
            Assertions.assertEquals(user.getId(), notification.getUser().getId());
        }
    }

    @Test
    public void testSendNotificationsMoreThanOnCategory(){

        User user = new User();
        user.setName("David");
        user.setEmail("email@email.com");
        user.setPhoneNumber("123321123");
        userRepository.save(user);

        Subscribed subscribed = new Subscribed();
        subscribed.setUser(user);
        subscribed.setCategory(CategoryEnum.SPORTS);
        subscribedRepository.save(subscribed);

        Subscribed subscribed2 = new Subscribed();
        subscribed2.setUser(user);
        subscribed2.setCategory(CategoryEnum.FINANCE);
        subscribedRepository.save(subscribed2);

        Channel channel = new Channel();
        channel.setUser(user);
        channel.setNotificationType(NotificationTypeEnum.SMS);
        channelRepository.save(channel);

        SendNotificationDTO dto = new SendNotificationDTO();
        dto.setCategories(Arrays.asList(CategoryEnum.SPORTS, CategoryEnum.FINANCE));
        dto.setMessage("test");
        sendNotificationService.sendNotifications(dto);

        List<Notification> notifications = notificationRepository.findAll();
        Assertions.assertEquals(2, notifications.size());
        for (Notification notification : notifications) {
            Assertions.assertEquals(user.getId(), notification.getUser().getId());
        }
    }
}
