package dio.taskmanager.infrastructure.http.request;

import java.util.Optional;

public record CreateTaskRequestDTO (String title, Optional<String> description){
}
