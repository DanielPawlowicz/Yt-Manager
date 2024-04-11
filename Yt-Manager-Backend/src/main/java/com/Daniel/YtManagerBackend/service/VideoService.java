package com.Daniel.YtManagerBackend.service;
import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.repository.VideoPlaylistRepository;
import com.Daniel.YtManagerBackend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // delete video
    @Transactional
    public void deleteVideo(Long videoId) {
        videoPlaylistRepository.deleteByVideoId(videoId);

        videoRepository.deleteById(videoId);
    }

}
