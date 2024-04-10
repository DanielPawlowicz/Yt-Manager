package com.Daniel.YtManagerBackend.service;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // creating new playlist
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }


}
