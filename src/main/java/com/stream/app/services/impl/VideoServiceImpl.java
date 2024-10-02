package com.stream.app.services.impl;

import com.stream.app.entities.Video;
import com.stream.app.services.Interfaces.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {

    Logger _logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Value("${files.video}")
    String DIR;
    @Override
    public Video getVideoByIdAsync(String videoId) {
        return null;
    }

    @Override
    public Video saveVideoAsync(Video video, MultipartFile file) {
        // folder path : create
        // folder path with filename
        // copy file to the folder
        // video metadata
        //  save
        try{
//            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            InputStream inputStream = file.getInputStream();

            String dir = cleanPath(DIR);
            String fileName = cleanPath(file.getOriginalFilename());

            Path path = Paths.get(dir,fileName);
            _logger.info("Path: " + path);



        }
        catch (Exception e){
            e.printStackTrace();
        }



        return null;
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
