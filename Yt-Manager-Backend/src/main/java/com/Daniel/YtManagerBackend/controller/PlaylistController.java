package com.Daniel.YtManagerBackend.controller;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // creating new playlist and return it's id
    @PostMapping("/create")
    public ResponseEntity<Long> createPlaylist(@RequestBody Playlist playlist) {
        Long playlistId = playlistService.createPlaylist(playlist);
        return new ResponseEntity<>(playlistId, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/one/{playlistId}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        return ResponseEntity.ok(playlist);
    }

    @PutMapping("/edit/{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long playlistId, @RequestBody Playlist Playlist){
        Playlist updatedPlaylist = playlistService.updatePlaylist(playlistId, Playlist);
        return ResponseEntity.ok(updatedPlaylist);
    }

    @DeleteMapping("/delete/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }


    // count videos on specific playlist
    @GetMapping("/count/{playlistId}")
    public ResponseEntity<Long> getVideoCountByPlaylistId(@PathVariable Long playlistId) {
        long videoCount = playlistService.getVideoCountByPlaylistId(playlistId);
        return ResponseEntity.ok(videoCount);
    }
}
