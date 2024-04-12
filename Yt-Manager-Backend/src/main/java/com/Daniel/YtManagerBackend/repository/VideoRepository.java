package com.Daniel.YtManagerBackend.repository;

import com.Daniel.YtManagerBackend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    void deleteByYtId(String ytId);

    Optional<Video> findByYtId(String ytId);
}
