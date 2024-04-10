package com.Daniel.YtManagerBackend.controller;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // create playlist
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }
}
