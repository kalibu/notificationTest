package test.david.notificationTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import test.david.notificationTest.NotificationTestApplication;
import test.david.notificationTest.dto.SendNotificationDTO;
import test.david.notificationTest.enums.CategoryEnum;
import test.david.notificationTest.service.SendNotificationService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NotificationTestApplication.class)
@AutoConfigureMockMvc
public class NotificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SendNotificationService sendNotificationService;

    @Test
    public void givenSendNotification_whenDtoOk_thenReturnOk() throws Exception {

        SendNotificationDTO sendNotificationDTO = new SendNotificationDTO();
        sendNotificationDTO.setMessage("message");
        sendNotificationDTO.setCategories(Arrays.asList(CategoryEnum.FINANCE));

        mvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .content(new ObjectMapper().writeValueAsString(sendNotificationDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenSendNotification_whenDtoWithoutMessage_thenReturnNok() throws Exception {

        SendNotificationDTO sendNotificationDTO = new SendNotificationDTO();
        sendNotificationDTO.setMessage("");
        sendNotificationDTO.setCategories(List.of(CategoryEnum.FINANCE));

        mvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .content(new ObjectMapper().writeValueAsString(sendNotificationDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void givenSendNotification_whenDtoWithoutCategory_thenReturnNok() throws Exception {

        SendNotificationDTO sendNotificationDTO = new SendNotificationDTO();
        sendNotificationDTO.setMessage("message");
        sendNotificationDTO.setCategories(List.of());

        mvc.perform(MockMvcRequestBuilders.post("/sendNotification")
                        .content(new ObjectMapper().writeValueAsString(sendNotificationDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
