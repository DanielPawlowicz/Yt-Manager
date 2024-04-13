import React, { useEffect, useState } from 'react';
import Service from '../service/Service';

const Playlist = ({ playlist }) => {
    const [videos, setVideos] = useState([]);

    useEffect(() => {
        if (playlist) {
            // setVideos([]);
            console.log("Selected playlist id: "+playlist.id);
            getPlaylistVideos();
        }
    }, [playlist]);


    const getPlaylistVideos = async () => {
        try {
            const response = await Service.getPlaylistVideos(playlist.id);
            console.log(response.data);
            const videoIds = response.data; // Assuming response.data is an array of video IDs
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
                // id.ytId = "";
            }
            setVideos(videoDetails);
        } catch (err) {
            console.error(`Error fetching videos details for playlist of id: ${playlist.id}`, err);
        }
    };
    

    return (
        <div className="main">
            <h1>{playlist.playlistName}</h1>
            <ul>
                {videos.map((video) => (
                    <li key={video.id}>
                        <h3>{video.title}</h3>
                        <img src={video.thumbnailUrl} alt={video.title} />
                        <p>{video.note}</p>
                        {/* Add more details as needed */}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Playlist;
