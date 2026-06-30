package dio.taskmanager.application.output;

import dio.taskmanager.domain.Task;

import java.util.Optional;

public record CreateTaskOutputDTO(String id, String title, Optional<String> description, String status) {

    public static CreateTaskOutputDTO from(Task task) {
        return new CreateTaskOutputDTO(task.getId().id().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name());
    }
}
