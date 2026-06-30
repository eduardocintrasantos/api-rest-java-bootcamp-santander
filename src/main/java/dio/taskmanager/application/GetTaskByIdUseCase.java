package dio.taskmanager.application;

import dio.taskmanager.application.output.CreateTaskOutputDTO;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {

    private final TaskRepository repository;

    public GetTaskByIdUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public CreateTaskOutputDTO execute (TaskId id) {
        return repository.findById(id).map(CreateTaskOutputDTO::from).orElse(null);
    }
}
