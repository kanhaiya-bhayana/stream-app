package com.stream.app.services.impl;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.stream.app.services.Interfaces.IUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@Service
public class UploadService implements IUploadService {

    Logger _logger = LoggerFactory.getLogger(UploadService.class);

    @Value("${azure.storage.connection-string}")
    private String _connectionString;
    @Value("${azure.storage.container-name}")
    private String _containerName;
    @Override
    public CompletableFuture<String> UploadAsync(MultipartFile file) throws IOException {
        _logger.info("UploadService.UploadAsync method called.");
        return CompletableFuture.supplyAsync(()->{
            return UploadFuture(file);
        });
    }
    public String UploadFuture(MultipartFile file){
        try{
            _logger.info("UploadService.UploadFuture method called.");
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            String blobUrl;

            BlobClientBuilder builder = new BlobClientBuilder()
                    .connectionString(_connectionString)
                    .containerName(_containerName)
                    .blobName(fileName);

            builder.buildClient().upload(inputStream, file.getSize(), true);

            BlobHttpHeaders headers = new BlobHttpHeaders()
                    .setContentType(contentType);

            builder.buildClient().setHttpHeaders(headers);

            blobUrl = builder.buildClient().getBlobUrl();

            return blobUrl;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
