package test.david.notificationTest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.entity.Notification;
import test.david.notificationTest.entity.User;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.enums.NotificationTypeEnum;
import test.david.notificationTest.repository.NotificationRepository;
import test.david.notificationTest.repository.UserRepository;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @Test
    public void testGetAllNotificationOrderedFromNewest() {

        User user = new User();
        user.setName("name");
        user.setEmail("email@email.com");
        user.setPhoneNumber("123321123");
        userRepository.save(user);

        Notification notificationOne = new Notification();
        notificationOne.setUser(user);
        notificationOne.setCategory(CategoryEnum.SPORTS);
        notificationOne.setNotificationType(NotificationTypeEnum.SMS);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, -10);
        notificationOne.setNotificationDate(c);
        notificationRepository.save(notificationOne);

        Notification notificationTwo = new Notification();
        notificationTwo.setUser(user);
        notificationTwo.setCategory(CategoryEnum.SPORTS);
        notificationTwo.setNotificationType(NotificationTypeEnum.SMS);
        notificationTwo.setNotificationDate(Calendar.getInstance());
        notificationRepository.save(notificationTwo);

        List<NotificationDTO> notificationDTOList = notificationService.getAllNotificationOrderedFromNewest();
        Assert.notEmpty(notificationDTOList, "list should not be empty");
        Assertions.assertEquals(2, notificationDTOList.size());

        Assertions.assertEquals(notificationTwo.getId(), notificationDTOList.get(0).getId());
        Assertions.assertEquals(notificationOne.getId(), notificationDTOList.get(1).getId());
    }

}
