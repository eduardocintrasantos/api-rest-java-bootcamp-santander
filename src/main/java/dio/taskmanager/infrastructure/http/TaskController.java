package dio.taskmanager.infrastructure.http;

import dio.taskmanager.application.*;
import dio.taskmanager.application.input.CreateTaskInputDTO;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.infrastructure.http.request.CreateTaskRequestDTO;
import dio.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import dio.taskmanager.infrastructure.http.response.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase, GetTaskByIdUseCase getTaskByIdUseCase, DeleteTaskUseCase deleteTaskUseCase, UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    @PostMapping
    TaskResponseDTO createTask(@RequestBody CreateTaskRequestDTO request, OutputStream outputStream) {
        var input = new CreateTaskInputDTO(request.title(), request.description());
        var output = createTaskUseCase.execute(input);
        return TaskResponseDTO.from(output);
    }

    @GetMapping
    List<TaskResponseDTO> list() {
        return getTaskUseCase.execute().stream().map(TaskResponseDTO::from).toList();
    }

    @GetMapping("/{id}")
    TaskResponseDTO read(@PathVariable UUID id) {
        var output =  getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponseDTO.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));
    }

    @PatchMapping("/{id}")
    TaskResponseDTO update(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInputDTO();
        var output = updateTaskUseCase.execute(new TaskId(id), input);
        return TaskResponseDTO.from(output);
    }
}
