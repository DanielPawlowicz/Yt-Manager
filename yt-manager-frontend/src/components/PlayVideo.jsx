import React from 'react';

const PlayVideo = ({ video }) => {
    return (
        <div className="play-video">
            <iframe
                width="840"
                height="472"
                src={`https://www.youtube.com/embed/${video.ytId}`}
                title="YouTube video player"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
            ></iframe>
        </div>
    );
};

export default PlayVideo;