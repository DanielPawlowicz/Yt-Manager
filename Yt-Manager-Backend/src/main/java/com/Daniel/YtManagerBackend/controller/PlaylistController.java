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

    // create playlist
    @PostMapping("/add")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
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
}
