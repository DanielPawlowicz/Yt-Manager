package com.Daniel.YtManagerBackend.controller;

import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // saving video
    @PostMapping("/add")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        Video savedVideo = videoService.saveVideo(video);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }

    // get all videos
    @GetMapping("/all")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    // get one specific video by id
    @GetMapping("/one/{videoId}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long videoId) {
        Video video = videoService.getVideoById(videoId);
        return ResponseEntity.ok(video);
    }

    // edit video
    @PutMapping("/edit/{videoId}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long videoId, @RequestBody Video video) {
        Video updatedVideo = videoService.updateVideo(videoId, video);
        return ResponseEntity.ok(updatedVideo);
    }

    // delete video
    @DeleteMapping("/delete/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long videoId) {
        videoService.deleteVideo(videoId);
        return ResponseEntity.noContent().build();
    }

}
