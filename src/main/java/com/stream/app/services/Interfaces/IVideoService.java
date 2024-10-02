package com.stream.app.services.Interfaces;


import com.stream.app.entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVideoService {
    Video getVideoByIdAsync(String videoId);
    Video saveVideoAsync(Video video, MultipartFile file);
    Video getVideoByTitleAsync(String title);
    List<Video> getAllVideosAsync();
}
