package dio.taskmanager.infrastructure.http.request;

import dio.taskmanager.application.input.UpdateTaskInputDTO;
import dio.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
) {
    public UpdateTaskInputDTO toInputDTO() {
        return new UpdateTaskInputDTO(title, description, status.map(TaskStatus::valueOf));
    }
}
