package com.stream.app.controllers;

import com.stream.app.dto.ApiResponse;
import com.stream.app.entities.Video;
import com.stream.app.services.Interfaces.IVideoService;
import com.stream.app.utility.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
public class VideosController {

    Logger _logger = LoggerFactory.getLogger(VideosController.class);
    private IVideoService _videoService;
    public VideosController(IVideoService videoService){
        _videoService = videoService;
    }
    @GetMapping
    public ResponseEntity<Void> get(){
        System.out.println("tatti");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Video>> create(
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

        return ResponseMapper.buildResponse(response);
    }

}
