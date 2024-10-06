package com.stream.app.utility;

import org.springframework.http.HttpStatus;

public class VideoConstants {
    public static final String FAILURE_MESSAGE = "Failed to upload video";
    public static final String SUCCESS_MESSAGE = "Video uploaded successfully";
    public static final String NOTFOUND_MESSAGE = "Video not found";

    // HTTP Status codes
    public static final int SUCCESS_STATUS_CODE = HttpStatus.OK.value();
    public static final int FAILURE_STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final int NOTFOUND_STATUS_CODE = HttpStatus.NOT_FOUND.value();
}
