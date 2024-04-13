import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Service from '../service/Service';
import Video from '../service/Video';
import PlayVideo from './PlayVideo';

const Playlist = ({ playlist }) => {
    const [videos, setVideos] = useState([]);
    const [selectedVideo, setSelectedVideo] = useState(null);

    useEffect(() => {
        if (playlist) {
            console.log("Selected playlist id: " + playlist.id);
            getPlaylistVideos();
        }
    }, [playlist]);

    const getPlaylistVideos = async () => {
        try {
            const response = await Service.getPlaylistVideos(playlist.id);
            const videoIds = response.data;
            getVideosDetails(videoIds);
        } catch (err) {
            console.error(`Error fetching video IDs for playlist of id: ${playlist.id}`, err);
        }
    };

    const getVideosDetails = async (ids) => {
        try {
            const videoDetails = [];
            for (const id of ids) {
                const response = await Service.getVideoByYtId(id.ytId);
                videoDetails.push(response.data);
            }
            setVideos(videoDetails);
            console.log(videoDetails);
        } catch (err) {
            console.error(`Error fetching videos details for playlist of id: ${playlist.id}`, err);
        }
    };

    const handleVideoClick = (video) => {
        setSelectedVideo(video);
    };
    
    const markVideoAsWatched = async (videoYtId) => {
        try {
            // Fetch the existing video data
            const existingVideoResponse = await Service.getVideoByYtId(videoYtId);
            const existingVideo = existingVideoResponse.data;
            
            // Set the watched field to true
            existingVideo.watched = true;

            // Make a PUT request to update the video with all fields
            const response = await Service.editByYtId(videoYtId, existingVideo);
            
            getPlaylistVideos();

            // Handle the response
            console.log('Video marked as watched:', response.data);
        } catch (error) {
            console.error('Error marking video as watched:', error);
        }
    };
    

    return (
        <div className="main">
            <h1>{playlist.playlistName}</h1>

            {selectedVideo && <PlayVideo video={selectedVideo} />}

            <ul>
                {videos.map((video) => (
                    <>
                        <li key={video.id} className='video-render' onClick={() => handleVideoClick(video)}>
                            <img src={video.thumbnailUrl} alt={video.title} />
                            <div className="video-info">
                                <p className='video-title'>{video.title}</p>
                                <p>{Video.parseDuration(video.duration)}</p>
                            </div>
                        </li>
                        <button onClick={() => markVideoAsWatched(video.ytId)}>Mark as Watched</button>
                    </>
                ))}
            </ul>
        </div>
    );
};

export default Playlist;
