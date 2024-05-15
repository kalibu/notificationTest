package test.david.notificationTest.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.david.notificationTest.dto.NotificationDTO;

import java.util.List;

@Validated
@Tag(name = "logHistory", description = "Controller for history of logs")
@RestController
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public interface LogHistoryApi {

    /**
     * Return an ordered list of log history from notifications
     */
    @Operation(summary = "History of Logs",
            operationId = "logHistory",
            description = "History of Logs",
            tags = {"logHistory"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))})})
    @GetMapping("/logHistory")
    ResponseEntity<List<NotificationDTO>> logHistory();

}
