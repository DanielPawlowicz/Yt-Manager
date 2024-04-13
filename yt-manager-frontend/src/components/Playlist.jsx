import React, { useEffect, useState } from 'react';
import Service from '../service/Service';
import Video from '../service/Video';
import PlayVideo from './PlayVideo';

const Playlist = ({ playlist }) => {
    const [videos, setVideos] = useState([]);
    const [selectedVideo, setSelectedVideo] = useState(null);

    useEffect(() => {
        if (playlist) {
            // setVideos([]);
            console.log("Selected playlist id: "+playlist.id);
            getPlaylistVideos();
            setSelectedVideo(null); // Reset selected video when playlist changes
        }
    }, [playlist]);


    const getPlaylistVideos = async () => {
        try {
            const response = await Service.getPlaylistVideos(playlist.id);
            console.log(response.data);
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
                console.log(id.ytId);
                const response = await Service.getVideoByYtId(id.ytId);
                videoDetails.push(response.data);
            }
            setVideos(videoDetails);
        } catch (err) {
            console.error(`Error fetching videos details for playlist of id: ${playlist.id}`, err);
        }
    };

    const handleVideoClick = (video) => {
        setSelectedVideo(video);
    };
    

    return (
        <div className="main">
            
            <h1>{playlist.playlistName}</h1>

            {selectedVideo && <PlayVideo video={selectedVideo} />}

            <ul>
                {videos.map((video) => (
                    <li key={video.id} className='video-render'  onClick={() => handleVideoClick(video)}>
                        <img src={video.thumbnailUrl} alt={video.title} />
                        <div className="video-info">
                            <p className='video-title'>{video.title}</p>
                            <p>{Video.parseDuration(video.duration)}</p>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Playlist;
