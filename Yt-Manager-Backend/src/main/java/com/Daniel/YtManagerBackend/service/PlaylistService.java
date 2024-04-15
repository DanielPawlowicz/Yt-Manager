package com.Daniel.YtManagerBackend.service;

import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.repository.PlaylistRepository;
import com.Daniel.YtManagerBackend.repository.VideoPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private VideoPlaylistRepository videoPlaylistRepository;

    // creating new playlist and return it's id
    public Long createPlaylist(Playlist playlist) {
        Playlist createdPlaylist = playlistRepository.save(playlist);
        return createdPlaylist.getId(); // Return the ID of the newly created playlist
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
    @Transactional
    public void deletePlaylist(Long playlistId) {
        videoPlaylistRepository.deleteByPlaylistId(playlistId);

        playlistRepository.deleteById(playlistId);
    }

    // count videos on the specific playlist
    public long getVideoCountByPlaylistId(Long playlistId) {
        return videoPlaylistRepository.countByPlaylistId(playlistId);
    }

}
