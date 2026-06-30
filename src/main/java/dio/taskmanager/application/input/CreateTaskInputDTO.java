package dio.taskmanager.application.input;

import java.util.Optional;

public record CreateTaskInputDTO(String title, Optional<String> description) {
}
