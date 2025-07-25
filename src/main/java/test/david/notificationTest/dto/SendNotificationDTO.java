package test.david.notificationTest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import test.david.notificationTest.enums.CategoryEnum;

@Data
public class SendNotificationDTO {

    @NotNull(message = "The list of categories must not be empty.")
    private CategoryEnum category;

    @NotEmpty(message = "The message is required.")
    @Size(min = 1, max = 4000, message = "The message length must be between 1 and 4000 characters.")
    private String message;

}
