package com.todo.dto.request;

import jakarta.validation.constraints.NotNull;

public record TaskLabelRequest(@NotNull(message = "Task Id is mandatory") Long taskId,
                               @NotNull(message = "Label Id is mandatory") Long labelId) {

}
