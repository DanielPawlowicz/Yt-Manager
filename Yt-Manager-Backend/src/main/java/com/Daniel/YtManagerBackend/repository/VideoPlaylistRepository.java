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

    @Query("SELECT vp.orderIndex FROM VideoPlaylist vp WHERE vp.ytId = :ytId AND vp.playlistId = :playlistId")
    Integer findOrderIndexByYtIdAndPlaylistId(String ytId, Long playlistId);

    Long findPlaylistIdByYtId(String ytId);

    List<VideoPlaylist> findByPlaylistIdAndOrderIndexGreaterThanOrderByOrderIndexAsc(Long playlistId, int orderIndex);

    void deleteByYtIdAndPlaylistId(String ytId, Long playlistId);

    @Query("SELECT DISTINCT vp.playlistId FROM VideoPlaylist vp WHERE vp.ytId = :ytId")
    List<Long> findDistinctPlaylistIdsByYtId(String ytId);

    long countByPlaylistId(Long playlistId);

}
