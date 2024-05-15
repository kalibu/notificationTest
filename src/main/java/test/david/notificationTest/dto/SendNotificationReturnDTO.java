package test.david.notificationTest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import test.david.notificationTest.enums.CategoryEnum;

import java.util.List;

@Data
@AllArgsConstructor
public class SendNotificationReturnDTO {
    private String message;
}
