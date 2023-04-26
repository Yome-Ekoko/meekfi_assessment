package com.yomeDev.blogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message;
    private Boolean success;
    private T data;
}
