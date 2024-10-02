package com.stream.app.services.impl;

import com.stream.app.entities.Video;
import com.stream.app.repositories.IVideoRepository;
import com.stream.app.services.Interfaces.IVideoService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoRepository _videoRepository;

    Logger _logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @PostConstruct
    public void init(){
        File file = new File(DIR);
        if (!file.exists()){
            file.mkdir();
            _logger.info("Directory created with the name: "+DIR);
        }
    }

    @Value("${files.video}")
    String DIR;
    @Override
    public Video getVideoByIdAsync(String videoId) {
        return null;
    }

    @Override
    public Video saveVideoAsync(Video video, MultipartFile file) {
        try{
            String contentType = file.getContentType();

            InputStream inputStream = file.getInputStream();

            String dir = cleanPath(DIR);
            String fileName = cleanPath(file.getOriginalFilename());

            Path path = Paths.get(dir,fileName);
            _logger.info("Path: " + path);

            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
            video.setContentType(contentType);
            video.setFilePath(path.toString());

            return _videoRepository.save(video);

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video getVideoByTitleAsync(String title) {
        return null;
    }

    @Override
    public List<Video> getAllVideosAsync() {
        return null;
    }

    private String cleanPath(String path){
        return StringUtils.cleanPath(path);
    }
}
