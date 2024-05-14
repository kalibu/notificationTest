package test.david.notificationTest.controller;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import test.david.notificationTest.NotificationTestApplication;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.enums.NotificationTypeEnum;
import test.david.notificationTest.service.NotificationService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NotificationTestApplication.class)
@AutoConfigureMockMvc
public class LogHistoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    public void givenNotification_whenGetAllNotificationOrderedFromNewest_thenReturnJsonArray() throws Exception {

        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        NotificationDTO dto = new NotificationDTO(1l, "David", CategoryEnum.SPORTS.toString(), NotificationTypeEnum.SMS.toString(), SDF.format(Calendar.getInstance().getTime()));

        List<NotificationDTO> allNotifications = Arrays.asList(dto);

        Mockito.when(notificationService.getAllNotificationOrderedFromNewest()).thenReturn(allNotifications);

        mvc.perform(MockMvcRequestBuilders.get("/logHistory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].user", CoreMatchers.is(dto.getUser())));
    }
}
