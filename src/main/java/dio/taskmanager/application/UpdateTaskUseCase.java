package dio.taskmanager.application;

import dio.taskmanager.application.input.UpdateTaskInputDTO;
import dio.taskmanager.application.output.CreateTaskOutputDTO;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {

    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public CreateTaskOutputDTO execute(TaskId id, UpdateTaskInputDTO input) {
        var task = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.update(input.title(), input.description(), input.status());
        var updatedTask = repository.save(task);
        return CreateTaskOutputDTO.from(updatedTask);
    }
}
