package com.Daniel.YtManagerBackend.service;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.repository.VideoRepository;
import org.springframework.stereotype.Service;

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
}
