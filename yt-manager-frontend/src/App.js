import React, { useState } from 'react';
import SearchYoutube from './components/SearchYoutube';
import Navbar from './components/Navbar';
import Playlist from './components/Playlist';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

function App() {
    const [selectedPlaylist, setSelectedPlaylist] = useState(null);
    const [showSearch, setShowSearch] = useState(true); // Initially show SearchYoutube

    const handlePlaylistSelection = (playlist) => {
        setSelectedPlaylist(playlist);
        setShowSearch(false); // Hide search when a playlist is selected
    };

    const handleTitleClick = () => {
        setSelectedPlaylist(null); // Clear selected playlist
        setShowSearch(true); // Show search
    };

    return (
        <>
            <div className='nav-panel'>
                <Navbar handlePlaylistClick={handlePlaylistSelection} handleTitleClick={handleTitleClick} />
            </div>
            <div className='main-panel'>
                {showSearch ? (
                    <SearchYoutube />
                ) : (
                    <Playlist playlist={selectedPlaylist} />
                )}
            </div>
            <div style={{ clear: 'both' }}></div>
        </>
    );
}

export default App;