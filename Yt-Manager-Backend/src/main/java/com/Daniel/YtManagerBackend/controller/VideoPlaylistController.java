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
@RequestMapping("/video-playlists")
public class VideoPlaylistController {
    private final VideoPlaylistService videoPlaylistService;

    public VideoPlaylistController(VideoPlaylistService videoPlaylistService) {
        this.videoPlaylistService = videoPlaylistService;
    }

    // add video-playlist
    @PostMapping("/assign")
    public ResponseEntity<VideoPlaylist> addVideoToPlaylist(@RequestBody VideoPlaylist videoPlaylist) {
        VideoPlaylist savedVideoPlaylist = videoPlaylistService.saveVideoPlaylist(videoPlaylist);
        return new ResponseEntity<>(savedVideoPlaylist, HttpStatus.CREATED);
    }

    // get all video-playlists
    @GetMapping("/all")
    public ResponseEntity<List<VideoPlaylist>> getAllVideoPlaylists() {
        List<VideoPlaylist> videoPlaylists = videoPlaylistService.getAllVideoPlaylists();
        return ResponseEntity.ok(videoPlaylists);
    }

    // get all video-playlist by playlist id
    @GetMapping("/allVideoFrom/{playlistId}")
    public ResponseEntity<List<VideoPlaylist>> getVideosByPlaylist(@PathVariable Long playlistId) {
        List<VideoPlaylist> videos = videoPlaylistService.getVideosByPlaylistOrderedByOrderIndex(playlistId);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    // get specific video-playlist by id
    @GetMapping("/one/{videoPlaylistId}")
    public ResponseEntity<VideoPlaylist> getVideoPlaylistById(@PathVariable Long videoPlaylistId) {
        VideoPlaylist videoPlaylist = videoPlaylistService.getVideoPlaylistById(videoPlaylistId);
        return ResponseEntity.ok(videoPlaylist);
    }

    // eddit
    @PutMapping("/edit/{videoPlaylistId}")
    public ResponseEntity<VideoPlaylist> updateVideoPlaylist(@PathVariable Long videoPlaylistId, @RequestBody VideoPlaylist videoPlaylist) {
        VideoPlaylist updatedVideoPlaylist = videoPlaylistService.updateVideoPlaylist(videoPlaylistId, videoPlaylist);
        return ResponseEntity.ok(updatedVideoPlaylist);
    }

    // delete
    @DeleteMapping("/delete/{videoPlaylistId}")
    public ResponseEntity<Void> deleteVideoPlaylist(@PathVariable Long videoPlaylistId) {
        videoPlaylistService.deleteVideoPlaylist(videoPlaylistId);
        return ResponseEntity.noContent().build();
    }

    // search for max orderIndex for specific playlist, and if no records then return 0
    @GetMapping("/max-order-index/{playlistId}")
    public int getMaxOrderIndexByPlaylistId(@PathVariable Long playlistId) {
        return videoPlaylistService.getMaxOrderIndexByPlaylistId(playlistId);
    }

    // delete video from playlist
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeVideoFromPlaylist(@RequestParam String videoYtId, @RequestParam Long playlistId) {
        videoPlaylistService.deleteAssociationByVideoYtIdAndPlaylistId(videoYtId, playlistId);
        return ResponseEntity.noContent().build();
    }
}
