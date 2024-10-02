package com.stream.app.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    // Getters and setters
    private int statusCode;
    private String message;
    private T data;
}
