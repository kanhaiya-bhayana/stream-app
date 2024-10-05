package com.stream.app.utility;

import com.stream.app.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.stream.app.utility.VideoConstants.*;

public class ResponseMapper {
    public static <T> ResponseEntity<ApiResponse<T>> buildResponse(T data) {
        HttpStatus status = HttpStatus.valueOf(data != null ? SUCCESS_STATUS_CODE : FAILURE_STATUS_CODE);
        String message = data != null ? SUCCESS_MESSAGE : FAILURE_MESSAGE;

        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .statusCode(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(apiResponse, status);
    }
}
