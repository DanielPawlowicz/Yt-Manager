package com.Daniel.YtManagerBackend.service;
import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

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

        return videoRepository.save(existingVideo);
    }

    // delete video
    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }

}
