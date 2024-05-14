package test.david.notificationTest.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.dto.NotificationDTO;
import test.david.notificationTest.dto.SendNotificationDTO;

@Validated
@Tag(name = "notification", description = "Controller for notification")
@RestController
public interface NotificationApi {

    /**
     * Send a notification for the users
     */
    @Operation(summary = "Send Notification",
            operationId = "sendNotification",
            description = "Send Notification",
            tags = {"notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid param",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))})})
    @PostMapping("/sendNotification")
    ResponseEntity<String> sendNotification(@Valid @RequestBody SendNotificationDTO sendNotificationDTO);

}
