package com.stream.app.services.Interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {
    String UploadAsync(MultipartFile file) throws IOException;
}
