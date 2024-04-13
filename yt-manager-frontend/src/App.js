import React, { useState } from 'react';
import SearchYoutube from './components/SearchYoutube';
import Navbar from './components/Navbar';
import Playlist from './components/Playlist';
import Service from './service/Service';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

function App() {
    const [selectedPlaylist, setSelectedPlaylist] = useState(null);
    const [showSearch, setShowSearch] = useState(true); // Initially show SearchYoutube
    const [playlists, setPlaylists] = useState([]);

    const loadPlaylists = async () => {
      try{
        const response = await Service.getPlaylists();
        setPlaylists(response.data);
      } catch(err){
          console.error("Error fetching playlist for navbar: ", err);
      }
    };

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
          <div id="notification" className="notification"></div>

            <div className='nav-panel'>
                <Navbar handlePlaylistClick={handlePlaylistSelection} handleTitleClick={handleTitleClick} loadPlaylists={loadPlaylists} playlists={playlists}/>
            </div>
            <div className='main-panel'>
                {showSearch ? (
                    <SearchYoutube onPlaylistCreated={loadPlaylists}/>
                ) : (
                    <Playlist playlist={selectedPlaylist} />
                )}
            </div>
            <div style={{ clear: 'both' }}></div>
        </>
    );
}

export default App;