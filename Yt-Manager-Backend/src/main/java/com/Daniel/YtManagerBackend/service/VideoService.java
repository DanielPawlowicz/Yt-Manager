package com.Daniel.YtManagerBackend.service;
import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Video;
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

    // delete video by Yt Id
    @Transactional
    public void deleteVideoByYtId(String ytId) {
        videoPlaylistRepository.deleteByYtId(ytId);

        videoRepository.deleteByYtId(ytId);
    }

    public Optional<Video> findByYtId(String ytId) {
        return videoRepository.findByYtId(ytId);
    }

    // check if there is record of ytId in db
    public boolean doesVideoExistByYtId(String ytId) {
        return videoRepository.existsByYtId(ytId);
    }

}
