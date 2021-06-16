package io.lpamintuan.springreactivedemo.globals;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
    private int status;
    
}
