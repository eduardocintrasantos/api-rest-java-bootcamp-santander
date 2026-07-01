package dio.taskmanager.infrastructure.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.taskmanager.application.output.CreateTaskOutputDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskResponseDTO(String id, String title, String description, String status) {
    public static TaskResponseDTO from (CreateTaskOutputDTO output) {
        return new TaskResponseDTO(
                output.id(),
                output.title(),
                output.description().orElse(""),
                output.status());
    }
}
