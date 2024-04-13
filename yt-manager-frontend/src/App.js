import React, { useState } from 'react';
import SearchYoutube from './components/SearchYoutube';
import Navbar from './components/Navbar';
import Playlist from './components/Playlist';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

function App() {

  const [selectedPlaylist, setSelectedPlaylist] = useState(null);

  // const handlePlaylistSelection = (playlist) => {
  //   setSelectedPlaylist(playlist);
  // };

  return(
    <>
      <div className='nav-panel'>
        <Navbar setSelectedPlaylist={setSelectedPlaylist} />
      </div>
      <div className='main-panel'>
        {selectedPlaylist ? (
          <Playlist playlist={selectedPlaylist} />
        ) : (
          <SearchYoutube />
        )}
      </div>
      <div style={{clear:'both'}}></div>
    </>
  );
}

export default App;