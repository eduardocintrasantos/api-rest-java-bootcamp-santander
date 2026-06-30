package dio.taskmanager.domain;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    public TaskId() {
        this(UUID.randomUUID());
    }
}
