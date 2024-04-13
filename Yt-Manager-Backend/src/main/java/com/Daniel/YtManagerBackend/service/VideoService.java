package com.Daniel.YtManagerBackend.service;
import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import com.Daniel.YtManagerBackend.repository.VideoPlaylistRepository;
import com.Daniel.YtManagerBackend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoPlaylistRepository videoPlaylistRepository;


    // creating new video
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    // get specific video by id
    public Video getVideoById(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new NotFoundException("Video not found with ID: " + videoId));
    }

    // edit video
    public Video updateVideo(Long videoId, Video video) {
        Video existingVideo = videoRepository.findById(videoId)
                .orElseThrow(() -> new NotFoundException("Video not found with ID: " + videoId));

        existingVideo.setTitle(video.getTitle());
        existingVideo.setLink(video.getLink());
        existingVideo.setDuration(video.getDuration());
        existingVideo.setThumbnailUrl(video.getThumbnailUrl());
        existingVideo.setBookmark(video.getBookmark());
        existingVideo.setNote(video.getNote());
        existingVideo.setTimestamps(video.getTimestamps());
        existingVideo.setWatched(video.getWatched());

        return videoRepository.save(existingVideo);
    }

    @Transactional
    public void deleteVideoByYtId(String ytId, Long playlistId) {
        // Get the order index of the video being deleted
        int deletedOrderIndex = videoPlaylistRepository.findOrderIndexByYtIdAndPlaylistId(ytId, playlistId);

        // Delete the video from the video table
        videoRepository.deleteByYtId(ytId);

        // Delete the association record
        videoPlaylistRepository.deleteByYtIdAndPlaylistId(ytId, playlistId);

        // Get all videos assigned to the same playlist with order index higher than deletedOrderIndex
        List<VideoPlaylist> videosToUpdate = videoPlaylistRepository.findByPlaylistIdAndOrderIndexGreaterThanOrderByOrderIndexAsc(playlistId, deletedOrderIndex);

        // Decrement the order index of each video
        for (VideoPlaylist video : videosToUpdate) {
            video.setOrderIndex(video.getOrderIndex() - 1);
        }

        // Update the videos in the database
        videoPlaylistRepository.saveAll(videosToUpdate);
    }

    // find video by yt Id
    public Optional<Video> findByYtId(String ytId) {
        return videoRepository.findByYtId(ytId);
    }

    // check if there is record of ytId in db
    public boolean doesVideoExistByYtId(String ytId) {
        return videoRepository.existsByYtId(ytId);
    }

    // edit video by ytId
    public Video updateVideoByYtId(String ytId, Video video) {
        Video existingVideo = videoRepository.findByYtId(ytId)
                .orElseThrow(() -> new NotFoundException("Video not found with YT ID: " + ytId));

        existingVideo.setTitle(video.getTitle());
        existingVideo.setLink(video.getLink());
        existingVideo.setDuration(video.getDuration());
        existingVideo.setThumbnailUrl(video.getThumbnailUrl());
        existingVideo.setBookmark(video.getBookmark());
        existingVideo.setNote(video.getNote());
        existingVideo.setTimestamps(video.getTimestamps());
        existingVideo.setWatched(video.getWatched());

        return videoRepository.save(existingVideo);
    }
}
