package com.stream.app.controllers;

import com.stream.app.dto.ApiResponse;
import com.stream.app.entities.Video;
import com.stream.app.services.Interfaces.IVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
public class VideosController {

    private IVideoService _videoService;
    public VideosController(IVideoService videoService){
        _videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestParam("file")MultipartFile file,
            @RequestParam("title")String title,
            @RequestParam("description")String description
            ){
        Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoId(UUID.randomUUID().toString());
            _videoService.saveVideoAsync(video,file);
        return null;
    }
}
