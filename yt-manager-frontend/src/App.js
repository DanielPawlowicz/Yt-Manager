import React, { useState } from 'react';
import SearchYoutube from './components/SearchYoutube';
import Navbar from './components/Navbar';
import Playlist from './components/Playlist';

const API_KEY = 'AIzaSyCCd-2OgVAdtWYRIWQ6JapPXYB1-IjSESg';

function App() {
  const [selectedPlaylist, setSelectedPlaylist] = useState(null);
  const [showSearch, setShowSearch] = useState(true); // Initially show SearchYoutube

  return (
      <>
          <div className='nav-panel'>
              <Navbar setSelectedPlaylist={setSelectedPlaylist} setShowSearch={setShowSearch} />
          </div>
          <div className='main-panel'>
            {showSearch ? (
                <SearchYoutube />
            ) : (
                <Playlist playlist={selectedPlaylist} />
            )}
          </div>
          <div style={{clear:'both'}}></div>
      </>
  );
}

export default App;