package com.Daniel.YtManagerBackend.service;

import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import com.Daniel.YtManagerBackend.repository.VideoPlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoPlaylistService {
    private final VideoPlaylistRepository videoPlaylistRepository;

    public VideoPlaylistService(VideoPlaylistRepository videoPlaylistRepository) {
        this.videoPlaylistRepository = videoPlaylistRepository;
    }

    // save video to playlist
    public VideoPlaylist saveVideoPlaylist(VideoPlaylist videoPlaylist) {
        return videoPlaylistRepository.save(videoPlaylist);
    }

    // get all video-playlists
    public List<VideoPlaylist> getAllVideoPlaylists() {
        return videoPlaylistRepository.findAll();
    }

    // get all videos from specific playlist ordered by index
    public List<VideoPlaylist> getVideosByPlaylistOrderedByOrderIndex(Long playlistId) {
        return videoPlaylistRepository.findByPlaylistIdOrderByOrderIndex(playlistId);
    }

    // get specific videoPlaylist by id
    public VideoPlaylist getVideoPlaylistById(Long videoPlaylistId) {
        return videoPlaylistRepository.findById(videoPlaylistId)
                .orElseThrow(() -> new NotFoundException("VideoPlaylist not found with ID: " + videoPlaylistId));
    }

    // update videoPlaylist
    public VideoPlaylist updateVideoPlaylist(Long videoPlaylistId, VideoPlaylist videoPlaylist) {
        VideoPlaylist existingVideoPlaylist = videoPlaylistRepository.findById(videoPlaylistId)
                .orElseThrow(() -> new NotFoundException("VideoPlaylist not found with ID: " + videoPlaylistId));

        existingVideoPlaylist.setVideoId(videoPlaylist.getVideoId());
        existingVideoPlaylist.setPlaylistId(videoPlaylist.getPlaylistId());
        existingVideoPlaylist.setOrderIndex(videoPlaylist.getOrderIndex());

        return videoPlaylistRepository.save(existingVideoPlaylist);
    }

    // delete videoPlaylist
    public void deleteVideoPlaylist(Long videoPlaylistId) {
        videoPlaylistRepository.deleteById(videoPlaylistId);
    }
}
