package dio.taskmanager.infrastructure.http.request;

import dio.taskmanager.application.input.CreateTaskInputDTO;

import java.util.Optional;

public record CreateTaskRequestDTO (String title, Optional<String> description){
    public CreateTaskInputDTO toInputDTO() {
        return new CreateTaskInputDTO(title, description);
    }
}
