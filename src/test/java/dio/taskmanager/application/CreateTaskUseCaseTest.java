package dio.taskmanager.application;

import dio.taskmanager.application.CreateTaskUseCase;
import dio.taskmanager.application.input.CreateTaskInputDTO;
import dio.taskmanager.application.output.CreateTaskOutputDTO;
import dio.taskmanager.domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    void execute_shouldReturnOutputDTO_withExpectedFields() {
        // given
        var input = new CreateTaskInputDTO("Estudar", Optional.of("POO"));

        // when
        CreateTaskOutputDTO output = useCase.execute(input);

        // then
        assertThat(output).isNotNull();
        assertThat(output.title()).isEqualTo("Estudar");
        assertThat(output.description()).isEqualTo(Optional.of("POO"));

        // status deve ser PENDING (conforme a Task inicializa)
        assertThat(output.status()).isEqualTo("PENDING");

        // id deve existir
        assertThat(output.id()).isNotBlank();

        // neste use case atual, o repository nem é usado
        verifyNoInteractions(repository);
    }

    @Test
    void execute_shouldHandleEmptyDescription() {
        // given
        var input = new CreateTaskInputDTO("Tarefa sem descrição", Optional.empty());

        // when
        CreateTaskOutputDTO output = useCase.execute(input);

        // then
        assertThat(output).isNotNull();
        assertThat(output.title()).isEqualTo("Tarefa sem descrição");
        assertThat(output.description()).isEqualTo(Optional.empty());
        assertThat(output.status()).isEqualTo("PENDING");
        assertThat(output.id()).isNotBlank();

        verifyNoInteractions(repository);
    }
}