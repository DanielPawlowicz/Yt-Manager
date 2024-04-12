package com.Daniel.YtManagerBackend.repository;

import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoPlaylistRepository extends JpaRepository<VideoPlaylist, Long> {

    List<VideoPlaylist> findByPlaylistIdOrderByOrderIndex(Long playlistId);

    void deleteByYtId(String ytId);

    void deleteByPlaylistId(Long playlistId);

    // search for max orderIndex for specific playlist, and if no records then return 0
    @Query("SELECT COALESCE(MAX(vp.orderIndex), 0) FROM VideoPlaylist vp WHERE vp.playlistId = :playlistId")
    int findMaxOrderIndexByPlaylistId(Long playlistId);

}
