package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInputDTO;
import dio.taskmanager.application.output.CreateTaskOutputDTO;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {
    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public CreateTaskOutputDTO execute(CreateTaskInputDTO input) {
        var task = new Task(input.title(), input.description());

        return CreateTaskOutputDTO.from(task);
    }
}
