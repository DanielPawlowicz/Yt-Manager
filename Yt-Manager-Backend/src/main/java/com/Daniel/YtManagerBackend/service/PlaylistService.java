package com.Daniel.YtManagerBackend.service;

import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // get all playlists
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // get specific playlist by id
    public Playlist getPlaylistById(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist not found with ID: " + playlistId));
    }

    // update playlist
    public Playlist updatePlaylist(Long playlistId, Playlist playlist) {
        Playlist existingPlaylist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist not found with ID: " + playlistId));
        existingPlaylist.setPlaylistName(playlist.getPlaylistName());

        return playlistRepository.save(existingPlaylist);
    }

    // delete playlist
    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

}
