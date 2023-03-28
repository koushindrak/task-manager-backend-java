package com.todo.app_function;

import com.todo.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class TestingFunction {
    @Bean
    @RouterOperation(operation =
    @Operation(description = "Say hello", operationId = "hello", tags = "persons",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponse.class)))))
    public Supplier<UserResponse> helloSupplier() {
        return () -> new UserResponse();
    }
}
