package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void save() {
        var task = new Task("Passar na padaria", Optional.empty());

        var saved = repository.save(task);

        assertThat(saved).isNotNull();
        assertThat(repository.findById(task.getId()))
                .isPresent()
                .get()
                .satisfies(found -> {
                    assertThat(found.getId()).isEqualTo(task.getId());
                    assertThat(found.getTitle()).isEqualTo(task.getTitle());
                    assertThat(found.getDescription()).isEqualTo(task.getDescription());
                    assertThat(found.getStatus()).isEqualTo(TaskStatus.PENDING);
                });
    }

    @Test
    void findAll() {
        var task1 = new Task("Comprar pão", Optional.of("Integral"));
        var task2 = new Task("Comprar leite", Optional.empty());

        repository.save(task1);
        repository.save(task2);

        List<Task> tasks = repository.findAll();

        assertThat(tasks)
                .hasSize(2)
                .extracting(Task::getId)
                .containsExactlyInAnyOrder(task1.getId(), task2.getId());
    }

    @Test
    void findById() {
        var task = new Task("Estudar", Optional.of("POO"));
        repository.save(task);

        Optional<Task> found = repository.findById(task.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Estudar");
        assertThat(found.get().getStatus()).isEqualTo(TaskStatus.PENDING);
    }

    @Test
    void delete() {
        var task = new Task("Fazer caminhada", Optional.empty());
        TaskId id = task.getId();

        repository.save(task);
        repository.delete(id);

        assertThat(repository.findById(id)).isNotPresent();
    }
}