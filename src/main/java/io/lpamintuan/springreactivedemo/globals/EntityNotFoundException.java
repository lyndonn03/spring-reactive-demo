package io.lpamintuan.springreactivedemo.globals;

public class EntityNotFoundException extends RuntimeException {

    private EntityNotFoundException(String msg) {
        super(msg);
    }

    public static EntityNotFoundException getInstance(String id, String baseMsg) {
        String msg = String.format(baseMsg, id);
        return new EntityNotFoundException(msg);
    }
    
}
