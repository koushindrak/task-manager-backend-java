package com.todo.context;

import java.util.Objects;

public class ExecutionContext {
    public static final ThreadLocal<ExecutionContext> CONTEXT = new ThreadLocal<>();
    private final UserContext userContext;

    public ExecutionContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public static void set(ExecutionContext ec) {
        if (Objects.isNull(ec)) {
            throw new RuntimeException("Invalid Execution Context");
        }
        CONTEXT.set(ec);
    }

    public static ExecutionContext get() {
        return CONTEXT.get();
    }

    public UserContext getUsercontext() {
        return this.userContext;
    }
}
