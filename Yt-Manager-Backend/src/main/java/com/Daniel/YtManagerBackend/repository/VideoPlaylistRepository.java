package com.Daniel.YtManagerBackend.repository;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoPlaylistRepository extends JpaRepository<VideoPlaylist, Long> {

    List<VideoPlaylist> findByPlaylistIdOrderByOrderIndex(Long playlistId);

    void deleteByYtId(String ytId);

    void deleteByPlaylistId(Long playlistId);


}
