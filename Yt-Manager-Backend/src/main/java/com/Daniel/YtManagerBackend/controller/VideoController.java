package com.Daniel.YtManagerBackend.controller;

import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // saving video
    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        Video savedVideo = videoService.saveVideo(video);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }
}
