package com.Daniel.YtManagerBackend.service;

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

    // get all videos from specific playlist ordered by index
    public List<VideoPlaylist> getVideosByPlaylist(Playlist playlist) {
        return videoPlaylistRepository.findByPlaylistOrderByOrderIndexAsc(playlist);
    }
}
