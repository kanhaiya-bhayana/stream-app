package com.stream.app.controllers;

import com.stream.app.dto.ResponseDto;
import com.stream.app.entities.Video;
import com.stream.app.services.Interfaces.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.stream.app.utility.VideoConstants.SUCCESS_MESSAGE;

@RestController
@RequestMapping("/api/v1/videos")
public class VideosController {

    Logger _logger = LoggerFactory.getLogger(VideosController.class);
    private IVideoService _videoService;
    public VideosController(IVideoService videoService){
        _videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description)
    {
        _logger.info("VideosController.create method called.");

        if (file == null)
        {
            _logger.error("Error: 'file' parameter is null during video upload.");
            return null;
        }

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());

        Video response = _videoService.saveVideoAsync(video, file);
        if(response == null){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(),SUCCESS_MESSAGE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> stream(
            @PathVariable String id
    ){
        _logger.info("VideosController.stream method called.");
        if (id == null){
            _logger.error("Error: 'id' parameter is null.");
            return null;
        }
        Video response = _videoService.getVideoByIdAsync(id);
        response.setContentType(response.getContentType() == null ? "application/octet-stream" : response.getContentType());
//        Resource resource = new FileSystemResource(response.getFilePath());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response.getFilePath());
    }

    @GetMapping
    public ResponseEntity<List<String>> streamAll() {
        _logger.info("VideosController.streamAll method called.");
        List<Video> result = _videoService.getAllVideosAsync();
        List<String> response = new ArrayList<>();

        result.forEach(v -> {
            v.setContentType(v.getContentType() == null ? "application/octet-stream" : v.getContentType());
            response.add(v.getFilePath());
        });

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
