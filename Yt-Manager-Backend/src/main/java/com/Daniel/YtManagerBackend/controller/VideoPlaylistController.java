package com.Daniel.YtManagerBackend.controller;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import com.Daniel.YtManagerBackend.service.VideoPlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlist/videos")
public class VideoPlaylistController {
    private final VideoPlaylistService videoPlaylistService;

    public VideoPlaylistController(VideoPlaylistService videoPlaylistService) {
        this.videoPlaylistService = videoPlaylistService;
    }

    @PostMapping
    public ResponseEntity<VideoPlaylist> addVideoToPlaylist(@RequestBody VideoPlaylist videoPlaylist) {
        VideoPlaylist savedVideoPlaylist = videoPlaylistService.saveVideoPlaylist(videoPlaylist);
        return new ResponseEntity<>(savedVideoPlaylist, HttpStatus.CREATED);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<List<VideoPlaylist>> getVideosByPlaylist(@PathVariable Long playlistId) {
        Playlist playlist = new Playlist();
        playlist.setId(playlistId);
        List<VideoPlaylist> videos = videoPlaylistService.getVideosByPlaylist(playlist);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}
