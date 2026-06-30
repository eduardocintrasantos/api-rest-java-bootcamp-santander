package dio.taskmanager.application;

import dio.taskmanager.application.output.CreateTaskOutputDTO;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTaskUseCase {

    private final TaskRepository repository;

    public GetTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<CreateTaskOutputDTO> execute() {
        return repository.findAll().stream().map(CreateTaskOutputDTO::from).toList();
    }
}
