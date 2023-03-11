package com.todo.context;

import java.util.Objects;

public class ExecutionContext {
    private final UserContext userContext;
    public static final ThreadLocal<ExecutionContext> CONTEXT = new ThreadLocal<>();

    public static void set(ExecutionContext ec) {
        if (Objects.isNull(ec)) {
            throw new RuntimeException("Invalid Execution Context");
        }
        CONTEXT.set(ec);
    }

    public static ExecutionContext get() {
        return (ExecutionContext)CONTEXT.get();
    }

    public ExecutionContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public UserContext getUsercontext() {
        return this.userContext;
    }
}
