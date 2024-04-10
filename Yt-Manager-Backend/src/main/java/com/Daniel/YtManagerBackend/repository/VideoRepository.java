package com.Daniel.YtManagerBackend.repository;

import com.Daniel.YtManagerBackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
