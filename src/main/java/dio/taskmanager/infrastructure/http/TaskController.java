package dio.taskmanager.infrastructure.http;

import dio.taskmanager.application.CreateTaskUseCase;
import dio.taskmanager.application.GetTaskUseCase;
import dio.taskmanager.application.input.CreateTaskInputDTO;
import dio.taskmanager.infrastructure.http.request.CreateTaskRequestDTO;
import dio.taskmanager.infrastructure.http.response.TaskResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
    }

    @PostMapping
    TaskResponseDTO createTask(@RequestBody CreateTaskRequestDTO request, OutputStream outputStream) {
        var input = new CreateTaskInputDTO(request.title(), request.description());
        var output = createTaskUseCase.execute(input);
        return TaskResponseDTO.from(output);
    }

}
