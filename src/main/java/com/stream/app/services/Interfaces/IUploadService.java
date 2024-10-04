package com.stream.app.services.Interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface IUploadService {
    CompletableFuture<String> UploadAsync(MultipartFile file) throws IOException;
}
