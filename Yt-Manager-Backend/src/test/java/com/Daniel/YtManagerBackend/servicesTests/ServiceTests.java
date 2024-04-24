package com.Daniel.YtManagerBackend.servicesTests;

import com.Daniel.YtManagerBackend.controller.exception.NotFoundException;
import com.Daniel.YtManagerBackend.model.Playlist;
import com.Daniel.YtManagerBackend.model.Video;
import com.Daniel.YtManagerBackend.model.VideoPlaylist;
import com.Daniel.YtManagerBackend.repository.PlaylistRepository;
import com.Daniel.YtManagerBackend.repository.VideoPlaylistRepository;
import com.Daniel.YtManagerBackend.repository.VideoRepository;
import com.Daniel.YtManagerBackend.service.PlaylistService;
import com.Daniel.YtManagerBackend.service.VideoPlaylistService;
import com.Daniel.YtManagerBackend.service.VideoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private VideoPlaylistRepository videoPlaylistRepository;

    @InjectMocks
    private VideoService videoService;

    @InjectMocks
    private PlaylistService playlistService;

    @InjectMocks
    private VideoPlaylistService videoPlaylistService;

    @Test
    public void testGetAllVideos() {
        when(videoRepository.findAll()).thenReturn(Arrays.asList(new Video(), new Video()));
        assertEquals(2, videoService.getAllVideos().size());
    }

    @Test
    public void testGetVideoById() {
        Video video = new Video();
        video.setId(1L);
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        assertEquals(video, videoService.getVideoById(1L));
    }

    @Test
    public void testUpdateVideo() {
        Video video = new Video();
        video.setId(1L);
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        when(videoRepository.save(any(Video.class))).thenReturn(video);
        assertEquals(video, videoService.updateVideo(1L, new Video()));
    }

    @Test
    public void testCreatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);
        assertEquals(1L, playlistService.createPlaylist(new Playlist()));
    }

    @Test
    public void testGetAllPlaylists() {
        when(playlistRepository.findAll()).thenReturn(Arrays.asList(new Playlist(), new Playlist()));
        assertEquals(2, playlistService.getAllPlaylists().size());
    }

    @Test
    public void testGetPlaylistById() {
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlist));
        assertEquals(playlist, playlistService.getPlaylistById(1L));
    }

    @Test
    public void testUpdatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setId(1L);
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlist));
        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);
        assertEquals(playlist, playlistService.updatePlaylist(1L, new Playlist()));
    }

    @Test
    public void testGetVideosByPlaylistOrderedByOrderIndex() {
        when(videoPlaylistRepository.findByPlaylistIdOrderByOrderIndex(1L)).thenReturn(Collections.singletonList(new VideoPlaylist()));
        assertEquals(1, videoPlaylistService.getVideosByPlaylistOrderedByOrderIndex(1L).size());
    }

    @Test
    public void testGetVideoPlaylistById() {
        VideoPlaylist videoPlaylist = new VideoPlaylist();
        videoPlaylist.setId(1L);
        when(videoPlaylistRepository.findById(1L)).thenReturn(Optional.of(videoPlaylist));
        assertEquals(videoPlaylist, videoPlaylistService.getVideoPlaylistById(1L));
    }

    @Test
    public void testUpdateVideoPlaylist() {
        VideoPlaylist videoPlaylist = new VideoPlaylist();
        videoPlaylist.setId(1L);
        when(videoPlaylistRepository.findById(1L)).thenReturn(Optional.of(videoPlaylist));
        when(videoPlaylistRepository.save(any(VideoPlaylist.class))).thenReturn(videoPlaylist);
        assertEquals(videoPlaylist, videoPlaylistService.updateVideoPlaylist(1L, new VideoPlaylist()));
    }

    @Test
    public void testDeleteVideoPlaylist() {
        videoPlaylistService.deleteVideoPlaylist(1L);
        verify(videoPlaylistRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAssociationByVideoYtIdAndPlaylistId() {
        videoPlaylistService.deleteAssociationByVideoYtIdAndPlaylistId("ytId", 1L);
        verify(videoPlaylistRepository, times(1)).deleteByYtIdAndPlaylistId("ytId", 1L);
    }
}
