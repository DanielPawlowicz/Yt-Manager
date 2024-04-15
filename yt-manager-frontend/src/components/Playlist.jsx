import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import Service from '../service/Service';
import Video from '../service/Video';

const Playlist = ({ playlist }) => {
    const [videos, setVideos] = useState([]);
    const [selectedVideo, setSelectedVideo] = useState(null);
    const [videoCount, setVideoCount] = useState(null);
    const [loading, setLoading] = useState(true);
    const [bookmarking, setBookmarking] = useState(false);
    const playerRef = useRef(null);
    const [readyVideo, setReadyVideo] = useState(false);

    useEffect(() => {
        setSelectedVideo(null);

        if (playlist) {
            console.log("Selected playlist id: " + playlist.id);
            getPlaylistVideos();
            getVideosCount();
        }
    }, [playlist]);

    useEffect(() => {
        // Reset bookmarking state when video changes
        setBookmarking(false);
    }, [selectedVideo]);

    const getPlaylistVideos = async () => {
        try {
            const response = await Service.getPlaylistVideos(playlist.id);
            const videoIds = response.data;
            getVideosDetails(videoIds);
        } catch (err) {
            console.error(`Error fetching video IDs for playlist of id: ${playlist.id}`, err);
        }
    };

    const getVideosCount = async () => {
        try {
            const response = await Service.countVideos(playlist.id);
            setVideoCount(response.data);
            setLoading(false); // Set loading to false after count is fetched
        } catch (err) {
            console.error("error getting the video count ", err);
            setVideoCount(0); // Set count to 0 in case of error
            setLoading(false); // Set loading to false
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

    const handleVideoClick = async (video) => {
        try {
            setReadyVideo(false);
            setSelectedVideo(video);
            // Fetch the bookmark time for the selected video
            const response = await Service.getVideoByYtId(video.ytId);
            const bookmarkTime = response.data.bookmark;
            setSelectedVideo({ ...video, bookmark: bookmarkTime });
            console.log("bookmark (for id " + response.data.ytId + "): " + bookmarkTime);
            console.log(video);
            setReadyVideo(true);
        } catch (error) {
            console.error('Error fetching bookmark time:', error);
        }
    };

    const handleBookmarkClick = async () => {
        try {
            // Ensure the player reference exists
            if (playerRef.current) {
                // Get the current time of the video
                const currentTime = playerRef.current.getCurrentTime();

                // Create a bookmark object with video details and current time
                const videoBookmark = { ...selectedVideo, bookmark: currentTime };

                // Make a PUT request to save the bookmark
                const response = await Service.editByYtId(selectedVideo.ytId, videoBookmark);

                // Handle success
                console.log('Bookmark saved:', response.data);

                // Set bookmarking state to false
                setBookmarking(false);
            } else {
                console.error('Player reference not found.');
            }
        } catch (error) {
            // Handle error
            console.error('Error bookmarking video:', error);
        }
    };

    const handleButtonClick = (video) => {
        // Toggle the watched property
        const newWatchedStatus = !video.watched;
    
        // Call markVideoAsWatched function with the updated watched status
        markVideoAsWatched(video.ytId, newWatchedStatus);
    };
    
    const handleDeleteButtonClick = async (videoYtId, playlistId) => {
        try {
            // Call deleteVideo function with the videoYtId and playlistId
            await Service.deleteVideo(videoYtId);
    
            console.log("Video of ytId " + videoYtId + " - deleted successfully");
    
            // Refresh the playlist after deleting the video
            getPlaylistVideos();
    
        } catch (err) {
            console.error("Error deleting Video of ytId " + videoYtId);
        }
    };

    const markVideoAsWatched = async (videoYtId, watched) => {
        try {
            // Fetch the existing video data
            const existingVideoResponse = await Service.getVideoByYtId(videoYtId);
            const existingVideo = existingVideoResponse.data;
    
            // Set the watched field to true
            existingVideo.watched = watched;
    
            // Make a PUT request to update the video with all fields
            const response = await Service.editByYtId(videoYtId, existingVideo);
    
            getPlaylistVideos();
    
            // Handle the response
            console.log('Video marked as watched:', response.data);
        } catch (error) {
            console.error('Error marking video as watched:', error);
        }
    };


   // Function to handle the onReady event of the player
const handlePlayerReady = () => {
    // Now the player is ready to receive API calls
    console.log('Player ready');

    // Ensure the player reference exists
    if (playerRef.current) {
        // Check if a bookmark exists
        if (selectedVideo && selectedVideo.bookmark !== null) {
            // Seek to the bookmark time
            .current.seekTo(selectedVideo.bookmark);
        }
    } else {
        console.error('Player reference not found.');
    }
};

return (
    <div className="main">
        <h1>{playlist.playlistName}</h1>

        {loading ? (
            <p>Loading...</p> // Render loading state while count is being fetched
        ) : (
            <h2 className='videos-count'>Videos: {videoCount}</h2> // Render the count when it's available
        )}

        {selectedVideo && readyVideo &&
            <div className="play-video">
                <iframe
                    ref={playerRef} // Set the ref to access the YouTube player instance
                    id="youtube-player"
                    width="840"
                    height="472"
                    src={`https://www.youtube.com/embed/${selectedVideo.ytId}?enablejsapi=1&t=3`}
                    title="YouTube video player"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                    onLoad={handlePlayerReady}
                ></iframe>
                <button onClick={handleBookmarkClick} disabled={bookmarking}>
                    {bookmarking ? 'Bookmarking...' : 'Bookmark'}
                </button>
            </div>
        }

        <ul>
            {videos.map((video) => (
                <li key={video.id} className={`video-render ${video.watched ? 'watched' : ''}`}>
                    <table>
                        <tr>
                            <td className='clickable-td'>
                                <div className="clickable-playlist" onClick={() => handleVideoClick(video)}>
                                    <img src={video.thumbnailUrl} alt={video.title} />
                                    <div className="video-info">
                                        <p className='video-title'>{video.title}</p>
                                        <p>{Video.parseDuration(video.duration)}</p>
                                    </div>
                                </div>
                            </td>
                            <td className='buttons-td'>
                                <div className="buttons-playlist">
                                    <button onClick={() => handleButtonClick(video)}>
                                        {video.watched ? 'Set unwatched' : 'Set watched'}
                                    </button>
                                    <br />
                                    <button onClick={() => handleDeleteButtonClick(video.ytId)}>Delete</button>

                                </div>
                            </td>
                        </tr>
                    </table>

                </li>

            ))}
        </ul>
    </div>
);

};

export default Playlist;
